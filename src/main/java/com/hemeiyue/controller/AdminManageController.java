package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.AdminModel;
import com.hemeiyue.common.AdminModifyModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.eumn.Auth;
import com.hemeiyue.service.AdminService;

@Controller
@RequestMapping("/adminManage")
public class AdminManageController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 返回四大记录数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCount")
	@ResponseBody
	public ResultBean getCount(HttpServletRequest request) {
//		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin currentAdmin = (Admin)request.getServletContext().getAttribute("currentAdmin");
		if(currentAdmin.getParentId() <= 0) {
			return adminService.getCount(currentAdmin);
		}
		return new ResultBean(false, "页面不存在");
	}
	
	
	/**
	 * 返回所有的申请总数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllApply")
	public String getAllApply(HttpServletRequest request) {
//		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
//		if(currentAdmin.getStatus() == 1) {
//			return adminService.getAllApply(currentAdmin.getSchool());
//		}else if(currentAdmin.getStatus() == -1) {
//			return adminService.getAllApply(null);
//		}
		return null;
	}
	
	/**
	 * 返回用户总数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserCount")
	public String getUserCount(HttpServletRequest request) {
//		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
//		if(currentAdmin.getStatus() == 1) {
//			return adminService.getUserCount(currentAdmin.getSchool());
//		}else if(currentAdmin.getStatus() == -1) {
//			return adminService.getUserCount(null);
//		}
		return null;
	}
	
	/**
	 * 返回当前学校总管理员的子管理员信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminManageList")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.priAccount)
	public ResultBean adminList(HttpServletRequest request) {
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin admin = (Admin)request.getServletContext().getAttribute("currentAdmin");
		return adminService.findByAdmin(admin);
	}
	
	/**
	 * 增加子管理员
	 * @param admin
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.priAccount)
	public ResultBean addAdmin(@RequestBody AdminModel adminModel, HttpServletRequest request) {
		//构造admin
		Admin admin = new Admin();
		admin.setAccount(adminModel.getAccount());
		admin.setPassword(adminModel.getPassword());
		admin.setAdminName(adminModel.getAdminName());
		admin.setEmail(adminModel.getEmail());
		admin.setPhone(adminModel.getPhone());
		admin.setSchool((Schools)request.getServletContext().getAttribute("school"));
		return adminService.insert(admin,request);
	}
	
	/**
	 * 增加子账号时检验账号
	 * @param account
	 * @return
	 */
	@RequestMapping("/validationAccount")
	@ResponseBody
	public ResultBean validationAccount(String account) {
		return adminService.validationAccount(account);
	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyAdmin")
	@ResponseBody
	public ResultBean modifyAdmin(@RequestBody AdminModifyModel am,HttpServletRequest request) {
//		if(error.hasErrors()) {
//			return ValidateHandler.validate(error);
//		}
		System.out.println(am.getAdminName());
		if(am.getPassword()!=null && !am.getPassword().trim().isEmpty()) {
			String password = am.getPassword();
			if(password.length()<5 || password.length()>11) {
				return new ResultBean(false, "密码必须5-11位");
			}
		}
//		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin currentAdmin = (Admin)request.getServletContext().getAttribute("currentAdmin");
		ResultBean result = new ResultBean();
		Admin admin = new Admin();
		admin.setId(am.getId());
		admin.setPassword(am.getPassword());
		admin.setAdminName(am.getAdminName());
		admin.setEmail(am.getEmail());
		admin.setPhone(am.getPhone());
		if(currentAdmin.getParentId()<=0 || currentAdmin.getAccount().equals(admin.getAccount())) {
			result = adminService.updateAdmin(admin);
		}else {
			result.setResult(false);
			result.setMessage("没有权限修改");
		}
		return result;
	}
	
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	@AuthLoginAnnotation(checkAuth=Auth.priAccount)
	public ResultBean deleteAdmin(@RequestParam("id")String id,
			HttpServletRequest request) {
//		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin currentAdmin = (Admin)request.getServletContext().getAttribute("currentAdmin");
		if(currentAdmin.getParentId() > 0) return new ResultBean(false, "删除失败");
		
		return adminService.deleteById(Integer.parseInt(id));
	}
	
}
