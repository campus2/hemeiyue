package com.hemeiyue.common;

import java.util.List;

public class ResultBookAndActList extends ResultBean{
	private List<BookingModel> bookingList;
	
	private List<ActivityShowModel> activityList;

	public List<BookingModel> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<BookingModel> bookingList) {
		this.bookingList = bookingList;
	}

	public List<ActivityShowModel> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityShowModel> activityList) {
		this.activityList = activityList;
	}
	
	public ResultBookAndActList(List<BookingModel> bookingList,List<ActivityShowModel> activityList) {
		this.bookingList = bookingList;
		this.activityList = activityList;
	}

	public ResultBookAndActList(boolean b, List<BookingModel> bmList, List<ActivityShowModel> amList) {
		super(b);
		this.activityList = amList;
		this.bookingList = bmList;
	}
}
