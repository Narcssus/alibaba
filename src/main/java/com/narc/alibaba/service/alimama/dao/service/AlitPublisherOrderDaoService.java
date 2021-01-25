package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitPublisherOrderMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitPublisherOrderMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitPublisherOrder;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitPublisherOrderDaoService {
    @Resource
    private AlitPublisherOrderMapper alitPublisherOrderMapper;

    @Resource
    private AlitPublisherOrderMapperExtend alitPublisherOrderMapperExtend;
}