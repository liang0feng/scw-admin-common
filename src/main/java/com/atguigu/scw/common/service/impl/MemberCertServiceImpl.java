package com.atguigu.scw.common.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.scw.common.bean.TMemberCert;
import com.atguigu.scw.common.mapper.TMemberCertMapper;
import com.atguigu.scw.common.service.MemberCertService;

@Service
public class MemberCertServiceImpl implements MemberCertService{

	@Autowired
	TMemberCertMapper memberCertMapper;

	@Override
	public void saveCerts(List<TMemberCert> memberCert) {
		try {
			for (TMemberCert tMemberCert : memberCert) {
				memberCertMapper.insertSelective(tMemberCert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
