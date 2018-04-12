package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hemeiyue.entity.Periods;
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
    
    /**
     * 返回可用的教室列表
     * @return
     */
    public List<Rooms> findRooms();
    
    /**
     * 返回对应时间段下的可用教室列表
     * @param periodId
     * @return
     */
    public List<Rooms> findRoomsByPeriodId(Integer periodId);
    
    /**
     * 返回可选的时间列表
     * @return
     */
    public List<Periods> findPeriods();
    
    /**
     * 返回对应教室下的可用时间段列表
     * @param roomId
     * @return
     */
    public List<Periods> findPeriodsByRoomId(Integer roomId);

    /***
     * 批量插入
     * @param rpList
     * @return
     */
	int insertList(@Param("list")List<RoomPeriods> list);
	
	public List<RoomPeriods> getPeriod(@Param("roomId")Integer roomId,@Param("weeks")String weeks);

	public List<RoomPeriods> select(Map<String,Object> map);

	public List<RoomPeriods> findRooms(Map<String, Object> map);
	
}