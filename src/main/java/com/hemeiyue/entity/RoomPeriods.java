package com.hemeiyue.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

/**
 * 场地时段类
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class RoomPeriods {
    private Integer id;

    private Rooms room;

    private Periods period;

    private String weeks;

    private Date expirdate;

    private Integer requiredconfirm;

    @JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
    private Date deadline;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
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

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public Periods getPeriod() {
		return period;
	}

	public void setPeriod(Periods period) {
		this.period = period;
	}
}