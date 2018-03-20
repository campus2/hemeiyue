package com.hemeiyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.eumn.Auth;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	/**
	 * 请求测试
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.operator)
	public List<ResultBean> index(HttpServletResponse response) {
		List<ResultBean> list = new ArrayList<>();
		list.add(new ResultBean(true,"success"));
		list.add(new ResultBean(true,"添加成功"));
		return list;
	}
}
