package com.hemeiyue.entity;

import java.sql.Timestamp;

import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateHHmmJSONDeserializer;
import com.hemeiyue.util.DateHHmmJSONSerializer;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class Periods {
    private Integer id;

    private String period;

    @JsonSerialize(using=DateHHmmJSONSerializer.class)
    @JsonDeserialize(using=DateHHmmJSONDeserializer.class)
    @DateTimeFormat(pattern="HH:mm")
    private Timestamp beginTime;
    
    @JsonSerialize(using=DateHHmmJSONSerializer.class)
    @JsonDeserialize(using=DateHHmmJSONDeserializer.class)
    @DateTimeFormat(pattern="HH:mm")
    private Timestamp endTime;

    @Null
    private Admin admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setBeginTime(Timestamp begintime) {
        this.beginTime = begintime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endtime) {
        this.endTime = endtime;
    }

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    

}