package com.atguigu.scw.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atguigu.scw.common.bean.TCert;
import com.atguigu.scw.common.bean.TMember;
import com.atguigu.scw.common.bean.TMemberExample;
import com.atguigu.scw.common.bean.TMemberExample.Criteria;
import com.atguigu.scw.common.bean.TMemberProcess;
import com.atguigu.scw.common.enu.AuthStatusEnum;
import com.atguigu.scw.common.enu.ProcessTypeEnum;
import com.atguigu.scw.common.exception.InsertException;
import com.atguigu.scw.common.exception.LoginAcctException;
import com.atguigu.scw.common.exception.LoginAcctExitException;
import com.atguigu.scw.common.exception.LoginEmailExitException;
import com.atguigu.scw.common.exception.TokenInvaildException;
import com.atguigu.scw.common.mapper.TCertMapper;
import com.atguigu.scw.common.mapper.TMemberMapper;
import com.atguigu.scw.common.mapper.TMemberProcessMapper;
import com.atguigu.scw.common.service.MemberService;
import com.atguigu.scw.common.service.SystemService;
import com.atguigu.scw.common.util.AppCacheUtils;
import com.atguigu.scw.common.util.DateUtils;
import com.atguigu.scw.common.util.MD5Utils;
import com.atguigu.scw.common.util.MyAppConstant;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	TMemberMapper memberMapper;
	@Autowired
	SystemService systemService;
	@Autowired
	TCertMapper certMapper;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TMemberProcessMapper memberProcessMapper;

	/**
	 * 登录业务
	 * @return 
	 */
	@Override
	public  TMember login(String loginacct, String password) {
		TMemberExample example = new TMemberExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginacctEqualTo(loginacct).andUserpswdEqualTo(MD5Utils.digestPassword(password));

		List<TMember> list = memberMapper.selectByExample(example);
		if (list.size() != 0) {
			// 登录成功
			return list.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 注册业务
	 * 
	 * @throws LoginAcctExitException
	 * @throws LoginEmailExitException
	 * @throws InsertException
	 */
	@Override
	public void regist(TMember member)
			throws LoginAcctExitException, LoginEmailExitException, InsertException {
		// TODO Auto-generated method stub
				// 添加新用户
				TMemberExample example = new TMemberExample();
				example.createCriteria().andLoginacctEqualTo(member.getLoginacct());
				// 1、检查用户名
				long l = memberMapper.countByExample(example);
				if (l != 0) {
					throw new LoginAcctExitException();
				}

				// 2、检查邮箱
				TMemberExample example2 = new TMemberExample();
				example2.createCriteria().andEmailEqualTo(member.getEmail());
				long m = memberMapper.countByExample(example2);
				if (m != 0) {
					throw new LoginEmailExitException();
				}

				// 3、注册
				// 数据初始化
				member.setUserpswd(MD5Utils.digestPassword(member.getUserpswd()));
				member.setUsername(member.getLoginacct());
				// 实名状态 0：未实名认证 1：认证中 2:已认证
				member.setAuthstatus(AuthStatusEnum.UNAUTH.getCode());
				member.setUsertype("1");
				memberMapper.insertSelective(member);
	}

	@Override
	public void sendResetEmail(String email) throws EmailException {
		// 生成唯一标识符
		String token = UUID.randomUUID().toString().replace("-", "");
		// 将唯一标识符保存到缓存中：待用户提交新密码调用 修改方法时 验证使用
		AppCacheUtils.put(token, email);
		// 调用系统服务，发送重置密码邮件
		try {
			systemService.sendResetPasswordEmail(email, token);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	// 修改密码
	@Override
	public void updateMember(String token, String newpassword) throws TokenInvaildException {
		// TODO Auto-generated method stub
		// 1、根据令牌找到用户【校验令牌】
		String email = (String) AppCacheUtils.get(token);
		if (StringUtils.isEmpty(email)) {
			// 令牌非法
			throw new TokenInvaildException();
		}

		// 2、令牌合法，就修改密码
		TMember member = new TMember();
		member.setUserpswd(MD5Utils.digestPassword(newpassword));
		// update from t_member set userpswd=? where email=?
		TMemberExample example = new TMemberExample();
		example.createCriteria().andEmailEqualTo(email);
		memberMapper.updateByExampleSelective(member, example);

		// 3、删除令牌
		AppCacheUtils.remove(token);
	}

	//实名认证保存基本信息
	@Override
	public void saveBaseINfo(TMember member) {
		try {
			memberMapper.updateByPrimaryKeySelective(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 根据账户类型查询需要上传的资质
	 */
	@Override
	public List<TCert> getCertByAccttype(String accttype) {
		return certMapper.getCertByAccttype(accttype);
	}

	/**
	 * 启动实名认证流程
	 * @memberid : 启动流程的会员id
	 */
	@Override
	public void startAuthProcess(Integer memberid) {

		// 启动流程并保存工单信息
		// 查出实名审核流程最新版 real_auth
		ProcessDefinition pd = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(MyAppConstant.AUTH_PROCS_KEY)
				.latestVersion().singleResult();

		// 按照id确认查询一下用户的信息;
		TMember member = memberMapper.selectByPrimaryKey(memberid);

		// 启动流程的时候将用户的基本信息一起传入
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", member.getId());
		map.put("userEmail", member.getEmail());
		map.put("username", member.getUsername());

		// 将流程实例的id和用户id保存到数据库
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), map);
		//修改tmember表中，实名认证状态字段authstatus:1表示实名审核中
		TMember tMember = new TMember();
		tMember.setAuthstatus("1");
		tMember.setId(memberid);
		memberMapper.updateByPrimaryKeySelective(tMember);
		
		TMemberProcess process = new TMemberProcess();
		process.setMemberid(member.getId());
		process.setProsinstid(processInstance.getId());
		process.setCreatetime(DateUtils.formatCurrentDate());
		process.setProtype(ProcessTypeEnum.REAL_AUTH.getCode());
		memberProcessMapper.insertSelective(process);
	}

}
