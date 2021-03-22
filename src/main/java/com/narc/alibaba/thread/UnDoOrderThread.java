package com.narc.alibaba.thread;

import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.dao.service.AlitPublisherOrderDaoService;
import com.narc.alibaba.service.alimama.entity.AlitPublisherOrder;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.service.tencent.service.SmsService;
import com.narc.alibaba.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 未结算订单通知任务
 *
 * @author : Narcssus
 * @date : 2021/1/25 16:16
 */
@Component
@EnableScheduling
@Slf4j
public class UnDoOrderThread extends CommonTask {


    @Autowired
    private AlitPublisherOrderDaoService alitPublisherOrderDaoService;
    @Autowired
    private SmsService smsService;

    private static final int THREAD_ID = 5;
    private static final int INTERVAL_TIME = 60 * 47;

    /**
     * 每天16点执行一次
     */

    @Scheduled(cron = "0 0 16 * * ?")
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        Date now = new Date();
        log.debug("执行未结算订单通知任务，{}", DateUtils.convertDateToStr(now, DateUtils.FORMAT_19));
        List<AlitPublisherOrder> list = alitPublisherOrderDaoService.getAllUndoOrders();
        if (CollectionUtils.isEmpty(list)) {
            log.debug("执行未结算订单通知任务，无未结算订单");
        }
        JSONObject paramObj = new JSONObject();
        paramObj.put("num", list.size());
        smsService.sendAlimamaUndoOrderNotice(paramObj.toJSONString());
    }

}
