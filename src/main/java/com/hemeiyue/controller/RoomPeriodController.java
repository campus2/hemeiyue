package com.hemeiyue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.annotion.AuthLoginAnnotation;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.service.RoomPeriodsService;

@Controller
@RequestMapping("/roomPeriod")
public class RoomPeriodController {

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
	
}
