package com.hemeiyue.service;

import java.util.List;
import java.util.Map;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
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
	 * 根据指定条件返回数据
	 * @param map
	 * @return
	 */
	public List<Rooms> find(Map<String, Object> map);

}
