package com.hemeiyue.controller.WXcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.service.RoomService;

@Controller
@RequestMapping("/WXroom")
public class WX_RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomPeriodsService roomPeriodsService;
	
	@RequestMapping("/getRoom")
	@ResponseBody
	/**
	 * 根据课室类型名，返回所有的该课室类型所有的课室号
	 * @param roomType
	 * @return
	 */
	public ResultBean getRoom(@RequestParam("roomType")String roomType,HttpServletRequest request) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		System.out.println(school.getId());
		return roomService.getRoom(roomType, school);
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
