package com.atguigu.scw.common.service;

import org.apache.commons.mail.EmailException;

public interface SystemService {

	void sendResetPasswordEmail(String email,String token) throws EmailException;

	void sendCustomEmail(String subject, String content, String to) throws EmailException;

}
