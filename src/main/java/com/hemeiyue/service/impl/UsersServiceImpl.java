package com.hemeiyue.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ActivityModel;
import com.hemeiyue.common.ActivityShowModel;
import com.hemeiyue.common.BookingModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultBookAndActList;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.ResultSchool;
import com.hemeiyue.common.UserRoom;
import com.hemeiyue.common.UsersModel;
import com.hemeiyue.dao.ActivityUserMapper;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.dao.MessagesMapper;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.ActivityUser;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.Messages;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.WX_Util;

@Service("userService")
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private MessagesMapper messagesMappers;
	
	@Autowired
	private BookingsMapper bookingsMapper;
	
	@Autowired
	private ActivityUserMapper activityUserMapper;
	
	@Autowired
	private RoomsMapper roomsMapper;
	
	@Override
	public ResultBean login(String code,HttpServletRequest request) {
		Map<String, String> map = WX_Util.getOpenId(code);
		String openId = map.get("openId");
//		String session_key = map.get("session_key");
		
		if(openId == null) {							//如果openId为空，返回false
			return new ResultBean(false,"没有openId");
		}
		
		Users user = usersMapper.selectByOpenId(openId);
		if (user == null) {								//本地数据库不存在该用户（第一次登录）
			user = new Users();
			user.setOpenId(openId);
			if(usersMapper.insertSelective(user) != 1) {
				return new ResultBean(false,"插入失败");
			}
			user = usersMapper.selectByOpenId(openId);
			request.getSession().setAttribute("user", user);
//			request.getSession().setAttribute("session_key", session_key);
			return new ResultBean(false);
		}else {											//用户非第一次登录
			request.getSession().setAttribute("user", user);
//			request.getSession().setAttribute(session_key, session_key);
			request.getSession().setAttribute("school", user.getSchool());
			return new ResultSchool(true, user.getSchool().getSchool());
		}
	}

	@Override
	public ResultBean update(Users user) {
		ResultBean result = new ResultBean();
		if(usersMapper.updateByPrimaryKeySelective(user) == 1) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ResultBean delete(Users user) {
		ResultBean result = new ResultBean();
		if(usersMapper.deleteByPrimaryKey(user.getId()) == 1) {
			result.setResult(true);
			result.setMessage("删除成功");
		}else {
			result.setResult(false);
			result.setMessage("删除失败");
		}
		return result;
	}

	@Override
	public ResultList userMessage(Integer userId) {
		List<Messages> list = messagesMappers.findUserMessage(userId);
		ResultList result = new ResultList();
		if(list != null && list.size() > 0) {
			result.setResult(true);
			result.setList(list);
			return result;
		}
		result.setResult(false);
		return result;
	}

	@Override
	public String reserve(Integer userId) {
		if(userId == null || userId == 0) {			//如果userId为空
			return JSONUtil.transform(new ResultBean(false));
		}
		Bookings book = new Bookings();
		Users user = new Users();
		user.setId(userId);
		book.setUser(user);
		book.setBookingDate(new Timestamp(System.currentTimeMillis()));
		
		//用户的教室预定
		List<Bookings> list  = bookingsMapper.findMyBooksWithoutTimeOut(book);
		List<BookingModel> bmList = new ArrayList<>();
		if(list != null && list.size() > 0) {
			for (Bookings b : list) {
				BookingModel bm = new BookingModel();
				bm.setId(b.getId());
				bm.setRoomType(b.getRoomPeriod().getRoom().getRoomType().getRoomType());
				bm.setRoomName(b.getRoomPeriod().getRoom().getRoom());
				bm.setDate(DateUtil.dateToString(b.getBookingDate()));
				bm.setTime(DateUtil.timeToString(b.getRoomPeriod().getPeriod().getBeginTime(), b.getRoomPeriod().getPeriod().getEndTime()));
				bm.setStatus(b.getStatus());
				bmList.add(bm);
			} 
		}
		
		//用户参加活动信息
		List<ActivityShowModel> amList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		List<ActivityUser> auList = activityUserMapper.find(map);
		for (ActivityUser activityUser : auList) {
			if(activityUser.getActivity().getDate().after(new Timestamp(System.currentTimeMillis()))&&
					activityUser.getActivity().getTime().after(new Timestamp(System.currentTimeMillis())) ) {	//判断活动是否过期
				System.out.println(activityUser.getActivity().getTime());
				ActivityShowModel am = new ActivityShowModel();
				am.setId(activityUser.getActivity().getId());
				am.setActivityTitle(activityUser.getActivity().getTitle());
				am.setDate(activityUser.getActivity().getDate());
				am.setTime(activityUser.getActivity().getTime());
				am.setAddress(activityUser.getActivity().getAddress());
				amList.add(am);
			}
		}
		return JSONUtil.transform(new ResultBookAndActList(bmList, amList));
	}

	@Override
	public String reserveHistory(Integer userId) {
		if(userId == null || userId == 0) {			//如果userId为空
			return JSONUtil.transform(new ResultBean(false));
		}
		Bookings book = new Bookings();
		Users user = new Users();
		user.setId(userId);
		book.setUser(user);
		
		//用户的教室预定
		List<Bookings> list  = bookingsMapper.findMyBooksWithoutTimeOut(book);
		List<BookingModel> bmList = new ArrayList<>();
		if(list != null && list.size() > 0) {
			for (Bookings b : list) {
				BookingModel bm = new BookingModel();
				bm.setId(b.getId());
				bm.setRoomType(b.getRoomPeriod().getRoom().getRoomType().getRoomType());
				bm.setRoomName(b.getRoomPeriod().getRoom().getRoom());
				bm.setDate(DateUtil.dateToString(b.getBookingDate()));
				bm.setTime(DateUtil.timeToString(b.getRoomPeriod().getPeriod().getBeginTime(), b.getRoomPeriod().getPeriod().getEndTime()));
				bm.setStatus(b.getStatus());
				bmList.add(bm);
			} 
		}
		
		//用户参加活动信息
		List<ActivityShowModel> amList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		List<ActivityUser> auList = activityUserMapper.find(map);
		for (ActivityUser activityUser : auList) {
				System.out.println(activityUser.getActivity().getTime());
				ActivityShowModel am = new ActivityShowModel();
				am.setId(activityUser.getActivity().getId());
				am.setActivityTitle(activityUser.getActivity().getTitle());
				am.setDate(activityUser.getActivity().getDate());
				am.setTime(activityUser.getActivity().getTime());
				am.setAddress(activityUser.getActivity().getAddress());
				amList.add(am);
		}
		return JSONUtil.transform(new ResultBookAndActList(bmList, amList));
	}

	@Override
	public String selectPersonalInfo(Integer userId) {
		return JSONUtil.transform(usersMapper.selectPersonalInfo(userId));
	}

	@Override
	public ResultBean updatePersonalInfo(UsersModel user) {
		if(user == null) {
			return new ResultBean(false);
		}
		if(usersMapper.updatePersonalInfo(user) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}

	@Override
	public String getApplyInfo(Users user) {
		Schools school;
		if(user == null || user.getSchool() == null) {
			return JSONUtil.transform(new ResultBean(false));
		}
		
		//个人信息
		UsersModel usersModel = usersMapper.selectPersonalInfo(user.getId());
		
		//教室信息
		school = user.getSchool();
		Map<String,Object> map = new HashMap<>();
		map.put("school", school);
		List<Rooms> rlist = roomsMapper.find(map);
		Map<String,String> roomType = new HashMap<>();
		for (Rooms rooms : rlist) {
			roomType.put(rooms.getRoom(), rooms.getRoomType().getRoomType());
		}
		return JSONUtil.transform(new UserRoom(usersModel, roomType));
	}

}
