package com.narc.alibaba.service.alimama.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.service.AlimamaService;
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

    private String tranShareWord(String originalWord) {
        if (!originalWord.contains("【") || !originalWord.contains("】")) {
            return "不支持此淘口令";
        }
        try {
            String name = originalWord.substring(originalWord.indexOf('【') + 1, originalWord.lastIndexOf('】'));
            TaobaoClient client = new DefaultTaobaoClient(apiUrl, appKey, appSecret);
            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            req.setAdzoneId(adzoneId);
            req.setQ(name);
            TbkDgMaterialOptionalResponse response = client.execute(req);
            List<TbkDgMaterialOptionalResponse.MapData> res = response.getResultList();
            if (CollectionUtils.isEmpty(res)) {
                return "没有找到该商品";
            }
            TbkDgMaterialOptionalResponse.MapData res1 = res.get(0);
            String itemUrl = res1.getCouponShareUrl();
            if (StringUtils.isEmpty(itemUrl)) {
                itemUrl = res1.getUrl();
            }
            itemUrl = "https:" + itemUrl;
            log.info("itemUrl=" + itemUrl);
            TbkTpwdCreateRequest req2 = new TbkTpwdCreateRequest();
            req2.setText(req.getQ());
            req2.setUrl(itemUrl);
            TbkTpwdCreateResponse response2 = client.execute(req2);
            TbkTpwdCreateResponse.MapData res2 = response2.getData();
            String model = res2.getModel();
            String rate = res1.getCommissionRate();
            StringBuilder sb = new StringBuilder();
            sb.append(model).append("\r\n");
            if (!StringUtils.isEmpty(rate)) {
                BigDecimal a = new BigDecimal(rate);
                a = a.multiply(new BigDecimal(0.9));
                BigDecimal b = a.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                sb.append("==================").append("\r\n");
                sb.append("返现率为").append(b.toPlainString()).append("%").append("\r\n");
                BigDecimal c = new BigDecimal(res1.getReservePrice());
                c = c.multiply(b).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
                sb.append("==================").append("\r\n");
                sb.append("预计返现为").append(c.toPlainString()).append("元");
            }
            return sb.toString();
        } catch (Exception e) {
            return "系统失败";
        }
    }


}
