package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.CodeUtil;
import com.hemeiyue.util.JSONUtil;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	/**
	 * 用于添加活动
	 * @param activity
	 * @param request
	 * @return
	 */
	@RequestMapping("/addActivity")
	@ResponseBody
	public ResultBean addActivity(@RequestBody Activity activity,
			HttpServletRequest request) {
		//当前管理员和学校
		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Schools school = admin.getSchool();
		activity.setOwner(admin);
		activity.setSchool(school);
		
		//添加
		return activityService.insertActivity(activity);
	}
	
	/**
	 * 返回指定学校的活动信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityList")
	@ResponseBody
	public String activityList(HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		String list = activityService.findActivity(school);
		if(list==null ||list.equals("")) return JSONUtil.transform(new ResultBean(false, "暂无活动信息"));
		
		return list;
	}
	
	/**
	 * 返回指定活动的二维码
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/getQrCode")
	public String getQrCode(
			HttpServletResponse response) {
		CodeUtil.createQRImg(response, "https://www.baidu.com/","附加信息");
		return null;
	}
	
	/**
	 * 返回指定活动的报名用户
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAll(@RequestParam("activityId")int activityId) {
		return activityService.getUsersByActivity(new Activity(activityId));
	}
	
	@RequestMapping("/deleteActivity")
	@ResponseBody
	public ResultBean deleteActivity(@RequestParam("activityId")int activityId) {
		return activityService.deleteById(activityId);
	}
}
