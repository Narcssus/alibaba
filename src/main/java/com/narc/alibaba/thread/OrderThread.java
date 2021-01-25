package com.narc.alibaba.thread;

import com.narc.alibaba.service.alimama.dao.service.AlitThreadCompetitionInfoDaoService;
import com.narc.alibaba.service.alimama.entity.AlitThreadCompetitionInfo;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/1/25 16:16
 */
@Component
@EnableScheduling
@Slf4j
public class OrderThread extends CommonTask {

    @Autowired
    private AlimamaService alimamaService;

    public static final int THREAD_ID = 1;
    public static final int INTERVAL_TIME = 15;

    /**
     * 每5分钟查询一次
     */

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        Date now = new Date();
        log.info("执行订单查询定时任务，{}", DateUtils.convertDateToStr(now, DateUtils.FORMAT_19));
        alimamaService.getOrders(DateUtils.addMinutes(now, -15), now);
    }

}
