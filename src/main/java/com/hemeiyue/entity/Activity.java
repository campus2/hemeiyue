package com.hemeiyue.entity;

import java.sql.Timestamp;

public class Activity {

	private Integer id;
	
	private String title;
	
	private String content;
	
	private Integer count;
	
	private Timestamp time;
	
	private Rooms address;
	
	private Admin owner;
	
	private Schools school;
	
	private Integer status;
	
	public Activity(Integer id) {
		super();
		this.id = id;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Rooms getAddress() {
		return address;
	}

	public void setAddress(Rooms address) {
		this.address = address;
	}

	public Admin getOwner() {
		return owner;
	}

	public void setOwner(Admin owner) {
		this.owner = owner;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}
	
}
