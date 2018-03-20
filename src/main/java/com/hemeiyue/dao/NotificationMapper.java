package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.Notification;

public interface NotificationMapper {
	
	public int insert(Notification notification);
	
	public int delete(Integer id);
	
	public List<Notification> findAll(Integer schoolId);
}
