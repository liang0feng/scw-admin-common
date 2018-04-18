package com.atguigu.scw.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.atguigu.scw.common.bean.TRole;
import com.atguigu.scw.common.bean.TUser;
import com.atguigu.scw.common.bean.TUserExample;
import com.atguigu.scw.common.bean.TUserExample.Criteria;
import com.atguigu.scw.common.bean.TUserRoleExample;
import com.atguigu.scw.common.mapper.TRoleMapper;
import com.atguigu.scw.common.mapper.TUserMapper;
import com.atguigu.scw.common.mapper.TUserRoleMapper;
import com.atguigu.scw.common.service.UserService;
import com.atguigu.scw.common.util.DateUtils;
import com.atguigu.scw.common.util.MD5Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TUserMapper userMapper;
	@Autowired
	TRoleMapper roleMapper;
	@Autowired
	TUserRoleMapper userRoleMapper;

	@Override
	public boolean regist(TUser user) {
		// 页面提交的数据插入进来
		// 全字段插入；所有带Selective的都是有选择的动态判断；
		// 失败的另外一种情况就是出错了；用户名重复；
		// 初始化默认值：
		user.setUsername(user.getLoginacct());
		// 2017-12-15 22:10:10
		user.setCreatetime(DateUtils.formatCurrentDate());
		// 密码加密存储
		user.setUserpswd(MD5Utils.digestPassword(user.getUserpswd()));

		int i = userMapper.insertSelective(user);

		return i > 0;
	}

	@Override
	public TUser login(TUser user) {

		TUserExample example = new TUserExample();
		// 一个Criteria是一组 and 连接的条件
		// selecr * from t_user where loginacct=? and userpswd=?
		Criteria criteria = example.createCriteria();
		criteria.andLoginacctEqualTo(user.getLoginacct())
				.andUserpswdEqualTo(MD5Utils.digestPassword(user.getUserpswd()));

		// 返回一个集合
		List<TUser> list = userMapper.selectByExample(example);
		return list.size() == 1 ? list.get(0) : null;
	}

	// 修改前查询要修改对象
	@Override
	public TUser getUserById(Integer id) {

		return userMapper.selectByPrimaryKey(id);
	}

	// 修改
	@Override
	public void updateUser(TUser user) {
		// 根据主键修改
		userMapper.updateByPrimaryKeySelective(user);
	}

	// 删除用户
	@Override
	public void deleteUser(List<Integer> u_ids) {
		TUserExample example = new TUserExample();
		example.createCriteria().andIdIn(u_ids);

		userMapper.deleteByExample(example);

	}

	// 添加用户
	@Override
	public void addUser(TUser user) {
		String currentDate = DateUtils.formatCurrentDate();
		user.setCreatetime(currentDate);
		// 设置新增用户的初始密码为:admin
		user.setUserpswd(MD5Utils.digestPassword("admin"));
		userMapper.insertSelective(user);
	}

	// 按条件查询用户
	@Override
	public List<TUser> queryUserByCondition(String condition) {
		// 根据条件进行模糊查询
		TUserExample example = new TUserExample();
		Criteria criteria1 = example.createCriteria();
		Criteria criteria2 = example.createCriteria();
		Criteria criteria3 = example.createCriteria();

		criteria1.andLoginacctLike("%" + condition + "%");
		criteria2.andEmailLike("%" + condition + "%");
		criteria3.andUsernameLike("%" + condition + "%");

		example.or(criteria1);
		example.or(criteria2);
		example.or(criteria3);

		return userMapper.selectByExample(example);
	}

	// 根据用户id，查询返回该用户分配的角色role
	@Override
	public List<TRole> getAssignedRole(Integer id) {
		return roleMapper.getAssignedRole(id);
	}

	// 根据用户id，查询返回该用户未分配的角色role
	@Override
	public List<TRole> getUnassignedRole(Integer id) {
		return roleMapper.getUnassignedRole(id);
	}

	// 为某个用户添加角色
	@Override
	public void addRoles(Integer userId, List<Integer> rids) {
		userRoleMapper.addRoles(userId, rids);
	}

	
	//为某个用户移除角色
	/**
	 * userid：用户id
	 * rids：要移除的角色id集合
	 */
	@Override
	public void removeRoles(Integer userId, List<Integer> rids) {
		//创建查询条件：查询用户id为：userId 且角色id为rids集合中的，
		TUserRoleExample example = new TUserRoleExample();
		example.createCriteria().andUseridEqualTo(userId).andRoleidIn(rids);
		
		try {
			userRoleMapper.deleteByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
