package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.AdminModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.validation.AdminLogin;
import com.hemeiyue.entity.validation.AdminRegister;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.ValidateHandler;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SchoolService schoolService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResultBean login(@RequestBody @Validated(AdminLogin.class) Admin admin,BindingResult result, 
			HttpServletRequest request) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		return adminService.login(admin, request);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public ResultBean register(@RequestBody @Validated(AdminRegister.class) AdminModel adminModel,
			BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			System.out.println("2");
			return ValidateHandler.validate(result);
		}
		//构造admin
		Admin admin = new Admin();
		admin.setAccount(adminModel.getAccount());
		admin.setPassword(adminModel.getPassword());
		admin.setAdminName(adminModel.getAdminName());
		admin.setEmail(adminModel.getEmail());
		admin.setPhone(adminModel.getPhone());
		
		ResultBean r = schoolService.findSchool(adminModel.getSchool());;
		if(!r.isResult()) return r;
		//插入学校
		Schools newSchool = new Schools(adminModel.getSchool(), 1);
		//获取学校id
		int schoolId = schoolService.insert(newSchool);
		newSchool.setId(schoolId);
		admin.setSchool(newSchool);
		r = adminService.insert(admin, request);
		//设置学校所属管理员
		newSchool.setOwner(admin);
		schoolService.update(newSchool);
		adminService.updateAdmin(admin);
		return r;
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
	public ResultBean validationAccount(@RequestBody AdminModel admin) {
		return adminService.validationAccount(admin.getAccount());
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
