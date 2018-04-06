package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.beust.jcommander.Parameter;
import com.hemeiyue.entity.RoomTypes;
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
     * 返回某个学校某个类型的课室
     * @param schoolId
     * @return
     */
	public List<Rooms> selectBySchoolAndRoomType(@Param("schoolId")int schoolId, @Param("roomTypeId")int roomTypeId);

	/**
	 * 修改课室
	 * @param room
	 * @return
	 */
	public int update(Rooms room);

	/**
	 * 返回指定条件的某个课室类型
	 * @param map
	 * @return
	 */
	public RoomTypes select(Map<String, Object> map);

	/**
	 * 根据指定条件返回记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
}