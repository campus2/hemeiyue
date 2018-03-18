package com.hemeiyue.dao;

import com.hemeiyue.entity.RoomPeriods;

public interface RoomperiodsMapper {
    int deleteById(Integer id);

    int insert(RoomPeriods record);

    RoomPeriods selectById(Integer id);

    int update(RoomPeriods record);
}