package com.hemeiyue.common;

import java.util.List;

public class ResultBookAndActList{
	private List<BookingModel> bookingList;
	
	private List<ActivityModel> activityList;

	public List<BookingModel> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<BookingModel> bookingList) {
		this.bookingList = bookingList;
	}

	public List<ActivityModel> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityModel> activityList) {
		this.activityList = activityList;
	}
	
	public ResultBookAndActList(List<BookingModel> bookingList,List<ActivityModel> activityList) {
		this.bookingList = bookingList;
		this.activityList = activityList;
	}
}
