package com.hemeiyue.controller.WXcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ApplyData;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultUserInfoNull;
import com.hemeiyue.common.UsersModel;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.BookingService;
import com.hemeiyue.service.NotificationService;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.service.WechatPictureService;
import com.hemeiyue.util.ResponseUtil;

@RequestMapping("/WXuser")
@Controller
public class WX_UserController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private WechatPictureService wechatPictureService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping("/login")
	@ResponseBody
	/**
	 * 小程序用户登录
	 * @param code		小程序临时登录凭证
	 * @param request
	 * @return
	 */
	public ResultBean login(@RequestParam("code")String code,HttpServletRequest request) {
		System.out.println(code);
		return userService.login(code,request);
	} 
	
	/**
	 * 把用户预定信息返回来，包括课室预定和活动预定，过期的不用返回来
	 * @param request
	 * @return
	 */
	@RequestMapping("/reserve")
	@ResponseBody
	public ResultBean reserve(HttpServletRequest request){
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.reserve(user.getId());
		}
		return new ResultBean(false,"没有登录");
	}
	
	/**
	 * 把用户预定信息返回来，包括课室预定和活动预定，过期的也要返回
	 * @param request
	 * @return
	 */
	@RequestMapping("/reserveHistory")
	@ResponseBody
	public ResultBean reserveHistory(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			return userService.reserveHistory(user.getId());
		}
		return new ResultBean(false);
	}
	
	/**
	 * 返回个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/personalInfo")
	public String personalInfo(HttpServletRequest request,HttpServletResponse response) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user != null) {
			ResponseUtil.write(response,userService.selectPersonalInfo(user.getId()));
			return null;
		}
		ResponseUtil.write(response, new ResultUserInfoNull(false, true));
		return null;
	}
	
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/modifyPersonalInfo")
	@ResponseBody
	public ResultBean modifyPersonalInfo(UsersModel user,HttpServletRequest request) {
		Users currentUser = (Users) request.getSession().getAttribute("user");
		if(currentUser == null || currentUser.getId() == null) {
			return new ResultBean(false, "user未登录");
		}
		user.setId(currentUser.getId());
		return userService.updatePersonalInfo(user,request);
	}
	
	/**
	 * 返回学校所有的教室及个人信息,用于申请教室
	 * @param request
	 * @return
	 */
	@RequestMapping("/getApplyInfo")
	@ResponseBody
	public ResultBean getApplyInfo(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		return userService.getApplyInfo(user);
	}
	
	@RequestMapping("/getIndex")
	@ResponseBody
	/**
	 * 把小程序首页所有的信息返回
	 * @return
	 */
	public ResultBean getIndex(HttpServletResponse response) {
		return wechatPictureService.getIndex();
	}
	
	/**
	 * 返回该学校所有通告
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNotification")
	@ResponseBody
	public ResultBean getNotification(HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		if(school == null || school.getId() == 0) {
			return new ResultBean(false, "未选择学校");
		}
		return notificationService.findAll(school.getId());
	}
	
	/**
	 * 根据预定的id取消该预定，校验该id的预定是否已经过期
	 * @param id
	 * @return
	 */
	@RequestMapping("/cancelReserve")
	@ResponseBody
	public String cancelReserve(int id) {
		return bookingService.updateCancelReserve(id);
	}
	
	/**
	 * 提交用户申请表（课室申请）
	 * @param application
	 * @return
	 */
	@RequestMapping("/handleRoomApply")
	@ResponseBody
	public ResultBean handleRoomApply(@RequestBody ApplyData application,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		Schools school = (Schools) request.getSession().getAttribute("school");
		if(user == null) {
			return new ResultBean(false,"找不到user");
		}
		if(school == null) {
			school = user.getSchool();
			if(school == null) {
				return new ResultBean(false,"找不到学校");
			}
		}
		return bookingService.insertRoomApply(application, user, school);
	}
	
	@RequestMapping("/isLogin")
	@ResponseBody
	public ResultBean isLogin(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) return new ResultBean(true);
		return new ResultBean(false);
	}
}
