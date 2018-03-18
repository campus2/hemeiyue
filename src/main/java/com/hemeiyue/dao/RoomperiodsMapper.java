package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.Rooms;

public interface RoomperiodsMapper {
    
	int deleteById(Integer id);

    int insert(RoomPeriods record);

    RoomPeriods selectById(Integer id);

    int update(RoomPeriods record);
    
    /**
     * 返回指定课室的预订时段
     * @param roomPeriods
     * @return
     */
    public List<RoomPeriods> find(Rooms room);
}