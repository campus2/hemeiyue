package com.hemeiyue.common;

import java.sql.Timestamp;

/**
 * 申请表model
 * @author a2338
 *
 */
public class Application {
	
	private Integer roomPeriodId;
	
	private Timestamp BookingDate;
	
	private String remark;

	public Integer getRoomPeriodId() {
		return roomPeriodId;
	}

	public void setRoomPeriodId(Integer roomPeriodId) {
		this.roomPeriodId = roomPeriodId;
	}

	public Timestamp getBookingDate() {
		return BookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		BookingDate = bookingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
