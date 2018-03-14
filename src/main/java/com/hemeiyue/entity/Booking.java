package com.hemeiyue.entity;

import java.util.Date;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class Booking {
    private Integer id;

    private RoomPeriod roomPeriod;

    private User user;

    private Date CDT;

    private Integer status;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomPeriod getRoomPeriod() {
		return roomPeriod;
	}

	public void setRoomPeriod(RoomPeriod roomPeriod) {
		this.roomPeriod = roomPeriod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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