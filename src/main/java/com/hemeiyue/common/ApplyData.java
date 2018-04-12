package com.hemeiyue.common;

import java.sql.Timestamp;

/**
 * 申请表model
 * @author a2338
 *
 */
public class ApplyData {
	
    /**
     * 申请人姓名
     */
    private String name;

	private String classRoom;
	
	private String studentId;
	
	private String phone;
	
	private String email;
    
	private String roomType;
	
	private String roomName;
	
	private Integer periodId;
	
	private Timestamp bookingDate;
	
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UsersModel getUsersModel() {
		UsersModel model = new UsersModel();
		model.setName(this.name);
		model.setClassRoom(this.classRoom);
		model.setEmail(this.email);
		model.setPhone(this.phone);
		return null;
	}
	
}
