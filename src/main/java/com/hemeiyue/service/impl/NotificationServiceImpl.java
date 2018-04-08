package com.hemeiyue.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.NotificationMapper;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.NotificationService;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationMapper notificationMapper;

	@Override
	public ResultBean insert(Notification notification, Schools school) {
		notification.setDate(new Timestamp(new Date().getTime()));
		notification.setSchool(school);
		if(notificationMapper.insert(notification) == 1) return new ResultBean(true,"发布成功");
		return new ResultBean(false,"发布失败");
	}

	@Override
	public ResultBean delete(Integer id) {
		if(notificationMapper.delete(id) == 1) return new ResultBean(true,"删除成功");
		return new ResultBean(false,"删除失败");
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
