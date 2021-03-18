package com.narc.alibaba.service.tencent.service;

import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.tencent.service.defaultImpl.DefaultFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 腾讯接口服务类
 *
 * @author : Narcssus
 * @date : 2020/11/25 21:46
 */
@Primary
@FeignClient(name = "sms", fallback = DefaultFallbackImpl.class)
public interface TencentService {

    /**
     * 发送管理员通知短信
     *
     */
    @RequestMapping(value = "/sms/sendAlimamaAuthCodeNotice", method = RequestMethod.POST)
    JSONObject sendSms(@RequestParam("param") String param);





}
