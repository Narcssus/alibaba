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
    @Value("${alimamaServer.keyWord}")
    private String keyWord;

    @Override
    public JSONObject tranShareWord(JSONObject paramObject) {
        String originalWord = paramObject.getString("originalWord");
        String res = tranShareWord(originalWord);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranShareWord", res);
        return jsonObject;
    }

    private static final String URL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/.?=&";

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
        String res = "";
        for (int i = content.indexOf("https://a.m.tmall.com/") + 23; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '.') {
                break;
            }
            res += c;
        }
        return res;
    }

    private static String getItemIdBy3rd(String url) {
        String res = "";
        if (url.contains("item.taobao.com")) {
            for (int i = url.indexOf("id=") + 3; i < url.length(); i++) {
                char c = url.charAt(i);
                if (c == '&') {
                    break;
                }
                res += c;
            }
            return res;
        }
        if (url.contains("a.m.tmall.com")) {
            String a = "https://a.m.tmall.com/";
            for (int i = url.indexOf(a) + a.length()+1; i < url.length(); i++) {
                char c = url.charAt(i);
                if (c == '.') {
                    break;
                }
                res += c;
            }
            return res;
        }
        if (url.contains("a.m.taobao.com")) {
            String a = "https://a.m.taobao.com/";
            for (int i = url.indexOf(a) + a.length()+1; i < url.length(); i++) {
                char c = url.charAt(i);
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
                c = c.multiply(b).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                sb.append("==================").append("\r\n");
                sb.append("预计返现为").append(c.toPlainString()).append("元").append("\r\n");
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
        TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
        try {
            if (originalWord.contains("https")) {
                //用链接的方式找
                String httpContent = HttpUtils.sendGet(getUrlByWord(originalWord));
                String itemId = getItemId(httpContent);
                String itemName = getNameById(itemId);
                if (itemName == null) {
                    itemName = getName(httpContent);
                }
                TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
                req.setAdzoneId(adzoneId);
                req.setQ(itemName);
                TbkDgMaterialOptionalResponse response = client.execute(req);
                List<TbkDgMaterialOptionalResponse.MapData> res = response.getResultList();
                for (TbkDgMaterialOptionalResponse.MapData mapData : res) {
                    if (itemId.equals("" + mapData.getItemId())) {
                        return mapData;
                    }
                }
            }
        } catch (Exception e) {
            log.error("查找商品失败", e);
        }
        try {
            //用第三方工具找
            String word = findWord(originalWord, keyWord);
            String url = "https://api.taokouling.com/tkl/tkljm?apikey=bwZnYFuzyD&tkl=" + word;
            String httpContent = HttpUtils.sendGet(url);
            JSONObject jsonObject = JSON.parseObject(httpContent);
            String itemId = getItemIdBy3rd(jsonObject.getString("url"));
            String itemName = getNameById(itemId);
            if (itemName == null) {
                itemName = jsonObject.getString("content");
            }
            if (itemId == null) {
                return null;
            }
            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            req.setAdzoneId(adzoneId);
            req.setQ(itemName);
            TbkDgMaterialOptionalResponse response = client.execute(req);
            List<TbkDgMaterialOptionalResponse.MapData> res = response.getResultList();
            for (TbkDgMaterialOptionalResponse.MapData mapData : res) {
                if (itemId.equals("" + mapData.getItemId())) {
                    return mapData;
                }
            }
        } catch (Exception e) {
            log.error("第三方工具查找商品失败", e);
        }
        return null;
    }

    private static String findWord(String originalWord, String k) {
        String res = "";
        boolean begin = false;
        for (int i = 0; i < originalWord.length(); i++) {
            char c = originalWord.charAt(i);
            if (k.contains("" + c)) {
                res += c;
                begin = !begin;
                continue;
            }
            if (begin) {
                res += c;
            }
        }
        return res;
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


//    public static void main(String[] args) {
////        System.out.println(findWord("6￥xomvcmTK0Fx￥回Tá0宔或點几url链 https://m.tb.cn/h.4Vi2ibl 至浏览er【阻门器门阻门挡顶门防盗家用女生安全防狼独居必备神器酒店防开门】",
////                "￥₤$¢"));
////        String word = findWord("6￥xomvcmTK0Fx￥回Tá0宔或點几url链 https://m.tb.cn/h.4Vi2ibl 至浏览er【阻门器门阻门挡顶门防盗家用女生安全防狼独居必备神器酒店防开门】",
////                "￥₤$¢");
////        String url = "https://api.taokouling.com/tkl/tkljm?apikey=bwZnYFuzyD&tkl=" + word;
////        String httpContent = HttpUtils.sendGet(url);
////        System.out.println(httpContent);
////        System.out.println(getItemIdBy3rd("https:\\/\\/a.m.tmall.com\\/i625281406528.htm?ut_sk=1.XKiuzxiIcxADAEWERcZzQVsL_12615387_1605790808772.Copy.tm_detail&suid=2909C163-DADF-4D65-B870-1D2BA78A6B52&sourceType=item&un=43bede4083e45fbfe8f55b3b63c56d8b&share_crt_v=1&spm=a2159r.13376460.0.0&sp_tk=R1hiSGNtVEtUbmc=&bxsign=tcdcS5djTDasOyOHncs0A-xCo-I5Vlmo8y5FtzDIwiJwrqKvZAuAUVuu3VDT9xZxHHRb6GGy2F9SCxIEGa3GMzXctId4TaZQotcBC8u1Y2w2x0"));
////        System.out.println(getItemIdBy3rd("https:\\/\\/item.taobao.com\\/item.htm?ut_sk=1.Wqpl06GQzL8DAADw52mhSySs_21380790_1605591641315.TaoPassword-Weixin.1&id=625281406528&sourceType=item&price=20.9-139&origin_price=41.8-278&suid=8B0A6538-9231-4D8F-831E-5D2537E0172E&shareUniqueId=5474070043&un=74b1d4c8442119419f2917200ca32a93&share_crt_v=1&spm=a2159r.13376460.0.0&sp_tk=blkzQ2NPcVhhd00=&bxsign=tcd8iPvTqy_5E9vyPGI5MC0uP2Whjc0jqjS8TgV7FZ9LmB71UCl0CPrBZUzwnuF5-t_0eICquyJrVG_9wf1TQGIedvz4RMk24kFdPKf7xumors"));
//        System.out.println(getItemIdBy3rd("https:\\\\/\\\\/a.m.taobao.com\\\\/i588044568562.htm?price=69.8&sourceType=item&detailSharePosition=interactBar&sourceType=item&suid=0125edf4-fe9f-4970-a608-e937e6d537b9&shareUniqueId=5513363141&ut_sk=1.Xzqa%2Bh96%2B20DAAVTPm1O48xE_21646297_1605711111406.TaoPassword-WeiXin.1&un=2b2ab22d313f074cd9a2d33a0bf32337&share_crt_v=1&spm=a2159r.13376460.0.0&sp_tk=Z3hlbmNtMXRSNm4=&bxsign=tcdYHnJNRN_XB3OH0X9-T9j43pYv1dkl2wwYrcH7oJlVQigVK8BvhipCjIDm5DCyGClyBBQ_NrqoCHqhmudT_nUsljyD53F-rcivo7LGEHrzlo"));
//    }

}
