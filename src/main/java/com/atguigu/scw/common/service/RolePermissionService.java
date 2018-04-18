package com.atguigu.scw.common.service;

import java.util.List;

import com.atguigu.scw.common.bean.TPermission;

public interface RolePermissionService {

	void updatePermission(Integer roleId, List<Integer> pIdList);

	List<TPermission> getRolePermission(Integer roleId);

	
}
