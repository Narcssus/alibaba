package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitMessageLogMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitMessageLogMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitMessageLog;

import javax.annotation.Resource;

import com.narc.alibaba.service.alimama.entity.AlitMessageLogExample;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitMessageLogDaoService {
    @Resource
    private AlitMessageLogMapper alitMessageLogMapper;

    @Resource
    private AlitMessageLogMapperExtend alitMessageLogMapperExtend;

    public void insertSelective(AlitMessageLog alitMessageLog) {
        alitMessageLogMapper.insertSelective(alitMessageLog);
    }

    public List<AlitMessageLog> getLogs(String itemId, Date paidTime) {
        AlitMessageLogExample example = new AlitMessageLogExample();
        example.createCriteria().andItemIdEqualTo(itemId)
                .andCreatedDatetimeLessThanOrEqualTo(paidTime);
        example.setOrderByClause("CREATED_DATETIME DESC");
        return alitMessageLogMapper.selectByExample(example);
    }


}