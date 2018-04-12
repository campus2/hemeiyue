package com.hemeiyue.controller.WXcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.service.SchoolService;

@RequestMapping("/WXschool")
@Controller
public class WX_SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * 模糊查找学校
	 * @param school	学校名
	 * @return
	 */
	@RequestMapping("/selectSchool")
	@ResponseBody
	public ResultBean selectSchool(@RequestParam("school")String school,HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("openId"));
		System.out.println(request.getSession().getAttribute("session_key"));
		return schoolService.selectSchool(school);
	}
	
	/**
	 * 通过学校名查询改学校是否存在（该学校已注册），如果存在，把openid，学校等信息放进数据库，并把学校名返回。
	 * @param school	学校名
	 * @param request
	 * @return
	 */
	@RequestMapping("/handleSchool")
	@ResponseBody
	public ResultBean handleSchool(@RequestParam("school")String school,HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute("openId");
		System.out.println(openId);
		return schoolService.insertHandleSchool(school,openId);
	}

}
