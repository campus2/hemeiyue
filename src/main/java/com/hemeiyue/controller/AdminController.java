package com.hemeiyue.controller;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.AdminModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.validation.AdminLogin;
import com.hemeiyue.entity.validation.AdminRegister;
import com.hemeiyue.eumn.Auth;
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
	
	/**
	 * 租户登录
	 * @param admin
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultBean login(@RequestBody @Validated(AdminLogin.class) Admin admin,BindingResult result, 
			HttpServletRequest request) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		return adminService.login(admin, request);
	}
	
	@RequestMapping("/testlogin")
	@ResponseBody
	public ResultBean testlogin(@RequestBody @Validated(AdminLogin.class) Admin admin,
			HttpServletRequest request) {
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
	@Transactional(rollbackFor=Exception.class)
	public ResultBean register(@RequestBody @Validated(AdminRegister.class) AdminModel adminModel,
			BindingResult result, HttpServletRequest request) throws ParseException {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		//构造admin
		Admin admin = new Admin();
		admin.setAccount(adminModel.getAccount());
		admin.setPassword(adminModel.getPassword());
		admin.setAdminName(adminModel.getAdminName());
		admin.setEmail(adminModel.getEmail());
		admin.setPhone(adminModel.getPhone());
		
		ResultBean r = schoolService.findSchool(adminModel.getSchool());
		if(!r.isResult()) return r;
		//插入学校
		Schools newSchool = new Schools(adminModel.getSchool(), 0);
		//获取学校id
		schoolService.insert(newSchool);
		admin.setSchool(newSchool);
		r = adminService.insert(admin, request);

		return r;
	}
	
	/**
	 * 租户申请列表
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
	 * 同意注册申请
	 * @param id
	 * @return
	 */
	@RequestMapping("/tenantApply")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.operator)
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
	@AuthLoginAnnotation(checkAuth=Auth.operator)
	public ResultBean deleteTenant(Integer id) {
		return adminService.deleteTenant(id);
	}
	
	/**
	 * 返回正常的租户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/tenantMangerList")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.operator)
	public ResultBean tenantMangerList(HttpServletRequest request) {
		ServletContext context = request.getServletContext();
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		//部署时候改为上边代码
		Admin admin = (Admin)context.getAttribute("currentAdmin");
		return adminService.tenantMangerList(admin);
	}
	
	/**
	 * 禁用租户
	 * @param id
	 * @return
	 */
	@RequestMapping("/suspendedTenant")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth= {Auth.operator,Auth.priAccount})
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
	@AuthLoginAnnotation(checkAuth= {Auth.operator,Auth.priAccount})
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
	public ResultBean validatePassword(@RequestParam("password")String password, HttpServletRequest request) {
//		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		Admin currentAdmin = (Admin) request.getServletContext().getAttribute("currentAdmin");
		System.out.println(password);
		Admin admin = new Admin();
		admin.setId(currentAdmin.getId());
		admin.setPassword(password);
		return adminService.findPassword(admin);
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public ResultBean logout(HttpServletRequest request) {
//		request.getSession().removeAttribute("currentAdmin");
//		request.getSession().removeAttribute("school");
		
		request.getServletContext().removeAttribute("currentAdmin");
		request.getServletContext().removeAttribute("school");
		return new ResultBean(false, "退出成功");
		
	}
}
