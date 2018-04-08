package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomTypeService;

@Service("roomTypeService")
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomtypeMapper roomTypeMapper;
	
	@Autowired
	private RoomsMapper roomsMapper;
	
	@Override
	public ResultBean insertRoomType(Schools school, String roomType) {
		if(roomType==null || roomType.isEmpty()) return new ResultBean(false, "课室类型不可为空");
		ResultBean result = new ResultBean();
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", roomType);
		map.put("school", school);
		List<RoomTypes> list = roomTypeMapper.find(map);
		if(list!=null && list.size()>0) {
			result.setResult(false);
			result.setMessage("该课室类型已存在");
		}else {
			System.out.println("a");
			RoomTypes recond = new RoomTypes(roomType);
			recond.setSchool(school);
			roomTypeMapper.insert(recond);
			result.setResult(true);
			result.setMessage("添加课室类型成功");
		}
		return result;
	}

	@Override
	public ResultBean updateRoomType(RoomTypes oldRoomType, String newRoomType) {
		//查询待修改的课室类型
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", oldRoomType);
		map.put("school", oldRoomType.getSchool());
		List<RoomTypes> list = roomTypeMapper.find(map);
		
		RoomTypes roomType = null;
		ResultBean resultBean = new ResultBean(false);
		if(list!=null && list.size()>0) {
			roomType = list.get(0);
			//修改课室类型
			roomType.setRoomType(newRoomType);
			//持久性更新
			if(roomTypeMapper.update(roomType) > 0) {
				resultBean.setResult(true);
				resultBean.setMessage("修改成功");
			}
		}
		return resultBean;
	}

	@Override
	public ResultBean delete(RoomTypes roomType) {
		//查询待删除课室类型是否存在
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", roomType);
		map.put("school", roomType.getSchool());
		
		List<RoomTypes> roomTypeList = roomTypeMapper.find(map);
		if(roomTypeList==null | roomTypeList.size()==0) return new ResultBean(false);
		
		RoomTypes delRoomType = roomTypeList.get(0);
		//删除其关联的所有课室，标志为改为-1
		map.put("roomType", delRoomType);
		List<Rooms> roomsList = roomsMapper.find(map);
		if(roomsList!=null && roomsList.size()>0) {
			for (Rooms rooms : roomsList) {
				roomsMapper.updateById(rooms.getId());
			}
		}
		
		//删除课室类型，标志为改为-1
		ResultBean result = new ResultBean();
		if(roomTypeMapper.updateById(delRoomType.getId()) > 0) {
			result.setResult(true);
			result.setMessage("删除成功");
		}
		
		return result;
	}

	@Override
	public RoomTypes selectBySchoolAndRoomType(Schools school, String roomType) {
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		map.put("roomType", roomType);
		
		return roomTypeMapper.select(map);
	}

}
