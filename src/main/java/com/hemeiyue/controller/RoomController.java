package com.hemeiyue.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.PeriodAddModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.service.RoomTypeService;
import com.hemeiyue.util.JSONUtil;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomPeriodsService roomPeriodService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
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
	@AuthLoginAnnotation(checkLogin=true)
	public Map<String, Object> roomList(HttpServletRequest request,
			HttpServletResponse response) {
//		Schools school = (Schools) request.getSession().getAttribute("school");
		System.out.println(request.getServletContext().getAttribute("currentAdmin"));
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		return roomService.selectBySchoolId(school.getId());
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
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		System.out.println("modify");
		return roomService.updateRoom(updateRoom, school);
	}
	
	/**
	 * 返回某间课室的预订时间段
	 * @param roomType
	 * @param roomName
	 * @param request
	 * @return
	 */
	@RequestMapping("/roomDetails")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public String roomDetails(@RequestParam("roomType")String roomType,
				@RequestParam("roomName")String roomName, HttpServletRequest request) {
		//当前学校
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		//所属的课室类型
		RoomTypes roomTypes = roomTypeService.selectBySchoolAndRoomType(school, roomType);
		Rooms room = new Rooms(roomName, roomTypes, school);
		
		return JSONUtil.transform(roomPeriodService.findRoomDetail(room));
	}
	
	/**
	 * 添加课室信息
	 * @param room
	 * @param response
	 * @return
	 */
	@RequestMapping("/addRoom")
	@ResponseBody
	@AuthLoginAnnotation(checkLogin=true)
	public ResultBean addRoom(@RequestBody RoomModel roomModel,
			HttpServletRequest request) {
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin admin = (Admin) request.getServletContext().getAttribute("currentAdmin");
		//构造待插入的数据
		Rooms room = new Rooms();
		room.setSchool(admin.getSchool());
		room.setRoomType(roomTypeService.selectBySchoolAndRoomType(admin.getSchool(), roomModel.getRoomType()));
		room.setRoom(roomModel.getRoomName());
		room.setStatus(1);
		
		return roomService.insertRoomModel(roomModel, room, admin);
	}
	
	/**
	 * 为某个课室添加时间段
	 * @param roomModel
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPeriod")
	@ResponseBody
	public ResultBean addPeriod(@RequestBody PeriodAddModel model, HttpServletRequest request) {
//		Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
		Admin admin = (Admin) request.getServletContext().getAttribute("currentAdmin");
		
		//构造待插入的数据
		Rooms room = new Rooms();
		room.setSchool(admin.getSchool());
		room.setRoomType(roomTypeService.selectBySchoolAndRoomType(admin.getSchool(), model.getRoomType()));
		room.setRoom(model.getRoomName());
		room.setStatus(1);
		
		return roomService.addRoomPeriod(model, room, admin);
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
//		Schools school = (Schools) request.getSession().getAttribute("school");
		Schools school = (Schools) request.getServletContext().getAttribute("school");
		RoomTypes roomtype = new RoomTypes(roomType, school);

		return roomService.deleteByTypeAndName(roomtype, roomName);
	}
}
