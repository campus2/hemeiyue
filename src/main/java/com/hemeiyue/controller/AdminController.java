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
	
	/**
	 * 租户登录
	 * @param admin
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth={Auth.priAccount,Auth.operator})
	public ResultBean login(@Validated(AdminLogin.class) @RequestBody Admin admin,BindingResult result, 
			HttpServletRequest request) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		return adminService.login(admin, request);
	}
	
	/**
	 * 注册租户
	 * @param admin
	 * @param schoolId
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public ResultBean register(@Validated(AdminRegister.class) @RequestBody Admin admin,Integer schoolId,
			BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		
		Schools school = new Schools();
		school.setId(schoolId);
		admin.setSchool(school);
		return adminService.insert(admin, request);
	}
	
	/**
	 * 返回需要处理的新租户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/tenantApplyList")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth = Auth.operator)
	public ResultBean tenantApplyList( HttpServletRequest request) {
		return adminService.tenantApplyList(request);
	}
	
	/**
	 * 通过租户申请
	 * @param id
	 * @return
	 */
	@RequestMapping("/tenantApply")
	@ResponseBody
	public ResultBean tenantApply(@RequestParam("id") Integer id) {
		return adminService.tenantApply(id);
	}
	
	/**
	 * 注册输入账号时检验账号
	 * @param account
	 * @return
	 */
	@RequestMapping("/validationAccount")
	@ResponseBody
	public ResultBean validationAccount(@RequestParam("account") String account) {
		return adminService.validationAccount(account);
	}
	
	/**
	 * 删除租户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteTenant")
	@ResponseBody
	public ResultBean deleteTenant(@RequestParam("id") Integer id) {
		return adminService.deleteTenant(id);
	}
	
	/**
	 * 返回租户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/tenantMangerList")
	@ResponseBody
	public ResultBean tenantMangerList(HttpServletRequest request) {
		Admin admin = new Admin();
		admin.setId(0);
		admin.setParentId(-1);
		request.getSession().setAttribute("currentAdmin", admin);
		return adminService.tenantMangerList(request);
	}
	
	/**
	 * 禁用租户
	 * @param id
	 * @return
	 */
	@RequestMapping("/suspendedTenant")
	@ResponseBody
	public ResultBean suspendedTenant(@RequestParam("id") Integer id) {
		return adminService.suspendedTenant(id);
	}
	
	/**
	 * 恢复租户的使用权
	 * @param id
	 * @return
	 */
	@RequestMapping("/restoreTenant")
	@ResponseBody
	public ResultBean restoreTenant(@RequestParam("id") Integer id) {
		return adminService.restoreTenant(id);
	}
	
	/**
	 * 屏保验证密码
	 * @param admin
	 * @return
	 */
	@RequestMapping("/validatePassword")
	@ResponseBody
	public ResultBean validatePassword(@RequestBody Admin admin) {
		return adminService.findPassword(admin);
	}
}
