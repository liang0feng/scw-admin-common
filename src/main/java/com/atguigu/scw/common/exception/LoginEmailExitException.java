package com.atguigu.scw.common.exception;

public class LoginEmailExitException extends Exception {

	public LoginEmailExitException() {
		super("邮箱已经存在");
	}

	
}
