package com.narc.alibaba.service.alimama.dao.mapper;

import com.narc.alibaba.service.alimama.entity.AlitMessageLog;
import com.narc.alibaba.service.alimama.entity.AlitMessageLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-27
*/
@Mapper
@SuppressWarnings("unused")
public interface AlitMessageLogMapper {
    long countByExample(AlitMessageLogExample example);

    int deleteByExample(AlitMessageLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AlitMessageLog record);

    int insertSelective(AlitMessageLog record);

    List<AlitMessageLog> selectByExample(AlitMessageLogExample example);

    AlitMessageLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AlitMessageLog record, @Param("example") AlitMessageLogExample example);

    int updateByExample(@Param("record") AlitMessageLog record, @Param("example") AlitMessageLogExample example);

    int updateByPrimaryKeySelective(AlitMessageLog record);

    int updateByPrimaryKey(AlitMessageLog record);

    int insertBatch(List<AlitMessageLog> records);
}