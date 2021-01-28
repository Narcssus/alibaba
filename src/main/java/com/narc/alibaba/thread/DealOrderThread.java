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
 * 订单处理任务
 * @author : Narcssus
 * @date : 2021/1/25 16:16
 */
@Component
@EnableScheduling
@Slf4j
public class DealOrderThread extends CommonTask {

    @Autowired
    private AlimamaService alimamaService;

    private static final int THREAD_ID = 2;
    private static final int INTERVAL_TIME = 60;

    /**
     * 执行完成后5分钟查询一次
     */

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        Date now = new Date();
        log.debug("执行订单处理定时任务，{}", DateUtils.convertDateToStr(now, DateUtils.FORMAT_19));
        alimamaService.dealOrders();
    }

}
