package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Users;

public interface UsersService {
	
	/**
	 * 登录时检查用户是否第一次登录
	 * 若为true，则添加该用户的openID
	 * 若为false，则返回该用户信息
	 * @param openid
	 * @return
	 */
	public ResultBean login(String openid,HttpServletRequest request);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public ResultBean update(Users user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public ResultBean delete(Users user);
	
	public ResultBean userMessage(Integer userId);
}
