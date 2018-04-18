package com.atguigu.scw.common.exception;

public class LoginAcctExitException extends Exception {

	public LoginAcctExitException() {
		super("用户名已经存在！");
	}

	
}
