package com.hemeiyue.service;

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

}
