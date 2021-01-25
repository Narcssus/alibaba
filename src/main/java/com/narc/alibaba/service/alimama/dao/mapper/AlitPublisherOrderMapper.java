package com.narc.alibaba.service.alimama.dao.mapper;

import com.narc.alibaba.service.alimama.entity.AlitPublisherOrder;
import com.narc.alibaba.service.alimama.entity.AlitPublisherOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-25
*/
@Mapper
@SuppressWarnings("unused")
public interface AlitPublisherOrderMapper {
    long countByExample(AlitPublisherOrderExample example);

    int deleteByExample(AlitPublisherOrderExample example);

    int deleteByPrimaryKey(String tradeId);

    int insert(AlitPublisherOrder record);

    int insertSelective(AlitPublisherOrder record);

    List<AlitPublisherOrder> selectByExample(AlitPublisherOrderExample example);

    AlitPublisherOrder selectByPrimaryKey(String tradeId);

    int updateByExampleSelective(@Param("record") AlitPublisherOrder record, @Param("example") AlitPublisherOrderExample example);

    int updateByExample(@Param("record") AlitPublisherOrder record, @Param("example") AlitPublisherOrderExample example);

    int updateByPrimaryKeySelective(AlitPublisherOrder record);

    int updateByPrimaryKey(AlitPublisherOrder record);

    int insertBatch(List<AlitPublisherOrder> records);
}