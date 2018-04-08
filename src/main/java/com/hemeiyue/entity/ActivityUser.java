package com.hemeiyue.entity;

import java.sql.Timestamp;

public class ActivityUser {
	
	private Integer id;

	private Activity activity;
	
	private Users user;
	
	/**
	 * 0:未签到，1：签到
	 */
	private Integer status;
	
	private Timestamp signTime;
	
	public Timestamp getSignTime() {
		return signTime;
	}

	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
