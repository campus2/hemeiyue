package com.hemeiyue.entity;

import java.util.Date;

/**
 * 场地时段类
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class RoomPeriod {
    private Integer id;

    private Room room;

    private Period period;

    private String repeat;

    private Date expirdate;

    private Integer requiredconfirm;

    private Date deadline;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat == null ? null : repeat.trim();
    }

    public Date getExpirdate() {
        return expirdate;
    }

    public void setExpirdate(Date expirdate) {
        this.expirdate = expirdate;
    }

    public Integer getRequiredconfirm() {
        return requiredconfirm;
    }

    public void setRequiredconfirm(Integer requiredconfirm) {
        this.requiredconfirm = requiredconfirm;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
}