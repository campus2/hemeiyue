package com.hemeiyue.common;

public class UserModel extends ResultBean {

	private String school;
	
	public UserModel(boolean result,String school) {
		super(result);
		this.school = school;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(boolean result, String code, String message) {
		super(result, code, message);
		// TODO Auto-generated constructor stub
	}

	public UserModel(boolean result) {
		super(result);
		// TODO Auto-generated constructor stub
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
}
