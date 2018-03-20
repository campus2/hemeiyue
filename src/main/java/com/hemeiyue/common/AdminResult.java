package com.hemeiyue.common;

import com.hemeiyue.entity.Admin;

public class AdminResult extends ResultBean {
	private Integer authority;
	
	private Admin admin;
	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
