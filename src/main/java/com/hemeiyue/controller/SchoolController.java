package com.hemeiyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	
	/**
	 * 添加学校
	 * @param school
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	public String add(@RequestBody(required=false) Schools school, HttpRequest request,HttpServletResponse response) {
		ResultBean result = schoolService.insert(school);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 请求测试
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response) {
		List<ResultBean> list = new ArrayList<>();
		list.add(new ResultBean(true,"success"));
		list.add(new ResultBean(true,"添加成功"));
		ResponseUtil.write(response, list);
		return null;
	}
}
