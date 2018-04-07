package com.hemeiyue.entity;

import java.sql.Timestamp;

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
    
    /**
     * 申请的日期
     */
    @JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
    private Timestamp bookingDate;

    /**
     * 创建时间
     */
    @JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
    private Timestamp CDT;

    /**
     * -1：删除状态、撤销申请，0：拒绝申请，1：申请中，,2：申请成功
     */
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

	public Timestamp getCDT() {
		return CDT;
	}

	public void setCDT(Timestamp cDT) {
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

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
    
}