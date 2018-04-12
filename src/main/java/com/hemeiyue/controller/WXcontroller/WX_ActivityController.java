package com.hemeiyue.controller.WXcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.WX_Util;

@Controller
@RequestMapping("/WXactivity")
public class WX_ActivityController {
		
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * 返回指定活动的详细信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getActivityDetail")
	@ResponseBody
	public ResultBean  getActivityDetail(@RequestParam("activityId")int activityId,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) {
			return new ResultBean(false,"user未登录");
		}
		return activityService.findById(activityId,user);
	}
	
	/**
	 * 返回学校对应的活动列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getArtivityList")
	@ResponseBody
	public String getArtivityList(HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		return activityService.findArtivityList(school);
	}
	
	/**
	 * 扫描二维码后台获取activityId，用于签到，
	 * 如过得到该活动还没有举办，还用户是否已经报名等。能扫码签到，签到时要把用户得签到时间记录在数据库里，
	 * 如果code为空，去session找是否为空。因为可以直接用微信扫二维码或者在小程序里面扫
	 * @param code
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("/weChatScan")
	@ResponseBody
	public ResultBean weChatScan(@RequestParam(value="code",required=false)String code,@RequestParam("activityId")int activityId,
				HttpServletRequest request) {
		Users user;
		//如果code为空，从session获取userId
		if(code == null || code.isEmpty()) {
			user = (Users) request.getSession().getAttribute("user");
			
		}else {			//code不为空，从腾讯客户端获取openId
			String openId = WX_Util.getOpenId(code).get("openId");
			user = usersMapper.selectByOpenId(openId);
		}
		if(user == null || user.getId() == 0) {
			return new ResultBean(false,"请先登录后再签到");
		}
		return activityService.updateWeChatScan(user, activityId);
	}
	
	/**
	 * 处理用户的活动申请
	 * @param activityId	活动Id
	 * @param request		
	 * @return
	 */
	@RequestMapping("/handleActivityApply")
	@ResponseBody
	public String handleActivityApply(Integer activityId,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null || user.getId() == 0) {
			return JSONUtil.transform(new ResultBean(false));
		}
		return activityService.insertActivityApply(activityId, user.getId());
	}
	
	@RequestMapping("/signUpActivity")
	@ResponseBody
	public ResultBean signUpActivity(@RequestParam("activityId") @NotEmpty Integer activityId,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start");
		Users user = (Users) request.getSession().getAttribute("user");
		Schools school = (Schools) request.getSession().getAttribute("school");
		System.out.println(user.getId());
		System.out.println(school.getId());
		return activityService.insertSignUpActivity(activityId, school, user);
	}
}
