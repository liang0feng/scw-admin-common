package com.atguigu.scw.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.scw.common.bean.TRole;
import com.atguigu.scw.common.bean.TRoleExample;
import com.atguigu.scw.common.exception.RoleIdExistException;
import com.atguigu.scw.common.mapper.TRoleMapper;
import com.atguigu.scw.common.mapper.TUserRoleMapper;
import com.atguigu.scw.common.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	TRoleMapper rolemapper;
	@Autowired
	TUserRoleMapper userRoleMapper;

	
	/**
	 * 新增角色
	 */
	@Override
	public void addRole(String roleName) {
		TRole role = new TRole();
		role.setName(roleName);
		rolemapper.insertSelective(role);
	}

	/**
	 * 获取所有角色
	 */
	@Override
	public List<TRole> getAllRole(String condition) {
		
		if (condition==null) {
			condition="";
		}
		//创建条件
		TRoleExample example = new TRoleExample();
		example.createCriteria().andNameLike("%"+condition+"%");
		//返回 条件结果
		return rolemapper.selectByExample(example);
	}

	/**
	 * 根据角色id删除角色
	 * @throws RoleIdExistException 
	 */
	@Override
	public void deleteRole(Integer rid) throws RoleIdExistException {
		try {
			rolemapper.deleteByPrimaryKey(rid);
		} catch (Exception e) {
			throw new RoleIdExistException();
		}
		
	}


}
