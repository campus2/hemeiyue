package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;

public interface AdminService {
	public ResultBean login(Admin admin,HttpServletRequest request);
	
	public ResultBean register(Admin admin);
}
