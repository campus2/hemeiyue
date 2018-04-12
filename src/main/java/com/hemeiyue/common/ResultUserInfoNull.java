package com.hemeiyue.common;

public class ResultUserInfoNull extends ResultBean{
	private boolean userInfoNull;

	public boolean isUserInfoNull() {
		return userInfoNull;
	}

	public void setUserInfoNull(boolean userInfoNull) {
		this.userInfoNull = userInfoNull;
	}
	
	public ResultUserInfoNull(boolean result,boolean userInfoNull) {
			super(result);
			this.userInfoNull = userInfoNull;
	}
}