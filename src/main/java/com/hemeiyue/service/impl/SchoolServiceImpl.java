package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.SchoolModel;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.APIUtil;
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
	public int insert(Schools school) {
		return schoolMapper.insert(school);
//		ResultBean result = new ResultBean();
//		if( > 0) {
//			result.setResult(true);
//			result.setMessage("添加成功");
//		}else {
//			result.setResult(false);
//		}
//		return result;
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

	@Override
	public ResultBean findSchool(String school) {
		System.out.println("3");
		Map<String, Object> map = new HashMap<>();
		//调用远程接口校验学校是否合法
		map.put("name", school);
		List<SchoolModel> modelList = JSONUtil.transToModels(APIUtil.API("schoolAPI", map), new TypeReference<List<SchoolModel>>(){});
		boolean flag = false;
		for (SchoolModel schoolModel : modelList) {
			if(schoolModel.getName().equals(school)) {
				flag = true;
				break;
			}
		}
		if(!flag) return new ResultBean(false, "请输入合法的学校名称");
		//校验学校是否已经被注册
//		map.put("school", school);
		int count = schoolMapper.findSchool(school);
		System.out.println(count);
		if(count!=0) {
			return new ResultBean(false,"该学校已被注册");
		}
		System.out.println("w");
		return new ResultBean(true,"该学校可注册");
	}

	@Override
	public ResultList selectSchool(String school) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
