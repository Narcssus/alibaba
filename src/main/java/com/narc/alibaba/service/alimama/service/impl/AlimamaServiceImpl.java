package com.narc.alibaba.service.alimama.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.dao.service.AlitConfigDaoService;
import com.narc.alibaba.service.alimama.dao.service.AlitMessageLogDaoService;
import com.narc.alibaba.service.alimama.dao.service.AlitPublisherOrderDaoService;
import com.narc.alibaba.service.alimama.entity.AlitMessageLog;
import com.narc.alibaba.service.alimama.entity.AlitPublisherOrder;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.DateUtils;
import com.narc.alibaba.utils.HttpUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkCouponGetRequest;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkCouponGetResponse;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
@Service
@Slf4j
public class AlimamaServiceImpl implements AlimamaService {

    @Value("${alimamaServer.url}")
    private String apiUrl;
    @Value("${alimamaServer.appKey}")
    private String appKey;
    @Value("${alimamaServer.appSecret}")
    private String appSecret;
    @Value("${alimamaServer.adzoneId}")
    private Long adzoneId;

    private static final String TYPE_VIP = "VIP";
    private static final String TYPE_NORMAL = "NORMAL";
    private static final String TYPE_NULL = "NULL";
    private static final String URL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/.?=&";
    private static final String WORDS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    private AlitConfigDaoService alitConfigDaoService;
    @Autowired
    private AlitPublisherOrderDaoService alitPublisherOrderDaoService;
    @Autowired
    private AlitMessageLogDaoService alitMessageLogDaoService;


    @Override
    public JSONObject tranShareWord(JSONObject paramObject) {
        String originalWord = paramObject.getString("originalWord");
        String type = paramObject.getString("type");
        AlitMessageLog alitMessageLog = new AlitMessageLog();
        alitMessageLog.setSenderId(paramObject.getString("senderId"));
        alitMessageLog.setSenderName(paramObject.getString("senderName"));
        alitMessageLog.setMsgContent(originalWord);
        String res = tranShareWord(originalWord, type, alitMessageLog);
        alitMessageLog.setRetMsg(res);
        alitMessageLogDaoService.insertSelective(alitMessageLog);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranShareWord", res);
        return jsonObject;
    }

    @Override
    public JSONObject dealTklAdmin(JSONObject paramObject) {
        String originalWord = paramObject.getString("originalWord");
        AlitMessageLog alitMessageLog = new AlitMessageLog();
        alitMessageLog.setSenderId(paramObject.getString("senderId"));
        alitMessageLog.setSenderName(paramObject.getString("senderName"));
        alitMessageLog.setMsgContent(originalWord);
        String res = "无效口令";
        switch (originalWord) {
            case "查看未结算用户":
                res = dealAllUndoOrders();
                break;
            case "结算未结算用户":
                res = endAllDoingOrders();
                break;
            default:
                break;
        }
        alitMessageLog.setRetMsg(res);
        alitMessageLogDaoService.insertSelective(alitMessageLog);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", res);
        return jsonObject;
    }

    private String endAllDoingOrders(){
        alitPublisherOrderDaoService.endAllDoingOrders();
        return "结算成功";
    }

    private String dealAllUndoOrders() {
        List<AlitPublisherOrder> list = alitPublisherOrderDaoService.getAllUndoOrders();
        if (CollectionUtils.isEmpty(list)) {
            return "无未结算订单";
        }
        Map<String, BigDecimal> map = new HashMap<>();
        for (AlitPublisherOrder order : list) {
            String key = order.getSenderName() + "[" + order.getSenderId() + "]";
            if (!map.containsKey(key)) {
                map.put(key, new BigDecimal(0));
            }
            map.put(key, map.get(key).add(order.getDiscountFee()));
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            BigDecimal mapValue = entry.getValue();
            sb.append(mapKey).append("===").append(mapValue.toPlainString())
                    .append("\r\n");
        }
        //更新状态为处理中
        List<String> ids = list.stream().map(AlitPublisherOrder::getTradeId).collect(Collectors.toList());
        alitPublisherOrderDaoService.updateUndoOrders(ids);
        return sb.toString();
    }


    @Override
    public void getOrders(Date startTime, Date endTime) {
        String uid = alitConfigDaoService.getValueByKey("taobao_user_id");
        String start_time = DateUtils.convertDateToStr(startTime, DateUtils.FORMAT_19);
        String end_time = DateUtils.convertDateToStr(endTime, DateUtils.FORMAT_19);
        JSONArray array = getPaidOrder(uid, start_time, end_time);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            AlitPublisherOrder order = new AlitPublisherOrder();
            order.setTradeId(jsonObject.getString("trade_id"));
            order.setTradeParentId(jsonObject.getString("trade_parent_id"));
            order.setItemId(jsonObject.getString("item_id"));
            order.setItemNum(jsonObject.getInteger("item_num"));
            order.setItemTitle(jsonObject.getString("item_title"));
            order.setTkCreateTime(jsonObject.getDate("tk_create_time"));
            order.setTbPaidTime(jsonObject.getDate("tb_paid_time"));
            order.setTotalCommissionRate(jsonObject.getBigDecimal("total_commission_rate"));
            order.setAlipayTotalPrice(jsonObject.getBigDecimal("alipay_total_price"));
            order.setSubsidyRate(jsonObject.getString("subsidy_rate"));
            order.setTkTotalRate(jsonObject.getString("tk_total_rate"));
            order.setPubSharePreFee(jsonObject.getBigDecimal("pub_share_pre_fee"));
            order.setAlimamaRate(jsonObject.getBigDecimal("alimama_rate"));
            order.setTkStatus(jsonObject.getString("tk_status"));
            order.setItemPrice(jsonObject.getBigDecimal("item_price"));
            if (alitPublisherOrderDaoService.isExistKey(order.getTradeId())) {
                alitPublisherOrderDaoService.updateByPrimaryKeySelective(order);
            } else {
                alitPublisherOrderDaoService.insertOne(order);
            }
        }
    }

    @Override
    public void dealOrders() {
        List<AlitPublisherOrder> orders = alitPublisherOrderDaoService.getAllNewOrders();
        log.debug("处理订单,新订单{}个", orders.size());
        for (AlitPublisherOrder order : orders) {
            String itemId = order.getItemId();
            Date paidTime = order.getTbPaidTime();
            List<AlitMessageLog> logs = alitMessageLogDaoService.getLogs(itemId, paidTime);
            if (CollectionUtils.isEmpty(logs)) {
                log.info("订单{}，商品{},非公众号用户，请手动匹配", order.getTradeParentId(), itemId);
                continue;
            }
            List<String> senders = logs.stream().map(AlitMessageLog::getSenderId).distinct()
                    .collect(Collectors.toList());
            if (senders.size() > 1) {
                log.info("订单{}，商品{},用户无法匹配，请手动匹配", order.getTradeParentId(), itemId);
                continue;
            }
            AlitMessageLog alitMessageLog = logs.get(0);
            BigDecimal discountRate = alitMessageLog.getDiscountRate();
            if (discountRate == null) {
                discountRate = new BigDecimal(0);
            }
            BigDecimal price = order.getAlipayTotalPrice();
            BigDecimal discount = price.multiply(discountRate).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
            AlitPublisherOrder update = new AlitPublisherOrder();
            update.setTradeId(order.getTradeId());
            update.setSenderId(alitMessageLog.getSenderId());
            update.setSenderName(alitMessageLog.getSenderName());
            update.setDiscountRate(discountRate);
            update.setDiscountFee(discount);
            update.setIsDone("0");
            alitPublisherOrderDaoService.updateByPrimaryKeySelective(update);
        }

    }

    @Override
    public void dealUnfinshOrders() {
        List<AlitPublisherOrder> orders = alitPublisherOrderDaoService.getAllUnFinishedOrders();
        log.debug("处理未完成订单,未完成订单{}个", orders.size());
        for (AlitPublisherOrder order : orders) {
            try {
                //休眠5秒
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
            }
            AlitPublisherOrder update = getOrderStatusByOrderId(order.getTradeId(),
                    order.getTbPaidTime(), order.getTkStatus());
            if (update != null) {
                alitPublisherOrderDaoService.updateByPrimaryKeySelective(update);
            }
        }
    }

    private AlitPublisherOrder getOrderStatusByOrderId(String tradeId, Date time, String tKStatus) {
        String uid = alitConfigDaoService.getValueByKey("taobao_user_id");
        String start_time = DateUtils.convertDateToStr(DateUtils.addMinutes(time, -1), DateUtils.FORMAT_19);
        String end_time = DateUtils.convertDateToStr(DateUtils.addMinutes(time, 1), DateUtils.FORMAT_19);
        JSONArray jsonArray = getPaidOrder(uid, start_time, end_time);
        if (CollectionUtils.isEmpty(jsonArray)) {
            log.error("没有找到对应的订单{},付款时间{}", tradeId, DateUtils.convertDateToStr(time, DateUtils.FORMAT_19));
            return null;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (tradeId.equals(jsonObject.getString("trade_id"))) {
                if (tKStatus.equals(jsonObject.getString("tk_status"))) {
                    log.debug("订单{},付款时间{},状态未改变", tradeId, DateUtils.convertDateToStr(time, DateUtils.FORMAT_19));
                    return null;
                }
                AlitPublisherOrder order = new AlitPublisherOrder();
                order.setTradeId(tradeId);
                order.setTkStatus(jsonObject.getString("tk_status"));
                return order;
            }
        }
        log.error("没有找到对应的订单{},付款时间{}", tradeId, DateUtils.convertDateToStr(time, DateUtils.FORMAT_19));
        return null;
    }

    private JSONArray getPaidOrder(String uid, String start_time, String end_time) {
        JSONArray resArray = new JSONArray();
        try {
            String url = "https://api.taokouling.com/tbk/TbkScOrderDetailsGet";
            JSONObject req = new JSONObject();
            req.put("uid", uid);
            req.put("start_time", start_time);
            req.put("end_time", end_time);
            req.put("query_type", "2");
            req.put("page_size", 20);
            String res = HttpUtils.httpGet(url, req);
            JSONObject data = JSON.parseObject(res).getJSONObject("data");
            JSONArray jsonArray = new JSONArray();
            if (data.get("results") instanceof JSONArray) {
                return resArray;
            } else {
                jsonArray = data.getJSONObject("results").getJSONArray("publisher_order_dto");
            }

            resArray.addAll(jsonArray);
            while (data.getBooleanValue("has_next")) {
                req.put("position_index", data.getString("position_index"));
                req.put("jump_type", "1");
                res = HttpUtils.httpGet(url, req);
                data = JSON.parseObject(res).getJSONObject("data");
                jsonArray = JSON.parseObject(res).getJSONObject("data")
                        .getJSONObject("results").getJSONArray("publisher_order_dto");
                resArray.addAll(jsonArray);
            }
        } catch (Exception e) {
            log.error("查询订单失败", e);
        }
        return resArray;
    }

    private static String getUrlByWord(String word) {
        String url = "";
        for (int i = word.indexOf("http"); i < word.length(); i++) {
            char c = word.charAt(i);
            if (!URL_CHAR.contains("" + c)) {
                break;
            }
            url += c;
        }
        return url;
    }

    private static String getNameByWord(String word) {
        String res = "";
        for (int i = word.lastIndexOf("】"); i >= 0; i--) {
            char c = word.charAt(i);
            if (c == '】') {
                continue;
            }
            if (c == '【') {
                break;
            }
            res = ("" + c) + res;

        }
        return res;
    }

    private static String getName(String content) {
        String res = "";
        int i = content.indexOf("extraData");
        content = content.substring(i);
        for (i = content.indexOf("{"); i < content.length(); i++) {
            char c = content.charAt(i);
            res += c;
            if (c == '}') {
                break;
            }
        }
        JSONObject jsonObject = JSON.parseObject(res);
        return jsonObject.getString("title");
    }

    private static String getItemId(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        if (content.contains("item.taobao.com")) {
            for (int i = content.indexOf("&id=") + 4; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '&') {
                    break;
                }
                res.append(c);
            }
            return res.toString();
        }
        if (content.contains("a.m.tmall.com")) {
            String a = "https://a.m.tmall.com/";
            int index = content.indexOf(a) + a.length() + 1;
            for (int i = index; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '.') {
                    break;
                }
                res.append(c);
            }
            return res.toString();
        }
        if (content.contains("a.m.taobao.com")) {
            String a = "https://a.m.taobao.com/";
            int index = content.indexOf(a) + a.length() + 1;
            for (int i = index; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '.') {
                    break;
                }
                res.append(c);
            }
            return res.toString();
        }
        return null;
    }

    private String tranShareWord(String originalWord, String type, AlitMessageLog alitMessageLog) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
            TbkDgMaterialOptionalResponse.MapData mapData = getItem(originalWord);
            if (mapData == null) {
                return "该商品不在优惠库中或输入淘口令错误";
            }
            log.debug("查询商品详情返回报文：{}", JSON.toJSONString(mapData));
            alitMessageLog.setItemId("" + mapData.getItemId());
            String itemUrl = mapData.getCouponShareUrl();
            if (StringUtils.isEmpty(itemUrl)) {
                itemUrl = mapData.getUrl();
            }
            itemUrl = "https:" + itemUrl;
            log.info("itemUrl=" + itemUrl);

            String tickets = "无优惠券";
            //查询有无优惠券
            try {
                TbkCouponGetRequest req = new TbkCouponGetRequest();
                req.setItemId(mapData.getItemId());
                req.setActivityId(mapData.getCouponId());
                TbkCouponGetResponse rsp = client.execute(req);
                TbkCouponGetResponse.MapData data = rsp.getData();
                if (data != null && data.getCouponRemainCount() > 0) {
                    tickets = data.getCouponAmount() + "元";
                }
            } catch (Exception e) {
                log.error("查询优惠券失败", e);
                tickets = "";
            }

            TbkTpwdCreateRequest req2 = new TbkTpwdCreateRequest();
            req2.setText(mapData.getTitle());
            req2.setUrl(itemUrl);
            TbkTpwdCreateResponse response2 = client.execute(req2);
            TbkTpwdCreateResponse.MapData res2 = response2.getData();

            String model = res2.getModel();
            StringBuilder sb = new StringBuilder();
            sb.append(model).append("\r\n");
            if (!StringUtils.isEmpty(mapData.getCommissionRate())) {
                if (!TYPE_NULL.equals(type)) {
                    BigDecimal rate = new BigDecimal(mapData.getCommissionRate());
                    rate = rate.multiply(new BigDecimal(0.9));
                    rate = rate.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                    BigDecimal price = new BigDecimal(mapData.getReservePrice());
                    if (mapData.getZkFinalPrice() != null) {
                        price = new BigDecimal(mapData.getZkFinalPrice());
                    }
                    rate = doDiscountStrategy(rate, price, type);
                    alitMessageLog.setDiscountRate(rate);
                    BigDecimal discount = price.multiply(rate)
                            .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                    sb.append("==================").append("\r\n");
                    sb.append("返现率为").append(rate.setScale(2, BigDecimal.ROUND_HALF_DOWN)
                            .toPlainString()).append("%").append("\r\n");

                    sb.append("==================").append("\r\n");
                    sb.append("支付").append(price.toPlainString()).append("元，");
                    sb.append("预计返现为").append(discount.setScale(2, BigDecimal.ROUND_HALF_DOWN)
                            .toPlainString()).append("元").append("\r\n");
                }
                if (StringUtils.isNotBlank(tickets)) {
                    sb.append("==================").append("\r\n");
                    sb.append("优惠券：").append(tickets);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("系统失败", e);
            return "系统失败,请稍后再试";
        }
    }

    private BigDecimal doDiscountStrategy(BigDecimal rate, BigDecimal price, String type) {
        if (TYPE_VIP.equals(type)) {
            return rate;
        }
        //执行策略
        BigDecimal earn = rate.multiply(price).divide(new BigDecimal(100));
        //返现小于1元，不赚钱
        if (earn.compareTo(new BigDecimal(1)) <= 0) {
            return rate;
        }
        //返现1-5元，赚10%
        if (earn.compareTo(new BigDecimal(5)) <= 0) {
            return rate.multiply(new BigDecimal(1 - 0.1));
        }
        //返现5-10元，赚15%
        if (earn.compareTo(new BigDecimal(10)) <= 0) {
            return rate.multiply(new BigDecimal(1 - 0.15));
        }
        //返现10-25元，赚25%
        if (earn.compareTo(new BigDecimal(25)) <= 0) {
            return rate.multiply(new BigDecimal(1 - 0.25));
        }
        //返现25-50元，赚40%
        if (earn.compareTo(new BigDecimal(50)) <= 0) {
            return rate.multiply(new BigDecimal(1 - 0.4));
        }
        //返现50-100元，赚50%
        if (earn.compareTo(new BigDecimal(100)) <= 0) {
            return rate.multiply(new BigDecimal(1 - 0.5));
        }
        //返现100元以上，赚60%
        return rate.multiply(new BigDecimal(1 - 0.6));
    }

    private TbkDgMaterialOptionalResponse.MapData getItem(String originalWord) {
        if (originalWord.contains("https")) {
            //用链接的方式找
            String httpContent = HttpUtils.sendGet(getUrlByWord(originalWord));
            String itemId = getItemId(httpContent);
            String itemName = getNameById(itemId);
            if (itemName == null) {
                itemName = getName(httpContent);
            }
            TbkDgMaterialOptionalResponse.MapData mapData = getItemByTitleAndId(itemName, itemId);
            if (mapData != null) {
                return mapData;
            }
        }
        //用第三方工具找
        String word = getWordByContent(originalWord);
        String url = "https://api.taokouling.com/tkl/tkljm?apikey=bwZnYFuzyD&tkl=" + word;
        String httpContent = HttpUtils.sendGet(url);
        JSONObject jsonObject = JSON.parseObject(httpContent);
        String itemId = getItemId(jsonObject.getString("url"));
        String itemName = getNameById(itemId);
        if (itemName == null) {
            itemName = jsonObject.getString("content");
        }
        if (itemId == null) {
            return null;
        }
        return getItemByTitleAndId(itemName, itemId);
    }

    private TbkDgMaterialOptionalResponse.MapData getItemByTitleAndId(String itemName, String itemId) {
        TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
        if (itemName.contains("】")) {
            itemName = itemName.substring(itemName.lastIndexOf("【") + 1, itemName.lastIndexOf("】"));
        }
        log.debug("调用淘宝接口搜索商品,名称：{}，id:{}", itemName, itemId);
        try {
            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            long pageNo = 1L;
            req.setAdzoneId(adzoneId);
            req.setQ(itemName);
            req.setPageSize(100L);
            req.setPageNo(pageNo);
            List<TbkDgMaterialOptionalResponse.MapData> res = new ArrayList<>();
            TbkDgMaterialOptionalResponse response;
            do {
                response = client.execute(req);
                if (response != null && !CollectionUtils.isEmpty(response.getResultList())) {
                    res.addAll(response.getResultList());
                }
                pageNo++;
            } while (response != null && response.getTotalResults() != null &&
                    response.getTotalResults() > pageNo * 100L);
            for (TbkDgMaterialOptionalResponse.MapData mapData : res) {
                if (itemId.equals("" + mapData.getItemId())) {
                    return mapData;
                }
            }
        } catch (Exception e) {
            log.error("失败", e);
            return null;
        }
        return null;
    }

    private String getNameById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        try {
            TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
            TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
            req.setNumIids(id);
            TbkItemInfoGetResponse rsp = client.execute(req);
            List<TbkItemInfoGetResponse.NTbkItem> items = rsp.getResults();
            TbkItemInfoGetResponse.NTbkItem item = items.get(0);
            return item.getTitle();
        } catch (Exception e) {
            log.error("失败", e);
            return null;
        }
    }


    private static String getWordByContent(String content) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (!WORDS.contains("" + c)) {
                if (res.length() > 10) {
                    return res.toString();
                }
                res = new StringBuilder();
                continue;
            }
            res.append(c);
        }
        if (res.length() > 10) {
            return res.toString();
        }
        return null;
    }

}
