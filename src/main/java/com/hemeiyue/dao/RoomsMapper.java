package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.Rooms;

public interface RoomsMapper {
    
	int deleteById(Integer id);

    int insert(Rooms record);

    Rooms selectById(Integer id);

    /**
     * 返回某个学校的课室
     * @param schoolId
     * @return
     */
	public List<Rooms> selectBySchool(int schoolId);

	/**
	 * 修改课室
	 * @param room
	 * @return
	 */
	public int update(Rooms room);
}