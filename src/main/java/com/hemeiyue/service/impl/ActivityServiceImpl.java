package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.ActivityMapper;
import com.hemeiyue.dao.ActivityUserMapper;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.ActivityUser;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.JSONUtil;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private ActivityUserMapper activityUserMapper;
	
	@Override
	public ResultBean insertActivity(Activity activity) {
		if(activityMapper.insert(activity) > 0) {
			return new ResultBean(true, "添加成功");
		}
		return new ResultBean(false, "添加失败");
	}

	@Override
	public String findActivity(Schools school) {
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		List<Activity> list = activityMapper.find(map);
		return JSONUtil.transform(list);
	}

	@Override
	public ResultBean deleteById(int activityId) {
		if(activityMapper.deleteById(activityId) > 0) {
			return new ResultBean(true, "删出成功");
		}
		return new ResultBean(false, "删除失败");
	}

	@Override
	public String getUsersByActivity(Activity activity) {
		Map<String, Object> map = new HashMap<>();
		map.put("activityId", activity.getId());
		List<ActivityUser> list = activityUserMapper.find(map);
		
		//抽取报名用户
		List<Users> userList = new ArrayList<>();
		for (ActivityUser au : list) {
			userList.add(au.getUser());
		}
		return JSONUtil.transform(userList);
	}
}
