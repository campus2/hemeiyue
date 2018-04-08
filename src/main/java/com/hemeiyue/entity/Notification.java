package com.hemeiyue.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateHHmmJSONDeserializer;
import com.hemeiyue.util.DateHHmmJSONSerializer;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Notification {
	private Integer id;
	
	private String title;
	
	private String content;
	
	@JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
	private Timestamp date;
	
	@JsonDeserialize(using=DateHHmmJSONDeserializer.class)
	@JsonSerialize(using=DateHHmmJSONSerializer.class)
	private Timestamp time;
	
	private Integer status;
	
	private Schools school;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
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


	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
