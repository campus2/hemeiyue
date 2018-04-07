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
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	
	
	@RequestMapping("/login")
	@ResponseBody
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
		return null;
	}
}
