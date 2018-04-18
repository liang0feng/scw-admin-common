package com.atguigu.scw.common.mapper;

import com.atguigu.scw.common.bean.TRole;
import com.atguigu.scw.common.bean.TRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleMapper {
    long countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    TRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

	List<TRole> getAssignedRoleById(Integer id);

	List<TRole> getUnassignedRoleById(Integer id);

	List<TRole> getAssignedRole(Integer id);

	List<TRole> getUnassignedRole(Integer id);
}