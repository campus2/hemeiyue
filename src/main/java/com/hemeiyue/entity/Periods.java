package com.hemeiyue.entity;

import java.util.Date;

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
    private Date begintime;
    
    @JsonSerialize(using=DateHHmmJSONSerializer.class)
    @JsonDeserialize(using=DateHHmmJSONDeserializer.class)
    private Date endtime;

    private Admin admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBegintime() {
        return begintime;
    }

    public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    

}