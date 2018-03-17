package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;

public interface RoomTypeService {

	/**
	 * 根据学校id添加课室类型
	 * @param schoolId
	 * @param roomType
	 * @return
	 */
	public ResultBean insertRoomType(int schoolId, String roomType);

	
}
