package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/write")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean writeNotification(@RequestBody Notification notification,HttpServletRequest request) {
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		System.out.println(notification.getTitle());
		System.out.println(notification.getContent());
		return notificationService.insert(notification,school);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean delete(@RequestParam("id") Integer id) {
		return notificationService.delete(id);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean notficationList(HttpServletRequest request) {
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		return notificationService.findAll(school.getId());
	}
	
}
