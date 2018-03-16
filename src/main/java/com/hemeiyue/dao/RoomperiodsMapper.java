package com.hemeiyue.dao;

import com.hemeiyue.entity.RoomPeriods;

public interface RoomperiodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomPeriods record);

    int insertSelective(RoomPeriods record);

    RoomPeriods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomPeriods record);

    int updateByPrimaryKey(RoomPeriods record);
}