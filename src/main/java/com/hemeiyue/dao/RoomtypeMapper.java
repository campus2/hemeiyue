package com.hemeiyue.dao;

import com.hemeiyue.entity.RoomTypes;

public interface RoomtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomTypes record);

    int insertSelective(RoomTypes record);

    RoomTypes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomTypes record);

    int updateByPrimaryKey(RoomTypes record);
}