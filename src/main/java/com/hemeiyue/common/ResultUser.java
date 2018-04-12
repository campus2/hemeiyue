package com.hemeiyue.common;

import com.hemeiyue.entity.Users;

public class ResultUser extends ResultBean{
	
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public ResultUser(boolean result,Users user) {
		super(result);
		this.user = user;
	}	
}
