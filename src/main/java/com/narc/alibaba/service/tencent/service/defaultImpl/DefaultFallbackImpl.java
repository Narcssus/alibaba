package com.narc.alibaba.service.tencent.service.defaultImpl;

import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.tencent.service.TencentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Narcssus
 * @date : 2021/1/22 16:12
 */
@Component
@Slf4j
public class DefaultFallbackImpl implements TencentService {

    @Override
    public JSONObject sendSms(String param) {
        log.info("请求sendSms，降级处理");
        return new JSONObject();
    }
}
