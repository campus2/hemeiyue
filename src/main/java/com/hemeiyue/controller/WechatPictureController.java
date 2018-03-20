package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.WechatPicture;
import com.hemeiyue.service.WechatPictureService;

@Controller
@RequestMapping("/file")
public class WechatPictureController {
	
	@Autowired
	private WechatPictureService wechatPictureService;
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean load(WechatPicture wechatPicture,HttpServletRequest request) {
		System.out.println("test");
		return wechatPictureService.insert(wechatPicture,request);
	}
	
	@RequestMapping(value="/pictureList")
	@ResponseBody
	public ResultBean pictureList() {
		return wechatPictureService.findAll();
	}
	
	@RequestMapping(value="/updateHrefUrl")
	@ResponseBody
	public ResultBean updateHrefUrl(WechatPicture wechatPicture) {
		return wechatPictureService.updateHrefUrl(wechatPicture);
	}
	
	@RequestMapping(value="/updateStatus")
	@ResponseBody
	public ResultBean updateStatus(Integer id) {
		return wechatPictureService.updateStatus(id);
	}
}
