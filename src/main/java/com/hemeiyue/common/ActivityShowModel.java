package com.hemeiyue.common;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateHHmmJSONDeserializer;
import com.hemeiyue.util.DateHHmmJSONSerializer;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityShowModel {
	private Integer id;
	
	private String activityTitle;
	
	private String content;
	
	@JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
	private Timestamp date;
	
	@JsonDeserialize(using=DateHHmmJSONDeserializer.class)
	@JsonSerialize(using=DateHHmmJSONSerializer.class)
	private Timestamp time;
	
	private Integer count;
	
	private Integer sign;
	
	private String imageUrl;
	
	private Integer status;
	
	private String address;
	
	public Timestamp getDate() {
		return date;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
