package com.hemeiyue.common;

import java.util.Map;

public class UserRoom {
	private UsersModel userInfo;
	
	private Map<String,String> roomType;

	public UsersModel getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UsersModel userInfo) {
		this.userInfo = userInfo;
	}

	public Map<String, String> getRoomType() {
		return roomType;
	}

	public void setRoomType(Map<String, String> roomType) {
		this.roomType = roomType;
	}
	
	public UserRoom(UsersModel userInfo,Map<String,String> roomType) {
		this.userInfo = userInfo;
		this.roomType = roomType;
	}
}
