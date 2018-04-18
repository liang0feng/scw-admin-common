package com.atguigu.scw.common.exception;

public class LoginAcctException extends RuntimeException {

	public LoginAcctException() {
		super("用户名或密码错误！");
	}
}
