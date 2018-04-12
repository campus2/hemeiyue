package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.Notification;

public interface NotificationMapper {
	
	public int insert(Notification notification);
	
	public int delete(Integer id);
	
//	public List<Notification> findAll();
	
	public List<Notification> findAll(Integer schoolId);
	
	/***
	 * 返回给小程序的公告
	 * @param schoolId
	 * @return
	 */
	public List<Notification> findAlltoWX(Integer schoolId);
}
