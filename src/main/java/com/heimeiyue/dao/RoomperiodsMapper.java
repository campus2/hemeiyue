package com.hemeiyue.dao;

import com.hemeiyue.entity.RoomPeriod;

public interface RoomperiodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomPeriod record);

    int insertSelective(RoomPeriod record);

    RoomPeriod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomPeriod record);

    int updateByPrimaryKey(RoomPeriod record);
}