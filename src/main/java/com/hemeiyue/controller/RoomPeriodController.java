package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.service.RoomService;

@Controller
@RequestMapping("/roomPeriod")
public class RoomPeriodController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomPeriodsService roomPeriodsService;
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRoomPeriod")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean delete(@RequestParam("id")Integer id) {
		return roomPeriodsService.delete(id);
	}
	
	/**
	 * 根据日期选择转换成星期几，然后去数据库查找该日期的可用时间段，把时间段全部发回来
	 * @param date
	 * @return
	 */
	@RequestMapping("/getPeriod")
	@ResponseBody
	public String getPeriod(@RequestParam("date")String date, @RequestParam("roomType")String roomType,
				@RequestParam("roomName")String roomName, HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		Rooms room = roomService.getRoom(roomName,roomType, school);
		return roomPeriodsService.getPeriod(room,date);
	}
}
