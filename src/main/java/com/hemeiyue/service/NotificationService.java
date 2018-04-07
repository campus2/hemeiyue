package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Notification;

public interface NotificationService {
	
	public ResultBean insert(Notification notification,HttpServletRequest request);
	
	public ResultBean delete(Integer id);
	
	public String findAll();
}
