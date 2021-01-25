package com.narc.alibaba.thread;

import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/1/25 16:16
 */
@Component
@EnableScheduling
@Slf4j
public class OrderThread {

    @Autowired
    private AlimamaService alimamaService;

    /**
     * 每十分钟查询一次
     */

    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void doFetchOrders() {
        try {
            Date now = new Date();
            log.info("执行订单查询定时任务，{}",DateUtils.convertDateToStr(now,DateUtils.FORMAT_19));
            alimamaService.getOrders(DateUtils.addMinutes(now, -15), now);
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
        }
    }

}
