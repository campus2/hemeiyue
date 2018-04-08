package com.hemeiyue.common;

import java.util.List;

import javax.validation.constraints.Size;

/**
 * 用与接受前端数据的room
 * @author cedo
 *
 */
public class RoomModel {

	private String roomType;
	
	private String roomName;
	@Size(min=1)
	private List<PeriodModel> period;
	
	private String repeat;
	
	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

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

	public List<PeriodModel> getPeriod() {
		return period;
	}

	public void setPeriod(List<PeriodModel> period) {
		this.period = period;
	}

	@Override
	public String toString() {
		period.forEach(p->System.out.println(p.getBeginTime()));
		return "RoomModel [roomType=" + roomType + ", roomName=" + roomName + ", period=" + period.size() + "]";
	}
	
}
