package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.RoomTypes;

public interface RoomtypeMapper {
    
	int deleteById(Integer id);

    int insert(RoomTypes record);

    public RoomTypes selectById(Integer id);

    int update(RoomTypes record);

    /***
     * 根据学校id查询课室类型
     * @param schoolId
     * @return
     */
	public List<RoomTypes> selectBySchoolId(int schoolId);

}