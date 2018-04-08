package com.hemeiyue.common;

import com.hemeiyue.entity.Users;

/**
 * 用户报名信息和签到状态
 * @author cedo
 *
 */
public class ActivityUserModel {

	private String userName;

    private String phone;

    private String studentNum;
    
    private String classRoom;
    
    private Integer status;

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUserInfo(Users user) {
		this.userName = user.getUserName();
		this.studentNum = user.getStudentNum();
		this.phone = user.getPhone();
		this.classRoom = user.getClassRoom();
	}
    
}
