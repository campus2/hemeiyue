package com.hemeiyue.common;

import java.util.List;

public class UserRoom extends ResultBean{
	private UsersModel userInfo;
	
	private List<String> roomType;

	public UsersModel getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UsersModel userInfo) {
		this.userInfo = userInfo;
	}

	public List<String> getRoomType() {
		return roomType;
	}

	public void setRoomType(List<String> roomType) {
		this.roomType = roomType;
	}

	public UserRoom(UsersModel userInfo,List<String> roomType) {
		this.userInfo = userInfo;
		this.roomType = roomType;
	}

	public UserRoom(boolean b, UsersModel usersModel, List<String> roomType2) {
		super(b);
		this.userInfo = usersModel;
		this.roomType = roomType2;
	}
}
