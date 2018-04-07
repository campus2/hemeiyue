package com.hemeiyue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.PageBean;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.eumn.Auth;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.ResponseUtil;
import com.hemeiyue.util.ValidateHandler;

@Controller
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 添加学校
	 * @param school
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	
	public String save(@RequestBody(required=false) Schools school, 
			HttpServletRequest request,HttpServletResponse response) {
		ResultBean result;
		if(school.getId() == null) {
			//设置学校的创建者
			Admin currentAdmin = (Admin)(request.getSession().getAttribute("currentAdmin"));
			school.setOwner(currentAdmin);
//			result = schoolService.insert(school);
			result = new ResultBean();
		}else {
			result = schoolService.update(school);
		}
		//以json格式写回前端
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 根据id删除学校
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	@AuthLoginAnnotation(checkAuth=Auth.operator)
	public String delete(@RequestParam("id")String id, HttpServletResponse response) {
		ResultBean result = schoolService.deleteById(Integer.parseInt(id));
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 返回学校集合
	 * @param rows 每页的记录数
	 * @param page 当前页
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	@AuthLoginAnnotation(checkAuth=Auth.operator)
	public String list(@RequestParam(value="rows",required=false)String rows,
				@RequestParam(value="page",required=false)String page, HttpServletResponse response) {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		//构造查询条件
		Map<String, Object> map = new HashMap<>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		String result = schoolService.find(map);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 请求测试
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public List<ResultBean> index(HttpServletRequest request,HttpServletResponse response) {
		List<ResultBean> list = new ArrayList<>();
		String localPath= request.getSession().getServletContext().getRealPath("/");
		list.add(new ResultBean(true,localPath));
		list.add(new ResultBean(true,"添加成功"));
		list.add(new ResultBean(true, null));
		return list;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public ResultBean login(@Valid Admin admin,BindingResult result, 
				HttpServletRequest request,Model model) {
		if(result.hasErrors()) {
			return ValidateHandler.validate(result);
		}
		ResultBean bean= adminService.login(admin, request);
		System.out.println("1111");
		return bean;
	}
	
	@RequestMapping("/validSchool")
	@ResponseBody
	public ResultBean validSchool(@RequestBody Schools school) {
		return schoolService.findSchool(school.getSchool());
	}
	
	@RequestMapping("/selectSchool")
	@ResponseBody
	public ResultBean selectSchool(@RequestParam("school")String school) {
		return schoolService.selectSchool(school);
	}
	
	@RequestMapping("/handleSchool")
	@ResponseBody
	public ResultBean handleSchool(@RequestParam("school")String school,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) {
			return new ResultBean(false);
		}
		return schoolService.insertHandleSchool(school, user);
	}
}
