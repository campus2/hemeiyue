package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.service.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/write")
	@ResponseBody
	public ResultBean writeNotification(Notification notification,HttpServletRequest request) {
		return notificationService.insert(notification,request);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultBean delete(Integer id) {
		return notificationService.delete(id);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultBean notficationList(Integer schoolId) {
		return notificationService.findAll(schoolId);
	}
}
