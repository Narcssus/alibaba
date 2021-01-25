package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitMessageLogMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitMessageLogMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitMessageLog;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitMessageLogDaoService {
    @Resource
    private AlitMessageLogMapper alitMessageLogMapper;

    @Resource
    private AlitMessageLogMapperExtend alitMessageLogMapperExtend;
}