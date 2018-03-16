package com.hemeiyue.entity;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-03-14
 */
public class SchoolAdmins {
    private Integer id;

    private Schools school;

    private Admin admin;

    private Date cdt;

    private Integer parentId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Date getCdt() {
        return cdt;
    }

    public void setCdt(Date cdt) {
        this.cdt = cdt;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}