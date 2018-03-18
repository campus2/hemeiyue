package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.PeriodTime;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.dao.PeriodsMapper;
import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Periods;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.util.JSONUtil;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomsMapper roomMapper;
	
	@Autowired
	private RoomtypeMapper roomTypeMapper;
	
	@Autowired
	private SchoolsMapper schoolMapper;
	
	@Autowired
	private PeriodsMapper periodMapper;
	
	@Autowired
	private RoomperiodsMapper roomperiodsMapper;
	
	@Override
	public ResultBean insertRoomModel(RoomModel roomModel,Rooms room) {
		ResultBean result = new ResultBean();
		
		Map<String, Object> map = new HashMap<>();
		map.put("room", room.getRoom());
		map.put("school", room.getSchool());
		//如果已存在此课室号
		List<Rooms> list = roomMapper.find(map);
		if(list!=null && list.size()>0) {
			result.setResult(false);
			result.setMessage("该课室已经存在");
			return result;
		}
		//插入rooms表
		roomMapper.insert(room);
		
		List<PeriodTime> pList = roomModel.getPeriod();
		for (PeriodTime periodTime : pList) {
			//插入period表
			Periods period = periodTime.getPeriod();
			periodMapper.insert(period);
			RoomPeriods roomPeriods = new RoomPeriods();
			roomPeriods.setRoom(room);
			roomPeriods.setPeriod(period);
			//插入RoomPeriod表
			roomperiodsMapper.insert(roomPeriods);
			period.setId(null);
		}
		result.setResult(true);
		result.setMessage("添加成功");
		return result;
	}

	@Override
	public String selectBySchoolId(int schoolId) {
		List<Rooms> roomList = roomMapper.selectBySchool(schoolId);
		List<RoomTypes> typeList = roomTypeMapper.selectBySchoolId(schoolId);
		
		//将课室按照课室类型进行分类
		Map<String, Object> roomMap = new HashMap<>();
		//外层循环课室类型，内层循环所有课室
		for(int i=0; i<typeList.size(); i++) {
			List<Rooms> temp = new ArrayList<>();
			String roomTypeName = typeList.get(i).getRoomType();
			if(roomList!=null && roomList.size()>0)
				for(int j=0; j<roomList.size(); j++) {
					Rooms room = roomList.get(j);
					//找到对应类型的课室
					if(roomTypeName.equals(room.getRoomType().getRoomType())) {
						temp.add(roomList.get(j));
					}
				}
			roomMap.put(roomTypeName, temp);
		}
		//学校名称
		String school = schoolMapper.selectById(schoolId).getSchool();
		Map<String, Object> map = new HashMap<>();
		map.put(school, roomMap);
		return JSONUtil.transform(map);
	}

	@Override
	public ResultBean insert(Rooms room) {
		ResultBean result = new ResultBean();
		if(roomMapper.insert(room) > 0) {
			result.setResult(true);
			result.setMessage("添加成功");
		}else {
			result.setResult(false);
		}
		return result;
	}

	@Override
	public ResultBean update(Rooms room) {
		ResultBean result = new ResultBean();
		if(roomMapper.update(room) > 0) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
		}
		return result;
	}

	@Override
	public ResultBean deleteById(int id) {
		ResultBean result = null;
		if(roomMapper.updateById(id) > 0) {
			result = new ResultBean(true, "删除成功");
		}else {
			result = new ResultBean(false);
		}
		return result;
	}

	@Override
	public ResultBean deleteByTypeAndName(RoomTypes roomType, String roomName) {
		//查询待删除课室的课室类型是否存在
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", roomType.getRoomType());
		map.put("school", roomType.getSchool());

		List<RoomTypes> rtList = roomTypeMapper.find(map);
		if(rtList==null | rtList.size()==0) return new ResultBean(false);
		RoomTypes roomtype = rtList.get(0);
		
		//待删除课室是否存在
		map.put("room", roomName);
		map.put("roomType", roomtype);
		System.out.println(map.get("roomType"));
		List<Rooms> roomList = roomMapper.find(map);
		if(roomList==null | roomList.size()==0) return new ResultBean(false);
		
		ResultBean result = new ResultBean();
		if(roomMapper.updateById(roomList.get(0).getId()) > 0) {
			result.setResult(true);
		}else {
			result.setResult(false);
		}
		return result;
	}
	
	@Override
	public ResultBean updateRoom(UpdateRoom updateRoom) {
		ResultBean result = new ResultBean();
		Map<String, Object> map = new HashMap<>();
		//查询课室类型
		map.put("roomType", updateRoom.getRoomType());
		RoomTypes roomType = roomTypeMapper.find(map).get(0);
		//查询课室是否存在
		map.put("roomType", roomType);
		map.put("room", updateRoom.getOldRoom());
		List<Rooms> list = roomMapper.find(map);
		if(list==null | list.size()==0) {
			result.setResult(false);
			result.setMessage("该课室不存在");
			return result;
		}
		//更新课室
		Rooms room = list.get(0);
		room.setRoom(updateRoom.getNewRoom());
		System.out.println(room.getRoomType().getId());
		if(roomMapper.update(room) > 0) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
			result.setMessage("修改失败");
		}
		return result;
	}
}