package com.narc.alibaba.service.alimama.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.HttpUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
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
                sb.append("预计返现为").append(c.toPlainString()).append("元");
            }
            return sb.toString();
        } catch (Exception e) {
            return "系统失败";
        }
    }

    private TbkDgMaterialOptionalResponse.MapData getItem(String originalWord) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
            if (originalWord.contains("https")) {
                //用链接的方式找
                String httpContent = HttpUtils.sendGet(getUrlByWord(originalWord));
                String itemName = getName(httpContent);
                String itemId = getItemId(httpContent);
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
            return null;
        }
        return null;
    }


}
