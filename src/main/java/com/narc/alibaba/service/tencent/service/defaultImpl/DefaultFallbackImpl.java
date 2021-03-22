package com.narc.alibaba.service.tencent.service.defaultImpl;

import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.tencent.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Narcssus
 * @date : 2021/1/22 16:12
 */
@Component
@Slf4j
public class DefaultFallbackImpl implements SmsService {

    @Override
    public JSONObject sendAlimamaAuthCodeNotice(String param) {
        log.info("请求sendAlimamaAuthCodeNotice，降级处理");
        return new JSONObject();
    }

    @Override
    public JSONObject sendAlimamaUndoOrderNotice(String param) {
        log.info("请求sendAlimamaUndoOrderNotice，降级处理");
        return new JSONObject();
    }
}
