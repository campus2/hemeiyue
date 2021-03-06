package com.hemeiyue.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.Application;
import com.hemeiyue.common.BookingModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.BookingService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;

@Service("bookingService")
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingsMapper bookingsMapper;
	
	@Autowired
	private RoomperiodsMapper roomperiodsMapper;

	@Override
	public ResultBean insertBook(Bookings book,Integer roomPeriodId,HttpServletRequest request) {
		//检查用户是否已登录
		Users user = (Users) request.getSession().getAttribute("user");
		if(user == null) {
			return new ResultBean(false, "用户请登录再执行操作");
		}
		
		//检查用户是否已注册学校
		Schools school = (Schools) request.getSession().getAttribute("school");
		if(school == null) {
			return new ResultBean(false, "请注册学校再执行操作");
		}
		
		RoomPeriods roomPeriod = roomperiodsMapper.selectById(roomPeriodId);
		if(roomPeriod == null || roomPeriod.getStatus() != 1) {
			return new ResultBean(false, "请再次确认申请是否可行");
		}
		book.setCDT(new Timestamp(new Date().getTime()));
		book.setRoomPeriod(roomPeriod);
		book.setSchool(school);
		book.setUser(user);
		book.setStatus(0);
		if(bookingsMapper.insertSelective(book) == 1) {
			return new ResultBean(true,"申请成功");
		}
		return new ResultBean(false,"操作失败");
	}

	@Override
	public ResultBean updateBook(Bookings book) {
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true,"修改成功");
		}
		return new ResultBean(false, "修改失败");
	}

	@Override
	public ResultBean deleteBook(Integer id) {
		Bookings book = new Bookings();
		book.setId(id);
		book.setStatus(4);
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true, "删除成功");
		}
		return new ResultBean(false, "删除失败");
	}

	@Override
	public ResultBean  updateApplyBook(Integer id) {
		//检查该申请是否过期
		Bookings book = bookingsMapper.selectByPrimaryKey(id);
		if(book.getRoomPeriod().getStatus() != 1) {
			return new ResultBean(false, "该申请已无效");
		}
		book.setStatus(2);
		
		//同意该申请后同时把别的同时期申请拒绝
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			List<Bookings> bookList = bookingsMapper.findSamePeriodBooks(book.getRoomPeriod().getId());
			for (Bookings b : bookList) {
				updateRefuseBook(b.getId());
			}
			return new ResultBean(true, "同意申请成功");
		}
		
		return new ResultBean(false, "同意申请失败");
	}

	@Override
	public ResultBean  updateRefuseBook(Integer id) {
		Bookings book = new Bookings();
		book.setId(id);
		//状态0：拒绝申请
		book.setStatus(0);
		
		//拒绝申请
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true, "拒绝申请成功");
		}
		return new ResultBean(false, "拒绝申请失败");
	}

	@Override
	public ResultBean  updateRevokeBook(Integer id) {
		Bookings book = new Bookings();
		book.setId(id);
		book.setStatus(-1);
		
		//撤销申请
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true, "撤销申请成功");
		}
		return new ResultBean(false, "撤销申请失败");
	}

	@SuppressWarnings("unused")
	@Override
	public ResultBean findAllBooks(Schools school) {
		//返回该管理员学校的所有申请
		List<Bookings> list = bookingsMapper.findAllBooks(school.getId());
		//封装数据
		List<BookingModel> modelList = new ArrayList<>(list.size());
		for (Bookings booking : list) {
			BookingModel bm = new BookingModel();
			bm.setId(booking.getId());
			bm.setUserId(booking.getUser().getId());
			bm.setUserName(booking.getUser().getUserName());
			bm.setRoomType(booking.getRoomPeriod().getRoom().getRoomType().getRoomType());
			bm.setRoomName(booking.getRoomPeriod().getRoom().getRoom());
			bm.setDate(DateUtil.dateToString(booking.getBookingDate()));
			bm.setTime(DateUtil.timeToString(booking.getRoomPeriod().getPeriod().getBeginTime(), 
					booking.getRoomPeriod().getPeriod().getEndTime()));
			bm.setStatus(booking.getStatus());
			modelList.add(bm);
		}
		if(list != null) {
			ResultList result = new ResultList();
			result.setResult(true);
			result.setList(modelList);
			return result;
		}
		return new ResultBean(false, "返回列表失败");
	}

	@Override
	public ResultBean findMyBooks(HttpServletRequest request) {
		//检查是否为用户操作
		Users user = (Users) request.getSession().getAttribute("user");
//		Users user = (Users) request.getServletContext().getAttribute("user");
		if(user == null) {
			return new ResultBean(false, "用户请登录再执行操作");
		}
		List<Bookings> list = bookingsMapper.findMyBooks(user.getId());
		if(list != null) {
			ResultList result = new ResultList();
			result.setResult(true);
			result.setMessage("返回列表成功");
			result.setList(list);
			return result;
		}
		return new ResultBean(false, "返回列表失败");
	}

	@Override
	public String updateCancelReserve(int id) {
		Bookings booking = new Bookings();
		booking.setId(id);
		booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
		Bookings theBook = bookingsMapper.findMyBooksWithoutTimeOut(booking).get(0);
		if(theBook == null) {
			return JSONUtil.transform(new ResultBean(false));
		}
		theBook.setStatus(-1);
		if(bookingsMapper.updateByPrimaryKeySelective(theBook) == 1) {
			return JSONUtil.transform(new ResultBean(true));
		}
		return JSONUtil.transform(new ResultBean(false));
	}

	@Override
	public ResultBean insertRoomApply(Application application,Users user,Schools school) {
		Bookings booking = new Bookings();
		
		RoomPeriods roomPeriod = roomperiodsMapper.selectById(application.getRoomPeriodId());
		if(roomPeriod == null) {
			return new ResultBean(false, "找不到roomPeriod");
		}
		booking.setRoomPeriod(roomPeriod);
		Timestamp timestamp = application.getBookingDate();
		String week = roomperiodsMapper.selectById(roomPeriod.getId()).getWeeks();
		if(week.indexOf(DateUtil.dateToWeek(timestamp.toString())) == -1) {		//插入日期与roomPeriod的week冲突
			return new ResultBean(false,"日期错误");
		}
		
		List<Bookings> blist = bookingsMapper.findBooksByRoomPeriod(roomPeriod.getId());
		for (Bookings bookings : blist) {
			if(DateUtil.dateToString(bookings.getBookingDate()) == DateUtil.dateToString(timestamp))	//同一个roomPeriod里同一天
				return new ResultBean(false,"日期错误");
		}
		
		booking.setBookingDate(timestamp);
		booking.setUser(user);
		booking.setStatus(0);
		booking.setCDT(new Timestamp(System.currentTimeMillis()));
		booking.setSchool(school);
		booking.setRemark(application.getRemark());
		
		if(bookingsMapper.insertSelective(booking) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}
}
