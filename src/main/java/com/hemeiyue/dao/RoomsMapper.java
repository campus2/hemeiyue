package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.Rooms;

public interface RoomsMapper {
    
	/**
	 * 删除某个课室（将标志位-1)
	 * @param id
	 * @return
	 */
	public int updateById(Integer id);

    int insert(Rooms record);

    Rooms selectById(Integer id);
    
    /**
	 * 根据指定条件返回数据
	 * @param map
	 * @return
	 */
	public List<Rooms> find(Map<String, Object> map);

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