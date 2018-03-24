package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Bookings;

public interface BookingService {
	
	/**
	 * 提交申请   用户操作
	 * @param book
	 * @param request
	 * @return
	 */
	public ResultBean insertBook(Bookings book,Integer roomPeriodId,HttpServletRequest request);
	
	/**
	 * 修改申请   用户操作
	 * @param book
	 * @param request
	 * @return
	 */
	public ResultBean updateBook(Bookings book);
	
	/**
	 * 删除申请   管理员操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean deleteBook(Integer id);
	
	/**
	 * 同意申请   管理员操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean applyBook(Integer id);
	
	/**
	 * 拒绝申请   管理员操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean refuseBook(Integer id);
	
	/**
	 * 撤销申请   用户操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean revokeBook(Integer id);
	
	/**
	 * 返回申请列表   管理员操作
	 * @param request
	 * @return
	 */
	public ResultBean findAllBooks(HttpServletRequest request);
	
	/**
	 * 返回申请列表 用户
	 * @param request
	 * @return
	 */
	public ResultBean findMyBooks(HttpServletRequest request);
}
