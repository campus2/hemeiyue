package com.hemeiyue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.ResponseUtil;

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
	public String login(Admin admin,
				HttpServletRequest request,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		if(adminService.login(admin, request)) {
			result.setResult(true);
			result.setMessage("登录成功");
			
		}else {
			result.setResult(false);
			result.setMessage("登录失败，用户名或密码错误");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 添加管理员
	 * @param admin
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Admin admin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		adminService.add(admin);
		result.setResult(true);
		result.setMessage("添加成功，请前往审核");
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/modify")
	public String modify(Admin admin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		if(adminService.update(admin)) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
			result.setMessage("修改失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 返回管理员列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String AdminList(HttpServletRequest request,HttpServletResponse response) {
		ResultList result = new ResultList();
		List<Admin> list = adminService.find(adminService.current(request));
		if(list != null) {
			result.setResult(true);
			result.setMessage("管理员列表");
		}else {
			result.setResult(false);
			result.setMessage("列表为空");
		}
		result.setList(list);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除管理员
	 * @param admin
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Admin admin,HttpServletResponse response) {
		ResultBean result = new ResultBean();
		if(adminService.delete(admin)) {
			result.setResult(true);
			result.setMessage("删除成功");
		}else {
			result.setResult(false);
			result.setMessage("删除失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
