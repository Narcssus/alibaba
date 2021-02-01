package com.narc.alibaba.service.alimama.dao.service;

import com.narc.alibaba.service.alimama.dao.mapper.AlitPublisherOrderMapper;
import com.narc.alibaba.service.alimama.dao.mapper.AlitPublisherOrderMapperExtend;
import com.narc.alibaba.service.alimama.entity.AlitPublisherOrder;

import javax.annotation.Resource;

import com.narc.alibaba.service.alimama.entity.AlitPublisherOrderExample;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-01-25
 */
@Service
public class AlitPublisherOrderDaoService {
    @Resource
    private AlitPublisherOrderMapper alitPublisherOrderMapper;

    @Resource
    private AlitPublisherOrderMapperExtend alitPublisherOrderMapperExtend;

    public boolean isExistKey(String tradeId) {
        return alitPublisherOrderMapper.selectByPrimaryKey(tradeId) != null;
    }

    public void updateByPrimaryKeySelective(AlitPublisherOrder order) {
        alitPublisherOrderMapper.updateByPrimaryKeySelective(order);
    }

    public void insertOne(AlitPublisherOrder order) {
        alitPublisherOrderMapper.insertSelective(order);
    }

    public List<AlitPublisherOrder> getAllNewOrders() {
        AlitPublisherOrderExample example = new AlitPublisherOrderExample();
        example.createCriteria().andSenderIdIsNull();
        return alitPublisherOrderMapper.selectByExample(example);
    }

    public List<AlitPublisherOrder> getAllUnFinishedOrders() {
        AlitPublisherOrderExample example = new AlitPublisherOrderExample();
        example.createCriteria().andTkStatusEqualTo("12");
        return alitPublisherOrderMapper.selectByExample(example);
    }

    /**
     * 查询所有未返现订单
     *
     * @return
     */
    public List<AlitPublisherOrder> getAllUndoOrders() {
        AlitPublisherOrderExample example = new AlitPublisherOrderExample();
        example.createCriteria().andIsDoneIn(Arrays.asList("0", "1"));
        return alitPublisherOrderMapper.selectByExample(example);
    }

    public void updateUndoOrders(List<String> ids) {
        AlitPublisherOrderExample example = new AlitPublisherOrderExample();
        example.createCriteria().andTradeIdIn(ids);
        AlitPublisherOrder update = new AlitPublisherOrder();
        update.setIsDone("1");
        alitPublisherOrderMapper.updateByExampleSelective(update, example);
    }

    public void endAllDoingOrders(){
        AlitPublisherOrderExample example = new AlitPublisherOrderExample();
        example.createCriteria().andIsDoneEqualTo("1");
        AlitPublisherOrder update = new AlitPublisherOrder();
        update.setIsDone("2");
        alitPublisherOrderMapper.updateByExampleSelective(update, example);
    }


}