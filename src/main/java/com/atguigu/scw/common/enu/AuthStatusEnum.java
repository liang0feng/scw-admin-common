package com.atguigu.scw.common.enu;

public enum AuthStatusEnum {
	
	UNAUTH("0","未认证"),AUTHING("1","认证中"),AUTHED("2","已认证");

	private String code,msg;
	private AuthStatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
