package com.hemeiyue.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class Bookings {
    private Integer id;

    private RoomPeriods roomPeriod;

    private Users user;

    @JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
    private Date CDT;

    private Integer status;

    private String remark;
    
    private Schools school;
    
    

    public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomPeriods getRoomPeriod() {
		return roomPeriod;
	}

	public void setRoomPeriod(RoomPeriods roomPeriod) {
		this.roomPeriod = roomPeriod;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getCDT() {
		return CDT;
	}

	public void setCDT(Date cDT) {
		CDT = cDT;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}