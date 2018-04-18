package com.atguigu.scw.common.service.impl;

import java.util.UUID;

import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atguigu.scw.common.service.SystemService;
import com.atguigu.scw.common.util.AppCacheUtils;

@Service
public class SystemServiceImpl implements SystemService {
	
	@Value("${email.hostname}")
	String hostName;//发件箱所用服务器
	
	@Value("${email.subject}")
	String subject;//邮件主题
	
	@Value("${email.from}")
	String from;//发件邮箱
	
	@Value("${email.username}")
	String username;//发件箱地址
	
	@Value("${email.password}")
	String password;//发件箱密码
	/**
	 * 发送密码重置邮件业务
	 * @throws EmailException 
	 */
	@Override
	public void sendResetPasswordEmail(String ToEmail,String token) throws EmailException {
		HtmlEmail htmlEmail = new HtmlEmail();
		//邮件基本信息
		htmlEmail.addTo(ToEmail);
		htmlEmail.setFrom(from);
		htmlEmail.setHostName(hostName);
		htmlEmail.setSubject(subject);
		
		htmlEmail.setCharset("UTF-8");
		
		String content = "<h4></h4></br><a href='http://localhost:8081/scw/password.html?token="+token+"'>修改密码</a>";
		htmlEmail.setMsg(content + token);
		
		//权限:邮箱用户名和密码
		htmlEmail.setAuthentication(username, password);
		
		htmlEmail.send();
	}
	
	/**
	 *	 发送自定义邮件
	 * @param subject : 邮件主题
	 * @param content ： 邮件内容
	 * @param to      ： 目标邮箱地址
	 * @throws EmailException 
	 */
	@Override
	public void sendCustomEmail(String subject,String content,String to) throws EmailException{
		HtmlEmail htmlEmail = new HtmlEmail();
		//邮件基本信息
		htmlEmail.addTo(to);
		htmlEmail.setFrom(from);
		htmlEmail.setSubject(subject);
		htmlEmail.setCharset("UTF-8");
		htmlEmail.setMsg(content);
		
		//2.设置用户名密码，连接服务器，发送邮件
		htmlEmail.setAuthentication(username, password);
		htmlEmail.setHostName(hostName);
		htmlEmail.send();
		
	}

	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	
}
