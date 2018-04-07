package com.hemeiyue.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.NotificationMapper;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.NotificationService;
import com.hemeiyue.util.JSONUtil;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationMapper notificationMapper;

	@Override
	public ResultBean insert(Notification notification,HttpServletRequest request) {
		notification.setSchool((Schools) request.getSession().getAttribute("school"));
		if(notificationMapper.insert(notification) == 1) return new ResultBean(true);
		return new ResultBean(false);
	}

	@Override
	public ResultBean delete(Integer id) {
		if(notificationMapper.delete(id) == 1) return new ResultBean(true);
		return new ResultBean(false);
	}

	@Override
	public String findAll() {
		List<Notification> list = notificationMapper.findAll();
		if(list != null && list.size() > 0) {
			return JSONUtil.transform(list);
		}
		return JSONUtil.transform(new ResultBean(false));
	}
}
