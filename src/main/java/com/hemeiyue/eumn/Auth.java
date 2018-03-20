package com.hemeiyue.eumn;

public enum Auth {

	operator("运营方",-1), priAccount("主账号",0), subAccount("子账号",1), NULL("NULL",Integer.MIN_VALUE);
	
	
	private String auth;
	
	private int code;
	
	private Auth(String auth, int code) {
		this.auth = auth;
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return this.auth;
	}
	
}
