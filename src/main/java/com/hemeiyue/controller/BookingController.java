package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ApplyData;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.BookingService;

@Controller
@RequestMapping("/book")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	/**
	 * 递交申请   用户操作
	 * @param booking  提交的数据有RoomPeriod，remark
	 * @param request
	 * @return
	 */
	@RequestMapping("/booking")
	@ResponseBody
	public ResultBean booking(@RequestBody Bookings book,@RequestParam("roomPeriodId") Integer roomPeriodId,HttpServletRequest request) {
		return bookingService.insertBook(book,roomPeriodId, request);
	}
	
	/**
	 * 修改申请   用户操作
	 * @param booking  提交的数据有RoomPeriod，remark
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateBook")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean updateBook(@RequestBody Bookings book) {
		return bookingService.updateBook(book);
	}
	
	/**
	 * 删除申请    管理员操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteBook")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean deleteBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService.deleteBook(bookingId);
	}
	
	/**
	 * 同意申请   管理员操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyBook")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean applyBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService. updateApplyBook(bookingId);
	}
	
	/**
	 * 拒绝申请   管理员操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/refuseBook")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean refuseBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService. updateRefuseBook(bookingId);
	}
	
	/**
	 * 撤销申请     用户操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/revokeBook")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean revokeBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService. updateRevokeBook(bookingId);
	}
	
	/**
	 * 返回管理员对应的所有申请
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllBooks")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean findAllBooks(HttpServletRequest request) {
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		return bookingService.findAllBooks(school);
	}
	
	@RequestMapping("/findMyBooks")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean findMyBooks(HttpServletRequest request) {
		return bookingService.findMyBooks(request);
	}
	
	/**
	 * 根据预定的id取消该预定，校验该id的预定是否已经过期
	 * @param id
	 * @return
	 */
	@RequestMapping("/cancelReserve")
	@ResponseBody
	public String cancelReserve(int id) {
		return bookingService.updateCancelReserve(id);
	}
	
	/**
	 * 提交用户申请表（课室申请）
	 * @param application
	 * @return
	 */
	@RequestMapping("/handleRoomApply")
	@ResponseBody
	public ResultBean handleRoomApply(ApplyData application,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		Schools school = (Schools) request.getSession().getAttribute("school");
		if(user == null) {
			return new ResultBean(false,"找不到user");
		}
		if(school == null) {
			school = user.getSchool();
			if(school == null) {
				return new ResultBean(false,"找不到学校");
			}
		}
		return bookingService.insertRoomApply(application, user, school);
	}
}
