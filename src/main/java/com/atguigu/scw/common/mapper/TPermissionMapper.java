package com.atguigu.scw.common.mapper;

import com.atguigu.scw.common.bean.TPermission;
import com.atguigu.scw.common.bean.TPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TPermissionMapper {
    long countByExample(TPermissionExample example);

    int deleteByExample(TPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    List<TPermission> selectByExample(TPermissionExample example);

    TPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByExample(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    //根据用户id查出这个用户的所有权限
	List<TPermission> getUserPermissions(Integer id);
}