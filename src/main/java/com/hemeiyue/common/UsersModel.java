package com.hemeiyue.common;

import com.hemeiyue.entity.Users;

public class UsersModel {
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	
	private String email;
	
	private String phone;
	
	private String studentId;
	
	private String classRoom;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public Users setUserInfo() {
		Users u = new Users();
		u.setEmail(this.email);
		u.setPhone(this.phone);
		u.setUserName(name);
		u.setStudentNum(studentId);
		u.setClassRoom(this.classRoom);
		return u;
	}
	
}
