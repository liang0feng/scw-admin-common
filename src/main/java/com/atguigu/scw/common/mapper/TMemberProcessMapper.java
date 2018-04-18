package com.atguigu.scw.common.mapper;

import com.atguigu.scw.common.bean.TMemberProcess;
import com.atguigu.scw.common.bean.TMemberProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberProcessMapper {
    long countByExample(TMemberProcessExample example);

    int deleteByExample(TMemberProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMemberProcess record);

    int insertSelective(TMemberProcess record);

    List<TMemberProcess> selectByExample(TMemberProcessExample example);

    TMemberProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMemberProcess record, @Param("example") TMemberProcessExample example);

    int updateByExample(@Param("record") TMemberProcess record, @Param("example") TMemberProcessExample example);

    int updateByPrimaryKeySelective(TMemberProcess record);

    int updateByPrimaryKey(TMemberProcess record);
}