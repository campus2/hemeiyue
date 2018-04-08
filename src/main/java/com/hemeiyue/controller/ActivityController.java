package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.entity.WechatPicture;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.CodeUtil;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.WX_Util;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UsersMapper usersMapper;
	/**
	 * 上传活动图片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean upload(MultipartFile file,HttpServletRequest request) {
		System.out.println("test");
		WechatPicture wechatPicture = new WechatPicture();
		wechatPicture.setFile(file);
		return null;
	}
	
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
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin admin = (Admin)request.getServletContext().getAttribute("currentAdmin");
//		String imageUrl = (Admin)request.getSession().getAttribute("imageUrl");
		String imageUrl = (String) request.getServletContext().getAttribute("imageUrl");

		if(imageUrl != null) {
			//更新活动
//			activity = (Activity) request.getSession().getAttribute("activity");
			activity = (Activity) request.getServletContext().getAttribute("activity");
			activity.setImageUrl(imageUrl);
			return activityService.updateActivity(activity);
		}else {
			Schools school = admin.getSchool();
			activity.setOwner(admin);
			activity.setSchool(school);
			//添加
			ResultBean result = activityService.insertActivity(activity);
			//添加activity临时缓存，用于更新image
//			request.getSession().setAttribute("activity",activity);
			request.getServletContext().setAttribute("activity",activity);
			
			return result;
		}
		
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
	public String getQrCode(@RequestParam("activityId")int activityId,
			HttpServletResponse response) {
		Activity activity = activityService.selectById(activityId);
		CodeUtil.createQRImg(response, "https://www.baidu.com/?id="+activityId,activity.getTitle());
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
	
	/**
	 * 返回指定活动的详细信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getActivityDetail")
	@ResponseBody
	public ResultBean  getActivityDetail(int id,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) {
			return new ResultBean(false);
		}
		return activityService.findById(id,user);
	}
	
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
	public String weChatScan(@RequestParam("code")String code,@RequestParam("activityId")int activityId,
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
			return JSONUtil.transform(new ResultBean(false));
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
}
