package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.WechatPicture;
import com.hemeiyue.service.WechatPictureService;

@Controller
@RequestMapping("/file")
public class WechatPictureController {
	
	@Autowired
	private WechatPictureService wechatPictureService;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean upload(MultipartFile file,HttpServletRequest request) {
		System.out.println("test");
		WechatPicture wechatPicture = new WechatPicture();
		wechatPicture.setFile(file);
		return wechatPictureService.insert(wechatPicture,request);
	}
	
	@RequestMapping(value="/pictureList")
	@ResponseBody
	public ResultBean pictureList() {
		return wechatPictureService.findAll();
	}
	
	@RequestMapping(value="/updateHrefUrl")
	@ResponseBody
	public ResultBean updateHrefUrl(@RequestParam("id")Integer id, @RequestParam("hrefUrl")String hrefUrl) {
		WechatPicture wechatPicture = new WechatPicture();
		wechatPicture.setId(id);
		wechatPicture.setHrefUrl(hrefUrl);
		return wechatPictureService.updateHrefUrl(wechatPicture);
	}
	
	@RequestMapping(value="/updateStatus")
	@ResponseBody
	public ResultBean updateStatus(@RequestParam("id") Integer id) {
		return wechatPictureService.updateStatus(id);
	}
	
	@RequestMapping("/getIndex")
	@ResponseBody
	/**
	 * 把小程序首页所有的信息返回
	 * @return
	 */
	public ResultBean getIndex() {
		return wechatPictureService.getIndex();
	}
}
