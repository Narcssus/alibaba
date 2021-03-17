package com.narc.alibaba.thread;

import com.narc.alibaba.service.alimama.dao.service.AlitConfigDaoService;
import com.narc.alibaba.service.alimama.entity.AlitConfig;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.service.tencent.service.TencentService;
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
public class AuthCodeThread extends CommonTask {

    @Autowired
    private TencentService tencentService;
    @Autowired
    private AlitConfigDaoService alitConfigDaoService;

    private static final int THREAD_ID = 4;
    private static final int INTERVAL_TIME = 60 * 14;

    /**
     * 执行完成后12小时查询一次
     */

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 12)
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        Date now = new Date();
        log.debug("执行管理员配置检查定时任务，{}", DateUtils.convertDateToStr(now, DateUtils.FORMAT_19));
        AlitConfig config = alitConfigDaoService.getByKey("taobao_user_id");
        if (config == null || DateUtils.getDateDiffDay(now, config.getModifiedDatetime()) >= 3) {
            tencentService.sendSms("");
        }

    }

}
