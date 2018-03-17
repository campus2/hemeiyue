package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
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

}
