package com.hemeiyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.validation.AdminLogin;
import com.hemeiyue.entity.validation.AdminRegister;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.ValidateHandler;
import com.hemeiyue.eumn.Auth;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/login")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth={Auth.priAccount,Auth.operator})
	public ResultBean login(@Validated(AdminLogin.class) Admin admin,BindingResult result, 
			HttpServletRequest request) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		return adminService.login(admin, request);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public ResultBean register(@Validated(AdminRegister.class) Admin admin,Integer schoolId,
			BindingResult result, HttpServletRequest request) {
		System.out.println("注册成功");
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		
		Schools school = new Schools();
		school.setId(schoolId);
		admin.setSchool(school);
		return adminService.insert(admin, request);
	}
	
	@RequestMapping("/tenantApplyList")
	@ResponseBody
	public ResultBean tenantApplyList( HttpServletRequest request) {
		return adminService.tenantApplyList(request);
	}
	
	@RequestMapping("/tenantApply")
	@ResponseBody
	public ResultBean tenantApply(Integer id) {
		return adminService.tenantApply(id);
	}
	
	@RequestMapping("/validationAccount")
	@ResponseBody
	public ResultBean validationAccount(String account) {
		return adminService.validationAccount(account);
	}
	
	@RequestMapping("/deleteTenant")
	@ResponseBody
	public ResultBean deleteTenant(Integer id) {
		return adminService.deleteTenant(id);
	}
	
	@RequestMapping("/tenantMangerList")
	@ResponseBody
	public ResultBean tenantMangerList(HttpServletRequest request) {
		Admin admin = new Admin();
		admin.setId(0);
		admin.setParentId(-1);
		request.getSession().setAttribute("currentAdmin", admin);
		return adminService.tenantMangerList(request);
	}
	
	@RequestMapping("/suspendedTenant")
	@ResponseBody
	public ResultBean suspendedTenant(Integer id) {
		return adminService.suspendedTenant(id);
	}
	
	@RequestMapping("/restoreTenant")
	@ResponseBody
	public ResultBean restoreTenant(Integer id) {
		return adminService.restoreTenant(id);
	}
	
	@RequestMapping("/validatePassword")
	@ResponseBody
	public ResultBean validatePassword(Admin admin) {
		return adminService.findPassword(admin);
	}
}
