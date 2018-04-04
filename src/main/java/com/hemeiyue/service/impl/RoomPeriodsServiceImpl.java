package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.util.JSONUtil;

@Service("roomPeriodService")
public class RoomPeriodsServiceImpl implements RoomPeriodsService {

	@Autowired
	private RoomperiodsMapper roomPeriodsMapper;
	
	@Override
	public String findRoomDetail(Rooms room) {
		List<RoomPeriods> list = roomPeriodsMapper.find(room);
		Map<String, List<RoomPeriods>> map = new HashMap<>();
		
		List<RoomPeriods> temp = null;
		for (RoomPeriods roomPeriods : list) {			//把教室的可用时间段根据星期分类
			String repeat = roomPeriods.getRepeat();
			
			if(repeat==null || "".equals(repeat)) continue;

			if(repeat.contains("1")) {
				if(map.get("1") == null) temp = new ArrayList<>();
				else temp = map.get("1");
				
				temp.add(roomPeriods);
				map.put("1", temp);
			}
			if(repeat.contains("2")) {
				if(map.get("2") == null) temp = new ArrayList<>();
				else temp = map.get("2");
				
				temp.add(roomPeriods);
				map.put("2", temp);
			}
			if(repeat.contains("3")) {
				if(map.get("3") == null) temp = new ArrayList<>();
				else temp = map.get("3");
				
				temp.add(roomPeriods);
				map.put("3", temp);
			}
			if(repeat.contains("4")) {
				if(map.get("4") == null) temp = new ArrayList<>();
				else temp = map.get("4");
				
				temp.add(roomPeriods);
				map.put("4", temp);
			}
			if(repeat.contains("5")) {
				if(map.get("5") == null) temp = new ArrayList<>();
				else temp = map.get("5");
				
				temp.add(roomPeriods);
				map.put("5", temp);
			}
			if(repeat.contains("6")) {
				if(map.get("6") == null) temp = new ArrayList<>();
				else temp = map.get("6");
				
				temp.add(roomPeriods);
				map.put("6", temp);
			}
			if(repeat.contains("7")) {
				if(map.get("7") == null) temp = new ArrayList<>();
				else temp = map.get("7");
				
				temp.add(roomPeriods);
				map.put("7", temp);
			}
		}
		return JSONUtil.transform(map);
	}

}
