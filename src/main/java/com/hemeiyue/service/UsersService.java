package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.UsersModel;
import com.hemeiyue.entity.Users;

public interface UsersService {
	
	/**
	 * 小程序用户登录
	 * @param code  小程序临时登录凭证
	 * @return
	 */
	public ResultBean login(String code,HttpServletRequest request);
	
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
	
	/**
	 * 把用户预定信息返回来，包括课室预定和活动预定，过期的不用返回来
	 * @param userId
	 * @return
	 */
	public String reserve(Integer userId);
	
	/**
	 * 把用户预定信息返回来，包括课室预定和活动预定，过期的也要返回
	 * @param userId
	 * @return
	 */
	public String reserveHistory(Integer userId);
	
	/**
	 * 返回个人信息
	 * @param userId
	 * @return
	 */
	public String selectPersonalInfo(Integer userId);
	
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	public ResultBean updatePersonalInfo(UsersModel user);
	
	/**
	 * 返回学校所有的教室及个人信息
	 * @return
	 */
	public String getApplyInfo(Users user);
}
