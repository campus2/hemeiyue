package com.hemeiyue.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	
	
	@RequestMapping("/login")
	public String login(@RequestBody Users user,HttpServletResponse response) {
		ResponseUtil.write(response, userService.login(user.getOpenId()));
		return null;
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestBody Users user,HttpServletResponse response) {
		ResponseUtil.write(response, userService.update(user));
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestBody Users user,HttpServletResponse response) {
		ResponseUtil.write(response, userService.delete(user));
		return null;
	}
}
