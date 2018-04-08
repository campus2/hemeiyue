package com.hemeiyue.common;

/**
 * 修改课室名类，用于接受前端数据
 * @author cedo
 *
 */
public class UpdateRoom {

	private String roomType;	//课室类型
	
	private String oldRoom;		//旧的课室名
	
	private String newRoom;		//新的课室名
	
	public UpdateRoom(String oldRoom) {
		super();
		this.oldRoom = oldRoom;
	}

	public UpdateRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getOldRoom() {
		return oldRoom;
	}

	public void setOldRoom(String oldRoom) {
		this.oldRoom = oldRoom;
	}

	public String getNewRoom() {
		return newRoom;
	}

	public void setNewRoom(String newRoom) {
		this.newRoom = newRoom;
	}
	
}
