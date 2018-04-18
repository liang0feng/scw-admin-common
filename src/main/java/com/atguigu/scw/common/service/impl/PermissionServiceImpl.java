package com.atguigu.scw.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.scw.common.bean.TPermission;
import com.atguigu.scw.common.mapper.TPermissionMapper;
import com.atguigu.scw.common.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	TPermissionMapper permissionMapper;
	
	@Override
	public List<TPermission> getUserPermissions(Integer id) {
		// TODO Auto-generated method stub
		return permissionMapper.getUserPermissions(id);
	}

	/**
	 * 查询所有Permission
	 */
	@Override
	public List<TPermission> getAllPermissions() {
		
		return permissionMapper.selectByExample(null);
	}

	/**
	 * 添加Permission
	 */
	@Override
	public void addPermission(TPermission permission) {
		permissionMapper.insertSelective(permission);
	}

	/**
	 * 修改permission
	 */
	@Override
	public void updataPermission(TPermission permission) {
		permissionMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public void deletePermission(Integer id) {
		permissionMapper.deleteByPrimaryKey(id);
	}

}
