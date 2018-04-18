package com.atguigu.scw.common.service;

import java.util.List;

import com.atguigu.scw.common.bean.TPermission;

public interface PermissionService {

	//通过id获取permission
	List<TPermission> getUserPermissions(Integer id);

	//查询所有Permission
	List<TPermission> getAllPermissions();

	void addPermission(TPermission permission);

	void updataPermission(TPermission permission);

	void deletePermission(Integer id);

}
