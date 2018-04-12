package com.hemeiyue.common;

public class UsersModelResult {
private String name;
	
	private String phone;
	
	private String classRoom;
	
	private String studentId;
	
	private String email;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UsersModelResult(UsersModel usersModel) {
		this.name = usersModel.getName();
		this.phone = usersModel.getPhone();
		this.classRoom = usersModel.getClassRoom();
		this.studentId = usersModel.getStudentId();
		this.email = usersModel.getEmail();
	}
}
