package com.hemeiyue.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ApplyData;
import com.hemeiyue.common.BookingModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.UsersModel;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.BookingService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.StringUtil;

@Service("bookingService")
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingsMapper bookingsMapper;
	
	@Autowired
	private RoomperiodsMapper roomperiodsMapper;
	
	@Autowired
	private RoomtypeMapper roomtypeMapper;
	
	@Autowired
	private RoomsMapper roomsMapper;
	
	private UsersMapper usersMapper;

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
		System.out.println(theBook.getUser().getId());
		theBook.setStatus(-1);
		if(bookingsMapper.updateByPrimaryKeySelective(theBook) == 1) {
			return JSONUtil.transform(new ResultBean(true));
		}
		return JSONUtil.transform(new ResultBean(false));
	}

	@Override
	public ResultBean insertRoomApply(ApplyData application,Users user,Schools school) {
		UsersModel usersModel = application.getUsersModel();
		if(usersModel != null) {
			usersMapper.updateByPrimaryKeySelective(usersModel.setUserInfo());
		}
		
		if(application.getPeriodId() == null || StringUtil.StringIsNot(application.getRoomName()) 
				|| StringUtil.StringIsNot(application.getRoomType()) || application.getBookingDate() == null) {
			return new ResultBean(false,"预订信息不完整");
		}
		
		//根据教室类型名在数据库中查找教室类型实体
		Map<String,Object> roomTypeMap = new HashMap<>();
		roomTypeMap.put("roomType", application.getRoomType());
		RoomTypes roomType = roomtypeMapper.select(roomTypeMap);
		if(roomType == null) {
			return new ResultBean(false,"课室类型不存在");
		}
		
		
		//根据学校，教室类型，教室名返回教室实体
		Map<String,Object> roomMap = new HashMap<>();
		roomMap.put("roomType", roomType);
		roomMap.put("room", application.getRoomName());
		roomMap.put("school", school);
		Rooms room = roomsMapper.select(roomMap);
		if(room == null) {
			return new ResultBean(false,"教室不存在");
		}
		
		
		//根据教室，periodId，weeks确定RoomPeriodId
		roomMap.clear();
		roomMap.put("periodId", application.getPeriodId());
		roomMap.put("room", room);
		roomMap.put("weeks", DateUtil.dateToWeek(DateUtil.dateToString(application.getBookingDate())));
		List<RoomPeriods> rpList = roomperiodsMapper.select(roomMap);
		if(rpList == null || rpList.size() == 0) {
			return new ResultBean(false,"RoomPeriodId");
		}
		
		
		Bookings booking = new Bookings();
		
		booking.setRoomPeriod(rpList.get(0));
		
		List<Bookings> blist = bookingsMapper.findBooksByRoomPeriod(rpList.get(0).getId());
		for (Bookings bookings : blist) {
			if(DateUtil.dateToString(bookings.getBookingDate()) == DateUtil.dateToString(application.getBookingDate()))	//同一个roomPeriod里同一天
				return new ResultBean(false,"日期错误");
		}
		
		booking.setBookingDate(application.getBookingDate());
		booking.setUser(user);
		//状态1为申请中
		booking.setStatus(1);
		booking.setCDT(new Timestamp(System.currentTimeMillis()));
		booking.setSchool(school);
		booking.setRemark(application.getRemark());
		System.out.println(booking.getBookingDate());
		System.out.println(booking.getCDT());
		if(bookingsMapper.insertSelective(booking) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false,"预订失败");
	}
}
