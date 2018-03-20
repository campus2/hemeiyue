package com.hemeiyue.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.NotificationMapper;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.NotificationService;
import com.hemeiyue.util.DateUtil;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationMapper notificationMapper;

	@Override
	public ResultBean insert(Notification notification,HttpServletRequest request) {
		try {
			notification.setDate(DateUtil.date());
			notification.setTime(DateUtil.time());
			notification.setSchool((Schools) request.getSession().getAttribute("school"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(notificationMapper.insert(notification) == 1) return new ResultBean(true);
		return new ResultBean(false);
	}

	@Override
	public ResultBean delete(Integer id) {
		if(notificationMapper.delete(id) == 1) return new ResultBean(true);
		return new ResultBean(false);
	}

	@Override
	public ResultBean findAll(Integer schoolId) {
		ResultList result = new ResultList();
		List<Notification> list = notificationMapper.findAll(schoolId);
		if(list != null) {
			result.setResult(true);
			result.setList(list);
			return result;
		}
		return new ResultBean(false);
	}
}
