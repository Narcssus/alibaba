package com.narc.alibaba.service.alimama.dao.mapper;

import com.narc.alibaba.service.alimama.entity.AlitThreadCompetitionInfo;
import com.narc.alibaba.service.alimama.entity.AlitThreadCompetitionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-25
*/
@Mapper
@SuppressWarnings("unused")
public interface AlitThreadCompetitionInfoMapper {
    long countByExample(AlitThreadCompetitionInfoExample example);

    int deleteByExample(AlitThreadCompetitionInfoExample example);

    int deleteByPrimaryKey(Integer threadId);

    int insert(AlitThreadCompetitionInfo record);

    int insertSelective(AlitThreadCompetitionInfo record);

    List<AlitThreadCompetitionInfo> selectByExample(AlitThreadCompetitionInfoExample example);

    AlitThreadCompetitionInfo selectByPrimaryKey(Integer threadId);

    int updateByExampleSelective(@Param("record") AlitThreadCompetitionInfo record, @Param("example") AlitThreadCompetitionInfoExample example);

    int updateByExample(@Param("record") AlitThreadCompetitionInfo record, @Param("example") AlitThreadCompetitionInfoExample example);

    int updateByPrimaryKeySelective(AlitThreadCompetitionInfo record);

    int updateByPrimaryKey(AlitThreadCompetitionInfo record);

    int insertBatch(List<AlitThreadCompetitionInfo> records);
}