package com.hemeiyue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Rooms;
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
	public String roomList(HttpServletRequest request,
			HttpServletResponse response) {
		String schoolId = (String) request.getSession().getAttribute("schoolId");
		String result = roomService.selectBySchoolId(Integer.parseInt(schoolId));
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 添加/修改课室信息
	 * @param room
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@RequestBody Rooms room,
			HttpServletResponse response) {
		ResultBean result = null;
		if(room.getId() == null) {
			result = roomService.insert(room);
		}else {
			result = roomService.update(room);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除课室信息
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String id,
			HttpServletResponse response) {
		ResultBean result = roomService.deleteById(Integer.parseInt(id));
		ResponseUtil.write(response, result);
		return null;
	}
}
