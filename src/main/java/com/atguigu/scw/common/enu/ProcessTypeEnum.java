package com.atguigu.scw.common.enu;

public enum ProcessTypeEnum {

	REAL_AUTH("0","实名认证"),PROJECT_CHECK("1","项目审核");
	
	private String code,status;
	private ProcessTypeEnum(String code,String status) {
		this.code = code;
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}
	
}
