package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomTypeService;

@Service("roomTypeService")
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomtypeMapper roomTypeMapper;
	
	@Override
	public ResultBean insertRoomType(int schoolId, String roomType) {
		ResultBean result = new ResultBean();
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", roomType);
		map.put("schoolId", schoolId);
		List<RoomTypes> list = roomTypeMapper.find(map);
		if(list!=null && list.size()>0) {
			result.setResult(false);
			result.setMessage("该课室类型已存在");
		}else {
			System.out.println("a");
			RoomTypes recond = new RoomTypes(roomType);
			recond.setSchool(new Schools(schoolId));
			roomTypeMapper.insert(recond);
			result.setResult(true);
		}
		return result;
	}

}
