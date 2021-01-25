package com.narc.alibaba.service.alimama.dao.mapper;

import com.narc.alibaba.service.alimama.entity.AlitConfig;
import com.narc.alibaba.service.alimama.entity.AlitConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-25
*/
@Mapper
@SuppressWarnings("unused")
public interface AlitConfigMapper {
    long countByExample(AlitConfigExample example);

    int deleteByExample(AlitConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AlitConfig record);

    int insertSelective(AlitConfig record);

    List<AlitConfig> selectByExample(AlitConfigExample example);

    AlitConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AlitConfig record, @Param("example") AlitConfigExample example);

    int updateByExample(@Param("record") AlitConfig record, @Param("example") AlitConfigExample example);

    int updateByPrimaryKeySelective(AlitConfig record);

    int updateByPrimaryKey(AlitConfig record);

    int insertBatch(List<AlitConfig> records);
}