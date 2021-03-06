package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.RoomTypes;

public interface RoomtypeMapper {
    
	/**
	 * 删除某个课室类型（将标志位设为-1）
	 * @param id
	 * @return
	 */
	public int updateById(Integer id);

    int insert(RoomTypes record);

    public RoomTypes selectById(Integer id);

    int update(RoomTypes record);

    /***
     * 根据学校id查询课室类型
     * @param schoolId
     * @return
     */
	public List<RoomTypes> selectBySchoolId(int schoolId);

	/**
	 * 根据指定条件查询
	 * @param roomType
	 * @return
	 */
	public List<RoomTypes> find(Map<String, Object> map);

	/**
	 * 根据指定条件返回某个课室类型
	 * @param map
	 * @return
	 */
	public RoomTypes select(Map<String, Object> map);

}