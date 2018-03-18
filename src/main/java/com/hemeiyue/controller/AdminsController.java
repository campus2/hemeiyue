package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.ResponseUtil;
import com.hemeiyue.util.ValidateHandler;

@Controller
@RequestMapping("/admin")
public class AdminsController {
	@Autowired
	private AdminService adminService;
	
	/**
	 * 管理员登录
	 * @param account
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@Valid Admin admin,BindingResult result, 
				HttpServletRequest request,HttpServletResponse response,Model model) {
		if(ValidateHandler.validate(result, model)) {
			return null;
		}
		ResponseUtil.write(response, adminService.login(admin, request));
		return null;
	}
	
	
}
