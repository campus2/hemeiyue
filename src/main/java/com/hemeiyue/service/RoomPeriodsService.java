package com.hemeiyue.service;

import java.util.Date;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultMap;
import com.hemeiyue.entity.Rooms;

public interface RoomPeriodsService {

	/**
	 * 返回指定课室的预订时段
	 * @param room
	 * @return
	 */
	public ResultMap findRoomDetail(Rooms room);

	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public ResultBean delete(Integer id);
	
	/**
	 * 根据日期选择转换成星期几，然后去数据库查找该日期的可用时间段，把时间段全部发回来
	 * @param date
	 * @return
	 */
	public String getPeriod(Date date);
}
