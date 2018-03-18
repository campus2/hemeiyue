package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;

public interface RoomService {

	/**
	 * 返回某个学校的课室
	 * @param 学校ID
	 * @return JSON数据
	 */
	public String selectBySchoolId(int schoolId);

	/**
	 * 添加课室
	 * @param room
	 */
	public ResultBean insert(Rooms room);

	/**
	 * 修改课室
	 * @param room
	 * @return
	 */
	public ResultBean update(Rooms room);

	/**
	 * 根据id删除课室
	 * @param id
	 * @return
	 */
	public ResultBean deleteById(int id);

	/**
	 * 添加课室以及添加对应的时间段
	 * @param roomModel
	 * @param room 
	 * @return
	 */
	public ResultBean insertRoomModel(RoomModel roomModel, Rooms room);
	
	/**
	 * 根据updateRoomd的属性修改room
	 * @param updateRoom
	 * @return
	 */
	public ResultBean updateRoom(UpdateRoom updateRoom);

	/**
	 * 删除指定的课室
	 * @param roomtype 课室类型
	 * @param roomName 课室名称
	 * @return
	 */
	public ResultBean deleteByTypeAndName(RoomTypes roomtype, String roomName);

}
