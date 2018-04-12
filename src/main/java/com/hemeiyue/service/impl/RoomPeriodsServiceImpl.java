package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.ResultMap;
import com.hemeiyue.common.WXPeriodModel;
import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;

@Service("roomPeriodService")
public class RoomPeriodsServiceImpl implements RoomPeriodsService {

	@Autowired
	private RoomperiodsMapper roomPeriodsMapper;
	
	@Override
	public ResultMap findRoomDetail(Rooms room) {
		List<RoomPeriods> list = roomPeriodsMapper.find(room);
		if(list==null || list.size()==0) return new ResultMap(false, "暂无数据");
		Map<String, List<RoomPeriods>> data = new HashMap<>();
		
		List<RoomPeriods> temp = null;
		for (RoomPeriods roomPeriods : list) {			//把教室的可用时间段根据星期分类
			String repeat = roomPeriods.getWeeks();
			
			if(repeat==null || "".equals(repeat)) continue;

			if(repeat.contains("1")) {
				if(data.get("1") == null) temp = new ArrayList<>();
				else temp = data.get("1");
				
				temp.add(roomPeriods);
				data.put("1", temp);
			}
			if(repeat.contains("2")) {
				if(data.get("2") == null) temp = new ArrayList<>();
				else temp = data.get("2");
				
				temp.add(roomPeriods);
				data.put("2", temp);
			}
			if(repeat.contains("3")) {
				if(data.get("3") == null) temp = new ArrayList<>();
				else temp = data.get("3");
				
				temp.add(roomPeriods);
				data.put("3", temp);
			}
			if(repeat.contains("4")) {
				if(data.get("4") == null) temp = new ArrayList<>();
				else temp = data.get("4");
				
				temp.add(roomPeriods);
				data.put("4", temp);
			}
			if(repeat.contains("5")) {
				if(data.get("5") == null) temp = new ArrayList<>();
				else temp = data.get("5");
				
				temp.add(roomPeriods);
				data.put("5", temp);
			}
			if(repeat.contains("6")) {
				if(data.get("6") == null) temp = new ArrayList<>();
				else temp = data.get("6");
				
				temp.add(roomPeriods);
				data.put("6", temp);
			}
			if(repeat.contains("7")) {
				if(data.get("7") == null) temp = new ArrayList<>();
				else temp = data.get("7");
				
				temp.add(roomPeriods);
				data.put("7", temp);
			}
		}
		return new ResultMap(true, data);
	}

	@Override
	public ResultBean delete(Integer id) {
		if(roomPeriodsMapper.deleteById(id) > 0) {
			return new ResultBean(true, "删除成功");
		}
		return new ResultBean(false, "删除失败");
	}

	@Override
	public String getPeriod(Rooms room,String date) {
		if(date == null || room.getRoomType()==null || room.getRoom()==null) {
			return JSONUtil.transform(new ResultBean(false,"请选择课室"));
		}
		String week = DateUtil.dateToWeek(date);
		List<RoomPeriods> plist = roomPeriodsMapper.getPeriod(room.getId(),week);
		if(plist==null || plist.size()==0) return JSONUtil.transform(new ResultBean(false,"暂无可预订时段"));
		List<WXPeriodModel> resultList = new ArrayList<>();
		for (RoomPeriods p : plist) {
			WXPeriodModel pm = new WXPeriodModel();
			pm.setId(p.getPeriod().getId());
			pm.setBeginTime(DateUtil.timeToString(p.getPeriod().getBeginTime()));
			pm.setEndTime(DateUtil.timeToString(p.getPeriod().getEndTime()));
			resultList.add(pm);
		}
		
		return JSONUtil.transform(new ResultList(true, resultList));
	}
}
