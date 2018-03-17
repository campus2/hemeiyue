package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.service.RoomTypeService;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	@RequestMapping("/addRoomType")
	public String addRoomType(@RequestParam("roomType")String roomType,
			HttpServletRequest request, HttpServletResponse response) {
		int schoolId = (int)request.getSession().getAttribute("schoolId");
		ResultBean result = roomTypeService.insertRoomType(schoolId,roomType);
		
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/modifyRoomType")
	public String modifyRoomType(@RequestParam("oldRoomType")String oldRoomType,
			@RequestParam("newRoomType")String newRoomType, HttpServletResponse response) {
		
		return null;
	}
	
}
