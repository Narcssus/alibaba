package com.narc.alibaba.thread;

import com.narc.alibaba.service.alimama.dao.service.AlitThreadCompetitionInfoDaoService;
import com.narc.alibaba.service.alimama.entity.AlitThreadCompetitionInfo;
import com.narc.alibaba.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/1/25 17:50
 */
@Slf4j
public class CommonTask {
    @Autowired
    private AlitThreadCompetitionInfoDaoService alitThreadCompetitionInfoDaoService;

    @Value("${local-ip}")
    private String ip;


    public void runTask(int threadId, int intervalTime) {
        try {
            AlitThreadCompetitionInfo competitionInfo =
                    alitThreadCompetitionInfoDaoService.getCompetitionInfo(threadId);
            if (competitionInfo == null) {
                return;
            }
            Date now = new Date();
            if (!ip.equals(competitionInfo.getNowIp()) &&
                    now.compareTo(DateUtils.addMinutes(competitionInfo.getLastRunTime(), intervalTime)) > 0) {
                //竞争锁
                competitionInfo.setNowIp(ip);
                alitThreadCompetitionInfoDaoService.updateCompetitionInfo(competitionInfo);
            }
            competitionInfo = alitThreadCompetitionInfoDaoService.getCompetitionInfo(threadId);
            if (ip.equals(competitionInfo.getNowIp())) {
                doTask();
                competitionInfo.setLastRunTime(new Date());
                alitThreadCompetitionInfoDaoService.updateCompetitionInfo(competitionInfo);
            }
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
        }
    }

    public void doTask() {
        //do something
    }


}
