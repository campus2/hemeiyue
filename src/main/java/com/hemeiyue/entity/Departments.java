package com.hemeiyue.entity;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class Departments {
    private Integer id;

    private String department;

    private Schools school;

    private Integer parentId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
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