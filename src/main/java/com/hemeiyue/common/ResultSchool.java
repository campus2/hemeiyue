package com.hemeiyue.common;

public class ResultSchool extends ResultBean{
	private String school;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	public ResultSchool(boolean result,String school) {
		super(result);
		this.school = school;
	}
}
