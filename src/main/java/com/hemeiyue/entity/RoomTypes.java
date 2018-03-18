package com.hemeiyue.entity;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class RoomTypes {
    private Integer id;

    private String roomType;

    private Schools school;

    private Integer status = 1;
    
    public RoomTypes(String roomType, Schools school) {
		super();
		this.roomType = roomType;
		this.school = school;
	}

	public RoomTypes(Integer id) {
		super();
		this.id = id;
	}

	public RoomTypes(String roomType) {
		super();
		this.roomType = roomType;
	}

	public RoomTypes() {
		super();
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

	public void setRoomType(String roomType) {
		this.roomType = roomType == null ? null : roomType.trim();
	}

    public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}


	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}