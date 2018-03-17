package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.JSONUtil;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolsMapper schoolMapper;
	
	@Override
	public Schools selectById(int id) {
		return schoolMapper.selectById(id);
	}

	@Override
	public ResultBean deleteById(int id) {
		ResultBean result = new ResultBean();
		
		if(schoolMapper.deleteById(id) > 0) {
			result.setResult(true);
			result.setMessage("删除成功");
		}else {
			result.setResult(false);
			result.setMessage("删除失败");
		}
		return result;
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
	public ResultBean update(Schools school) {
		ResultBean result = new ResultBean();
		if(schoolMapper.update(school) > 0) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
		}
		return result;
	}

	@Override
	public String find(Map<String, Object> map) {
		List<Schools> list = schoolMapper.find(map);
		Long total = schoolMapper.getTotal(map);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", total);
		return JSONUtil.transform(result);
	}
}
