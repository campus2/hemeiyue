package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.util.ResponseUtil;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	/**
	 * 返回某个学校的课室
	 * @param schoolId
	 * @param response
	 * @return 学校
	 * 				课室号
	 * 				课室类型
	 */
	@RequestMapping("/roomList")
	@ResponseBody
	public String roomList(HttpServletRequest request,
			HttpServletResponse response) {
		Schools school = (Schools) request.getSession().getAttribute("school");
		String result = roomService.selectBySchoolId(school.getId());
		ResponseUtil.write(response, result);
		return result;
	}
	
	/**
	 * 修改课室名称
	 * @param updateRoom
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyRoomName")
	@ResponseBody
	public ResultBean modifyRoomName(@RequestBody UpdateRoom updateRoom,
			HttpServletRequest request) {
		Schools school = (Schools)request.getSession().getAttribute("school");
		updateRoom.setSchool(school);
		ResultBean result = roomService.updateRoom(updateRoom);
		
		return result;
	}
	
	@RequestMapping("/roomDetails")
	@ResponseBody
	public String roomDetails(@RequestParam("roomType")String roomType,
				@RequestParam("roomName")String roomName, HttpServletRequest request) {
		
		return null;
	}
	
	/**
	 * 添加课室信息
	 * @param room
	 * @param response
	 * @return
	 */
	@RequestMapping("/addRoom")
	@ResponseBody
	public ResultBean addRoom(@RequestBody RoomModel roomModel,
			HttpServletRequest request) {
		Schools school = (Schools)request.getSession().getAttribute("school");
		//构造待插入的数据
		Rooms room = new Rooms();
		room.setSchool(school);
		room.setRoomType(new RoomTypes(roomModel.getRoomType()));
		room.setRoom(roomModel.getRoomName());
		
		ResultBean result = roomService.insertRoomModel(roomModel,room);
		return result;
	}
	
	
	/**
	 * 删除课室信息
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteRoom")
	@ResponseBody
	public ResultBean delete(@RequestParam("roomType")String roomType,
			@RequestParam("roomName")String roomName, HttpServletRequest request) {
		Schools school = (Schools)request.getSession().getAttribute("school");
		RoomTypes roomtype = new RoomTypes(roomType, school);
		ResultBean result = roomService.deleteByTypeAndName(roomtype, roomName);

		return result;
	}
}
