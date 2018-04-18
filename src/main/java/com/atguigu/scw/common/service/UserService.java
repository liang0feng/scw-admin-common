package com.atguigu.scw.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.alibaba.druid.stat.TableStat.Mode;
import com.atguigu.scw.common.bean.TRole;
import com.atguigu.scw.common.bean.TUser;

public interface UserService {

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	boolean regist(TUser user);

	TUser login(TUser user);

	//获取所有用户
	List<TUser> queryUserByCondition(String condition);

	TUser getUserById(Integer id);

	void updateUser(TUser user);

	void deleteUser(List<Integer> u_ids);

	void addUser(TUser user);

	List<TRole> getAssignedRole(Integer id);

	List<TRole> getUnassignedRole(Integer id);

	void addRoles(Integer userId, List<Integer> rids);

	void removeRoles(Integer userId, List<Integer> rids);

}
