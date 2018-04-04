package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Bookings;
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
	public ResultBean applyBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService.applyBook(bookingId);
	}
	
	/**
	 * 拒绝申请   管理员操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/refuseBook")
	@ResponseBody
	public ResultBean refuseBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService.refuseBook(bookingId);
	}
	
	/**
	 * 撤销申请     用户操作
	 * @param bookingId
	 * @param request
	 * @return
	 */
	@RequestMapping("/revokeBook")
	@ResponseBody
	public ResultBean revokeBook(@RequestParam("bookingId") Integer bookingId) {
		return bookingService.revokeBook(bookingId);
	}
	
	/**
	 * 返回管理员对应的所有申请
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllBooks")
	@ResponseBody
	public ResultBean findAllBooks(HttpServletRequest request) {
		return bookingService.findAllBooks(request);
	}
	
	@RequestMapping("/findMyBooks")
	@ResponseBody
	public ResultBean findMyBooks(HttpServletRequest request) {
		return bookingService.findMyBooks(request);
	}
}
