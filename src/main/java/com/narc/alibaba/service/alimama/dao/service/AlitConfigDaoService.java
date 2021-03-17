package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitConfigMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitConfigMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitConfig;

import javax.annotation.Resource;

import com.narc.alibaba.service.alimama.entity.AlitConfigExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitConfigDaoService {
    @Resource
    private AlitConfigMapper alitConfigMapper;

    @Resource
    private AlitConfigMapperExtend alitConfigMapperExtend;

    public String getValueByKey(String key) {
        AlitConfigExample example = new AlitConfigExample();
        example.createCriteria().andConfigKeyEqualTo(key);
        List<AlitConfig> list = alitConfigMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list) || list.get(0) == null) {
            return "";
        }
        return list.get(0).getConfigValue();
    }

    public AlitConfig getByKey(String key){
        AlitConfigExample example = new AlitConfigExample();
        example.createCriteria().andConfigKeyEqualTo(key);
        List<AlitConfig> list = alitConfigMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list) || list.get(0) == null) {
            return null;
        }
        return list.get(0);
    }

}