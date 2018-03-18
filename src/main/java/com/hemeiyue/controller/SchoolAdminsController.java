package com.hemeiyue.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.SchoolAdmins;
import com.hemeiyue.service.SchoolAdminsService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/schoolAdmin")
public class SchoolAdminsController {
	
	@Autowired
	private SchoolAdminsService schoolAdminsService;
	
	@RequestMapping("/add")
	public String add(SchoolAdmins schoolAdmin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		try {
			schoolAdmin.setCdt(DateUtil.date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(schoolAdminsService.add(schoolAdmin)) {
			result.setResult(true);
			result.setMessage("学校管理员添加成功");
		}else {
			result.setResult(false);
			result.setMessage("学校管理员添加失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/modify")
	public String modify(SchoolAdmins schoolAdmin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		if(schoolAdminsService.modify(schoolAdmin)) {
			result.setResult(true);
			result.setMessage("学校管理员修改成功");
		}else {
			result.setResult(false);
			result.setMessage("学校管理员修改失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(SchoolAdmins schoolAdmin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		if(schoolAdminsService.delete(schoolAdmin)) {
			result.setResult(true);
			result.setMessage("学校管理员删除成功");
		}else {
			result.setResult(false);
			result.setMessage("学校管理员删除失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
