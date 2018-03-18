package com.hemeiyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Schools;
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
		Schools school = (Schools)request.getSession().getAttribute("schoold");
		ResultBean result = roomTypeService.insertRoomType(school,roomType);
		
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 修改课室类型
	 * @param oldRoomType 原来的课室类型名称
	 * @param newRoomType 新的课室类型名称
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyRoomType")
	@ResponseBody
	public ResultBean modifyRoomType(@RequestParam("oldRoomType")String oldRoomType,
			@RequestParam("newRoomType")String newRoomType, HttpServletRequest request) {
		Schools school = (Schools)request.getSession().getAttribute("school");
		RoomTypes roomType = new RoomTypes(oldRoomType, school);
		ResultBean result = roomTypeService.updateRoomType(roomType, newRoomType);
		return result;
	}
	
	@RequestMapping("/deleteRoomType")
	@ResponseBody
	public ResultBean deleteRoomType(@RequestParam("roomType")String roomType,
			HttpServletRequest request) {
		Schools school = (Schools)request.getSession().getAttribute("school");
		RoomTypes roomtype = new RoomTypes(roomType, school);
		
		ResultBean result = roomTypeService.delete(roomtype);
		
		return result;
	}
	
}
