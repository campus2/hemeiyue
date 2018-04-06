package com.hemeiyue.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		
		try {
			book.setCDT(DateUtil.dateTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public ResultBean applyBook(Integer id) {
		//检查该申请是否过期
		Bookings book = bookingsMapper.selectByPrimaryKey(id);
		if(book.getRoomPeriod().getStatus() != 1) {
			return new ResultBean(false, "该申请已无效");
		}
		book.setStatus(1);
		
		//同意该申请后同时把别的同时期申请拒绝
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			List<Bookings> bookList = bookingsMapper.findSamePeriodBooks(book.getRoomPeriod().getId());
			for (Bookings b : bookList) {
				refuseBook(b.getId());
			}
			return new ResultBean(true, "同意申请成功");
		}
		
		return new ResultBean(false, "同意申请失败");
	}

	@Override
	public ResultBean refuseBook(Integer id) {
		Bookings book = new Bookings();
		book.setId(id);
		book.setStatus(2);
		
		//拒绝申请
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true, "拒绝申请成功");
		}
		return new ResultBean(false, "拒绝申请失败");
	}

	@Override
	public ResultBean revokeBook(Integer id) {
		Bookings book = new Bookings();
		book.setId(id);
		book.setStatus(3);
		
		//撤销申请
		if(bookingsMapper.updateByPrimaryKeySelective(book) == 1) {
			return new ResultBean(true, "撤销申请成功");
		}
		return new ResultBean(false, "撤销申请失败");
	}

	@Override
	public ResultBean findAllBooks(HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		
		//返回该管理员学校的所有申请
		List<Bookings> list = bookingsMapper.findAllBooks(school.getId());
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
}
