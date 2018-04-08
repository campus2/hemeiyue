package com.hemeiyue.controller;

import java.util.Date;

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
	
	/**
	 * 根据日期选择转换成星期几，然后去数据库查找该日期的可用时间段，把时间段全部发回来
	 * @param date
	 * @return
	 */
	@RequestMapping("/getPeriod")
	@ResponseBody
	public String getPeriod(Date date) {
		return roomPeriodsService.getPeriod(date);
	}
}
