package com.atguigu.scw.common.service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.atguigu.scw.common.bean.TCert;
import com.atguigu.scw.common.bean.TMember;
import com.atguigu.scw.common.exception.InsertException;
import com.atguigu.scw.common.exception.LoginAcctExitException;
import com.atguigu.scw.common.exception.LoginEmailExitException;
import com.atguigu.scw.common.exception.TokenInvaildException;

public interface MemberService {

	TMember login(String loginacct, String password);

	void sendResetEmail(String email) throws EmailException;

	void updateMember(String token, String newPassword) throws TokenInvaildException;

	void regist(TMember member) throws LoginAcctExitException, LoginEmailExitException, InsertException;

	void saveBaseINfo(TMember member);

	List<TCert> getCertByAccttype(String accttype);

	void startAuthProcess(Integer id);


}
