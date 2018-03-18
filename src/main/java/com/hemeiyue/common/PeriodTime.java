package com.hemeiyue.common;

import com.hemeiyue.entity.Periods;
import com.hemeiyue.entity.Rooms;

/**
 * 课室时间段关系
 * @author cedo
 *
 */
public class PeriodTime {

	private Rooms room;
	
	private Periods period;
	
	private Integer status;

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public Periods getPeriod() {
		return period;
	}

	public void setPeriod(Periods period) {
		this.period = period;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
