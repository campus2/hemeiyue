package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Schools;

public interface RoomTypeService {

	/**
	 * 根据学校id添加课室类型
	 * @param school
	 * @param roomType
	 * @return
	 */
	public ResultBean insertRoomType(Schools school, String roomType);

	/**
	 * 修改课室类型名称
	 * @param oldRoomType 原来的教室类型名称
	 * @param newRoomType 新的教室类型名称
	 * @return
	 */
	public ResultBean updateRoomType(RoomTypes oldRoomType, String newRoomType);

	/**
	 * 删除某个学校的课室类型
	 * @param roomType
	 * @return
	 */
	public ResultBean delete(RoomTypes roomType);
	
	/**
	 * 返回指定学校的指定课室类型
	 * @param school
	 * @param roomType
	 * @return
	 */
	public RoomTypes selectBySchoolAndRoomType(Schools school, String roomType);

	
}
