package com.atguigu.scw.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.scw.common.bean.TTag;
import com.atguigu.scw.common.mapper.TTagMapper;
import com.atguigu.scw.common.service.ProjectTagService;


@Service
public class ProjectTagServiceImpl implements ProjectTagService {

	@Autowired
	TTagMapper tagMapper;
	
	@Override
	public List<TTag> getAllTag() {
		return tagMapper.selectByExample(null);
	}

}
