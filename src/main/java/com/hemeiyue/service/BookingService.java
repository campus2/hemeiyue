package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ApplyData;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;

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
	public ResultBean updateApplyBook(Integer id);
	
	/**
	 * 拒绝申请   管理员操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean  updateRefuseBook(Integer id);
	
	/**
	 * 撤销申请   用户操作
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultBean  updateRevokeBook(Integer id);
	
	/**
	 * 返回申请列表   管理员操作
	 * @param request
	 * @return
	 */
	public ResultBean findAllBooks(Schools school);
	
	/**
	 * 返回申请列表 用户
	 * @param request
	 * @return
	 */
	public ResultBean findMyBooks(HttpServletRequest request);
	
	/**
	 * 根据预定的id取消该预定，校验该id的预定是否已经过期
	 * @param id
	 * @return
	 */
	public String updateCancelReserve(int id);
	
	/**
	 * 提交用户申请表（课室申请）
	 * @param application
	 * @return
	 */
	public ResultBean insertRoomApply(ApplyData application,Users user,Schools school);
}
