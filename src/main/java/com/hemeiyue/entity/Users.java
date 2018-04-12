package com.hemeiyue.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 微信用户
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Users {
    private Integer id;

    private String openId;

    private Schools school;

    private String userName;

    private String email;

    private String phone;

    private String address;
    
    private String studentNum;
    
    private String classRoom;
    
	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
		return openId;
	}

//	public void setOpenid(String openId) {
//		this.openId = openId;
//	}

	public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

    public void setOpenId(String openId) {
		this.openId = openId;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    
    public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
}