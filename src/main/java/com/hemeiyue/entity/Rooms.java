package com.hemeiyue.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Rooms {
    private Integer id;

    private String room;

    private RoomTypes roomType;

    private Schools school;

    private Departments department;

    private Integer status;
    
    public Rooms(String room, RoomTypes roomType, Schools school) {
		super();
		this.room = room;
		this.roomType = roomType;
		this.school = school;
	}

	public Rooms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public RoomTypes getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypes roomType) {
		this.roomType = roomType;
	}

	public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}