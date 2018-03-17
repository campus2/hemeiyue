package com.hemeiyue.common;

import java.util.List;

/**
 * 用与接受前端数据的room
 * @author cedo
 *
 */
public class RoomModel {

	private String roomType;
	
	private String roomName;
	
	private List<PeriodTime> period;
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<PeriodTime> getPeriod() {
		return period;
	}

	public void setPeriod(List<PeriodTime> period) {
		this.period = period;
	}
	
}
