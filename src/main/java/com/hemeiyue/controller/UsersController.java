package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.UsersModel;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	/**
	 * 小程序用户登录
	 * @param code		小程序临时登录凭证
	 * @param request
	 * @return
	 */
	public ResultBean login(@RequestParam("code")String code,HttpServletRequest request) {
		return userService.login(code,request);
	} 
	
	@RequestMapping("/modify")
	@ResponseBody
	public ResultBean modifyUser(@RequestBody Users user) {
		return userService.update(user);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestBody Users user,HttpServletResponse response) {
		ResponseUtil.write(response, userService.delete(user));
		return null;
	}
	
	@RequestMapping("/messageList")
	@ResponseBody
	public ResultBean messageList(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.userMessage(user.getId());
		}
		return new ResultBean(false);
	}
	
	@RequestMapping("/reserve")
	@ResponseBody
	public String reserve(HttpServletRequest request){
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.reserve(user.getId());
		}
		return JSONUtil.transform(new ResultBean(false));
	}
	
	@RequestMapping("/reserveHistory")
	@ResponseBody
	public String reserveHistory(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.reserveHistory(user.getId());
		}
		return JSONUtil.transform(new ResultBean(false));
	}
	
	@RequestMapping("/personalInfo")
	@ResponseBody
	public String personalInfo(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.selectPersonalInfo(user.getId());
		}
		return JSONUtil.transform(null);
	}
	
	@RequestMapping("/modifyPersonalInfo")
	@ResponseBody
	public ResultBean modifyPersonalInfo(UsersModel user) {
		return userService.updatePersonalInfo(user);
	}
	
	@RequestMapping("/getApplyInfo")
	@ResponseBody
	public String getApplyInfo(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		return userService.getApplyInfo(user);
	}
}
