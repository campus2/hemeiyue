package com.hemeiyue.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ActivityModel;
import com.hemeiyue.common.ActivityUserModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.CodeUtil;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.ExcelUtil;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.PicUtil;
import com.hemeiyue.util.WX_Util;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/down")
	public ResponseEntity<byte[]> downActivityExcel(Integer id) throws Exception {
		//文件目录
		String localPath = this.getClass().getClassLoader().getResource("")
			.getPath().replaceAll("/WEB-INF/classes/", "/assets/excel/");  
		//若没有这个目录则新建一个
		File dir = new File(localPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//文件名字，文件内容
		File excelFile = ExcelUtil.createActivityExcel(localPath+"hemeiyue.xls", 
				(List<ActivityUserModel>) activityService.getUsersByActivity(new Activity(id)).getList());
		byte[] body = null;
	    InputStream is = new FileInputStream(excelFile);
	    body = new byte[is.available()];
	    is.read(body);
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=" + excelFile.getName());
	    headers.add("Content-Type", "application/octet-stream");
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    is.close();
	    return entity;
	}
	
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
	@AuthLoginAnnotation(checkLogin=true)
	public String upload(MultipartFile file,HttpServletRequest request) {
		System.out.println("pic upload test");
		ResultBean result = new PicUtil().uploadPic(file,"/assets/activityImage/");
		if(result.isResult()) {
//			request.getSession().setAttribute("imageUrl",result.getMessage());
			request.getServletContext().setAttribute("imageUrl",result.getMessage());
			//返回图片URL
			return result.getMessage();
		}
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
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean addActivity(@RequestBody ActivityModel model,
			HttpServletRequest request) {
		//当前管理员和学校
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin admin = (Admin)request.getServletContext().getAttribute("currentAdmin");
//		String imageUrl = (Admin)request.getSession().getAttribute("imageUrl");
		String imageUrl = (String) request.getServletContext().getAttribute("imageUrl");

		Activity activity = new Activity();
		if(imageUrl != null) {
			//更新活动
			activity.setImageUrl(imageUrl);
//			request.getSession().removeAttribute("imageUrl");
			request.getServletContext().removeAttribute("imageUrl");
		}
		activity.setOwner(admin);
		activity.setSchool(admin.getSchool());
		activity.setTitle(model.getTitle());
		activity.setContent(model.getContent());
		activity.setNumber(model.getNumber());
		activity.setAddress(model.getAddress());
		activity.setDate(DateUtil.date2stamp(model.getDate()));
		activity.setTime(DateUtil.time2stamp(model.getTime()));
		activity.setStatus(0);
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
	@AuthLoginAnnotation(checkLogin=true)
	public String activityList(HttpServletRequest request) {
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
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
	@AuthLoginAnnotation(checkLogin=true)
	public ResultList getAll(@RequestParam("activityId")int activityId) {
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
