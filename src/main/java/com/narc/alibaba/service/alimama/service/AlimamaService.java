package com.narc.alibaba.service.alimama.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface AlimamaService {

    /**
     *  淘口令转链
     * @return
     */
    JSONObject tranShareWord(JSONObject paramObject);

    void getOrders(Date startTime,Date endTime);
}
