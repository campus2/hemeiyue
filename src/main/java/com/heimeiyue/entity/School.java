package com.hemeiyue.entity;

import java.util.Date;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class School {
    private Integer id;

    private String school;

    private Admin owner;

    private Date CDT;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

	public Admin getOwner() {
		return owner;
	}

	public void setOwner(Admin owner) {
		this.owner = owner;
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
    
}