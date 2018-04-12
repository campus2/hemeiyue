package com.hemeiyue.common;

public class ResultUseModel extends ResultBean{
	
	private UsersModelResult user;

	public UsersModelResult getUser() {
		return user;
	}

	public void setUser(UsersModelResult user) {
		this.user = user;
	}

	public ResultUseModel(boolean result,UsersModel usersModel) {
		super(result);
		this.user = new UsersModelResult(usersModel);
	}
}
