package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;

@Controller
@RequestMapping("/adminManage")
public class AdminManageController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 返回所有的学校/课室总数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllSchoolsOrRooms")
	public String getAllSchoolsOrRooms(HttpServletRequest request) {
		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getStatus() == -1) {
			return adminService.getAllSchools();
		}else if(currentAdmin.getStatus() == 0) {
			return adminService.getAllRooms(currentAdmin.getSchool());
		}
		return null;
	}
	
	/**
	 * 返回所有的申请总数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllApply")
	public String getAllApply(HttpServletRequest request) {
		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getStatus() == 1) {
			return adminService.getAllApply(currentAdmin.getSchool());
		}else if(currentAdmin.getStatus() == -1) {
			return adminService.getAllApply(null);
		}
		return null;
	}
	
	@RequestMapping("/getUserCount")
	public String getUserCount(HttpServletRequest request) {
		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getStatus() == 1) {
			return adminService.getUserCount(currentAdmin.getSchool());
		}else if(currentAdmin.getStatus() == -1) {
			return adminService.getUserCount(null);
		}
		return null;
	}
	
	/**
	 * 返回当前学校总管理员的子管理员信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminManageList")
	@ResponseBody
	public String adminList(HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		return adminService.findByAdmin(admin);
	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyAdmin")
	@ResponseBody
	public ResultBean modifyAdmin(@RequestBody Admin admin,
			HttpServletRequest request) {
		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		ResultBean result = new ResultBean();
		if(currentAdmin.getParentId()<0 || currentAdmin.getAccount().equals(admin.getAccount())) {
			result = adminService.updateAdmin(admin);
		}else {
			result.setResult(false);
			result.setMessage("没有权限修改");
		}
		return result;
	}
	
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public ResultBean deleteAdmin(@RequestParam("id")String id,
			HttpServletRequest request) {
		Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getParentId() > 0) return new ResultBean(false, "删除失败");
		
		return adminService.deleteById(Integer.parseInt(id));
	}
}
