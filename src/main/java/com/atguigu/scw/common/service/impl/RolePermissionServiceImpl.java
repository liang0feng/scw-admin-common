package com.atguigu.scw.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.scw.common.bean.TPermission;
import com.atguigu.scw.common.bean.TRolePermissionExample;
import com.atguigu.scw.common.bean.TRolePermissionExample.Criteria;
import com.atguigu.scw.common.mapper.TRolePermissionMapper;
import com.atguigu.scw.common.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	TRolePermissionMapper rolePermissionMapper;

	@Transactional
	@Override
	public void updatePermission(Integer roleId, List<Integer> pIdList) {

		//更新角色权限
		//1.根据roleId删除角色所有权限
		TRolePermissionExample example = new TRolePermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		rolePermissionMapper.deleteByExample(example);
		
		//2.根据roleId，插入permissionId
		rolePermissionMapper.insertPermissionByRoleId(roleId,pIdList);
	}

	@Override
	public List<TPermission> getRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.getRolePermission(roleId);
	}
}
