package com.narc.alibaba.service.alimama.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.HttpUtils;
import com.taobao.api.ApiException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public JSONObject tranShareWord(JSONObject paramObject) {
        String originalWord = paramObject.getString("originalWord");
        String res = tranShareWord(originalWord);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranShareWord", res);
        return jsonObject;
    }

    private static final String URL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/.?=&";
    private static final String WORDS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

//    private static String getItemId(String content) {
//        String res = "";
//        for (int i = content.indexOf("https://a.m.tmall.com/") + 23; i < content.length(); i++) {
//            char c = content.charAt(i);
//            if (c == '.') {
//                break;
//            }
//            res += c;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        System.out.println(getItemId("https:\\/\\/item.taobao.com\\/item.htm?ut_sk=1.XGGbB\\/zdPqUDAPYmrex1D0u%2B_21380790_1606614854934.TaoPassword-Weixin.DETAIL_FISSION_COUPON&preSpm=share&suid=CD4ED24F-7864-4283-80B0-08521429E2B6&id=601636637508&poplayer=fission_sub_coupon&sellerId=1885672960&token=8845609da93da616e8848abbd7bd045fb265ac8f6c5b214b609b4cea74361f82d78e1347a43f732e4767ac14f7a1838dc8316b5edae173b48afd47bab6f049f788342e415af1cc5020692deb991bcd4e4733d8567a40dc79ee42cf10f3701fe1&sourceType=item&detailSharePosition=interactBar&shareUniqueId=5794075827&un=698f4d817eb93d5f28b5e1fb31f54c83&share_crt_v=1&spm=a2159r.13376460.0.0&sp_tk=UWJiNmNudXVoZTQ=&poplayertoken=1606636945678&bxsign=tcdnyz1p8NkDQ6uTq-ZZoPgPeFP_Jr9NHV7IPGwuDV7ewgBMREstB80gnCPF6l3Mp-QF9HvlK6k2F4tsjLafwEN940Tfe1C0TOSc-8y1YUh2HE"));
    }


    private static String getItemId(String content) {
        String res = "";
        if (content.contains("item.taobao.com")) {
            for (int i = content.indexOf("&id=") + 4; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '&') {
                    break;
                }
                res += c;
            }
            return res;
        }
        if (content.contains("a.m.tmall.com")) {
            String a = "https://a.m.tmall.com/";
            for (int i = content.indexOf(a) + a.length() + 1; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '.') {
                    break;
                }
                res += c;
            }
            return res;
        }
        if (content.contains("a.m.taobao.com")) {
            String a = "https://a.m.taobao.com/";
            for (int i = content.indexOf(a) + a.length() + 1; i < content.length(); i++) {
                char c = content.charAt(i);
                if (c == '.') {
                    break;
                }
                res += c;
            }
            return res;
        }
        return null;
    }

    private String tranShareWord(String originalWord) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);


            TbkDgMaterialOptionalResponse.MapData mapData = getItem(originalWord);
            if (mapData == null) {
                return "没有找到该商品";
            }
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
                if (data.getCouponRemainCount() > 0) {
                    tickets = data.getCouponAmount() + "元";
                }
            } catch (Exception e) {

            }

            TbkTpwdCreateRequest req2 = new TbkTpwdCreateRequest();
            req2.setText(mapData.getTitle());
            req2.setUrl(itemUrl);
            TbkTpwdCreateResponse response2 = client.execute(req2);
            TbkTpwdCreateResponse.MapData res2 = response2.getData();
            String model = res2.getModel();
            String rate = mapData.getCommissionRate();
            StringBuilder sb = new StringBuilder();
            sb.append(model).append("\r\n");
            if (!StringUtils.isEmpty(rate)) {
                BigDecimal a = new BigDecimal(rate);
                a = a.multiply(new BigDecimal(0.9));
                BigDecimal b = a.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                sb.append("==================").append("\r\n");
                sb.append("返现率为").append(b.toPlainString()).append("%").append("\r\n");
                BigDecimal c = new BigDecimal(mapData.getReservePrice());
                BigDecimal d = c.multiply(b).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                sb.append("==================").append("\r\n");
                sb.append("支付").append(c.toPlainString()).append("元，");
                sb.append("预计返现为").append(d.toPlainString()).append("元").append("\r\n");
                sb.append("==================").append("\r\n");
                sb.append("优惠券：").append(tickets);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("系统失败", e);
            return "系统失败";
        }
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
        if(itemName.contains("】")){
            itemName = itemName.substring(itemName.lastIndexOf("【")+1,itemName.lastIndexOf("】"));
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
                res.addAll(response.getResultList());
                pageNo++;
            } while (response.getTotalResults() > pageNo * 100L);
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
        String res = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (!WORDS.contains("" + c)) {
                if (res.length() > 10) {
                    return res;
                }
                res = "";
                continue;
            }
            res += c;
        }
        if (res.length() > 10) {
            return res;
        }
        return null;
    }

}
