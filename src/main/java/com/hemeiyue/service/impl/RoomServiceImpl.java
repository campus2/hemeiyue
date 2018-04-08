package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.PeriodAddModel;
import com.hemeiyue.common.PeriodModel;
import com.hemeiyue.common.ResultAddPeriod;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.dao.PeriodsMapper;
import com.hemeiyue.dao.RoomperiodsMapper;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Periods;
import com.hemeiyue.entity.RoomPeriods;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.util.DateUtil;

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
	public ResultBean insertRoomModel(RoomModel roomModel,Rooms room, Admin admin) {
		Map<String, Object> map = new HashMap<>();
		map.put("room", room.getRoom());
		map.put("school", room.getSchool());
		//如果已存在此课室号s
		List<Rooms> list = roomMapper.find(map);
		if(list!=null && list.size()>0) {
			return new ResultBean(false, "该课室已经存在");
		}
		//插入rooms表
		roomMapper.insert(room);
		List<Periods> pList = new ArrayList<>();
		System.out.println(roomModel.getPeriod().size());
		for (PeriodModel pm : roomModel.getPeriod()) {
			Periods period = new Periods();
			period.setAdmin(admin);
			period.setBeginTime(DateUtil.time2stamp(pm.getBeginTime()));
			period.setEndTime(DateUtil.time2stamp(pm.getEndTime()));
			pList.add(period);
		}
		List<RoomPeriods> rpList = new ArrayList<>();
		System.out.println("da"+pList.size());
		//批量插入period表
		periodMapper.insertList(pList);
		for (Periods period : pList) {
			period.setAdmin(admin);
			int i = 1;
			while(i<=7) {
				RoomPeriods roomPeriods = new RoomPeriods();
				roomPeriods.setRoom(room);
				roomPeriods.setPeriod(period);
				roomPeriods.setWeeks(""+(i++));
				roomPeriods.setStatus(1);
				rpList.add(roomPeriods);
			}
		}
		roomperiodsMapper.insertList(rpList);
		return new ResultBean(true, "添加成功");
	}
	
	@Override
	public ResultBean addRoomPeriod(PeriodAddModel model, Rooms room, Admin admin) {
		Map<String, Object> map = new HashMap<>();
		map.put("room", room.getRoom());
		map.put("school", room.getSchool());
		//如果不存在此课室号
		List<Rooms> list = roomMapper.find(map);
		if(list==null || list.size()<=0) {
			return new ResultBean(false, "该课室不存在");
		}
		room = list.get(0);
		Periods period = new Periods();
		period.setAdmin(admin);
		period.setBeginTime(DateUtil.time2stamp(model.getBeginTime()));
		period.setEndTime(DateUtil.time2stamp(model.getEndTime()));
		//插入period表
		periodMapper.insert(period);
		
		RoomPeriods roomPeriods = new RoomPeriods();
		roomPeriods.setRoom(room);
		roomPeriods.setPeriod(period);
		roomPeriods.setWeeks(model.getWeeks());
		roomPeriods.setStatus(1);
		//插入roomperiod表
		roomperiodsMapper.insert(roomPeriods);
		return new ResultAddPeriod(true,"添加成功", roomPeriods.getId());
	}

	@Override
	public Map<String, Object> selectBySchoolId(int schoolId) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<RoomTypes> typeList = roomTypeMapper.selectBySchoolId(schoolId);
		
		for(int i=0; i<typeList.size(); i++) {
			Map<String, Object> roomMap = new HashMap<>();
			RoomTypes rt = typeList.get(i);
			List<Rooms> roomList = roomMapper.selectBySchoolAndRoomType(schoolId,rt.getId());
			roomMap.put("type", rt.getRoomType());
			roomMap.put("class", roomList);
			resultList.add(roomMap);
		}
		//学校名称
		String school = schoolMapper.selectById(schoolId).getSchool();
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		map.put("roomType", resultList);
		Map<String, Object> r = new HashMap<>();
		r.put("result", true);
		r.put("data", map);
		return r;
		
//		//将课室按照课室类型进行分类
//		Map<String, Object> roomMap = new HashMap<>();
//		//外层循环课室类型，内层循环所有课室
//		for(int i=0; i<typeList.size(); i++) {
//			List<Rooms> temp = new ArrayList<>();
//			String roomTypeName = typeList.get(i).getRoomType();
//			if(roomList!=null && roomList.size()>0)
//				for(int j=0; j<roomList.size(); j++) {
//					Rooms room = roomList.get(j);
//					//找到对应类型的课室
//					if(roomTypeName.equals(room.getRoomType().getRoomType())) {
////						roomList.get(j).setRoomType(null);
//						temp.add(roomList.get(j));
//						System.out.println(roomList.get(j).getRoom());
////						roomList.remove(roomList.get(j));
//					}
//				}
////			roomMap.put(roomTypeName, temp);
//			for (Rooms rooms : temp) {
//				System.out.println(rooms.getRoom());
//			}
//			roomMap.put("type", roomTypeName);
//			roomMap.put("class", temp);
//			resultList.add(roomMap);
//		}
		
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
		if(roomMapper.update(room) > 0) {
			return new ResultBean(true,"修改成功");
		}else {
			return new ResultBean(false,"修改失败");
		}
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
		map.put("roomType", roomType);
		map.put("school", roomType.getSchool());

		List<RoomTypes> rtList = roomTypeMapper.find(map);
		if(rtList==null | rtList.size()==0) return new ResultBean(false,"删除失败");
		RoomTypes roomtype = rtList.get(0);
		
		//待删除课室是否存在
		map.put("room", roomName);
		map.put("roomType", roomtype);
		System.out.println(map.get("roomType"));
		List<Rooms> roomList = roomMapper.find(map);
		if(roomList==null | roomList.size()==0) return new ResultBean(false,"删除失败");
		
		if(roomMapper.updateById(roomList.get(0).getId()) > 0) {
			return new ResultBean(true, "删除成功");
		}else {
			return new ResultBean(false, "删除失败");
		}
	}
	
	@Override
	public ResultBean updateRoom(UpdateRoom updateRoom, Schools school) {
		Map<String, Object> map = new HashMap<>();
		//查询课室类型
		map.put("roomType", updateRoom.getRoomType());
		RoomTypes roomType = roomTypeMapper.find(map).get(0);
		//查询课室是否存在
		map.put("roomType", roomType);
		map.put("room", updateRoom.getOldRoom());
		map.put("school", school);
		List<Rooms> list = roomMapper.find(map);
		if(list==null | list.size()==0) {
			return new ResultBean(false, "该课室不存在");
		}
		//更新课室
		Rooms room = list.get(0);
		room.setRoom(updateRoom.getNewRoom());
		System.out.println(room.getRoomType().getId());
		if(roomMapper.update(room) > 0) {
			return new ResultBean(true, "修改成功");
		}else {
			return new ResultBean(false, "修改失败");
		}
	}
}
