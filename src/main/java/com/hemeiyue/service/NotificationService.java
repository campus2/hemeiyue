package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;

public interface NotificationService {
	
	public ResultBean insert(Notification notification,Schools school);
	
	public ResultBean delete(Integer id);
	
	/**
	 * 返回学校的全部公告
	 * @param schoolId
	 * @return
	 */
	public ResultBean findAll(Integer schoolId);
}
