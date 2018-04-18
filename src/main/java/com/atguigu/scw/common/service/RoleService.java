package com.atguigu.scw.common.service;

import java.util.List;

import com.atguigu.scw.common.bean.TRole;
import com.atguigu.scw.common.exception.RoleIdExistException;

public interface RoleService {

	//获取所有角色
	List<TRole> getAllRole(String condition);

	void addRole(String roleName);

	void deleteRole(Integer rid) throws RoleIdExistException;


}
