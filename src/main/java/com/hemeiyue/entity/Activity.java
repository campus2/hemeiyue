package com.hemeiyue.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateHHmmJSONDeserializer;
import com.hemeiyue.util.DateHHmmJSONSerializer;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

public class Activity {

	private Integer id;
	
	private String title;
	
	private String content;
	
	private Integer number;
	
	@JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
	private Timestamp date;
	
	@JsonDeserialize(using=DateHHmmJSONDeserializer.class)
	@JsonSerialize(using=DateHHmmJSONSerializer.class)
	private Timestamp time;
	
	private String imageUrl;
	
	private String address;
	
	private Admin owner;
	
	private Schools school;
	
	/**
	 * 0待举办，1：已举办
	 */
	private Integer status;
	
	public Activity(Integer id) {
		super();
		this.id = id;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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

	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", content=" + content + ", count=" + number + ", date="
				+ date + ", time=" + time + ", imageUrl=" + imageUrl + ", address=" + address + ", owner=" + owner
				+ ", school=" + school + ", status=" + status + "]";
	}
	
	
}
