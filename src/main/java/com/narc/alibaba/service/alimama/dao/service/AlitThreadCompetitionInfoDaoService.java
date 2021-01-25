package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitThreadCompetitionInfoMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitThreadCompetitionInfoMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitThreadCompetitionInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitThreadCompetitionInfoDaoService {
    @Resource
    private AlitThreadCompetitionInfoMapper alitThreadCompetitionInfoMapper;

    @Resource
    private AlitThreadCompetitionInfoMapperExtend alitThreadCompetitionInfoMapperExtend;

    public AlitThreadCompetitionInfo getCompetitionInfo(int threadId) {
        return alitThreadCompetitionInfoMapper.selectByPrimaryKey(threadId);
    }

    public void updateCompetitionInfo(AlitThreadCompetitionInfo info) {
        alitThreadCompetitionInfoMapper.updateByPrimaryKeySelective(info);
    }


}