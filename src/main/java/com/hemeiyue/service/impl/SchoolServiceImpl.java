package com.hemeiyue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.SchoolService;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolsMapper schoolMapper;
	
	@Override
	public Schools selectById(int id) {
		return schoolMapper.selectById(id);
	}

	@Override
	public int deleteById(int id) {
		return schoolMapper.deleteById(id);
	}

	@Override
	public ResultBean insert(Schools school) {
		ResultBean result = new ResultBean();
		if(schoolMapper.insert(school) > 0) {
			result.setResult(true);
			result.setMessage("添加成功");
		}else {
			result.setResult(false);
		}
		return result;
	}

	@Override
	public int update(Schools school) {
		return schoolMapper.update(school);
	}

}
