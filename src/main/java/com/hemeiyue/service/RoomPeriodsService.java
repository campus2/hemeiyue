package com.hemeiyue.service;

import com.hemeiyue.entity.Rooms;

public interface RoomPeriodsService {

	/**
	 * 返回指定课室的预订时段
	 * @param room
	 * @return
	 */
	public String findRoomDetail(Rooms room);

}
