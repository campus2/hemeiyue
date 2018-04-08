package com.hemeiyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ActivityUserModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.ActivityMapper;
import com.hemeiyue.dao.ActivityUserMapper;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.ActivityUser;
import com.hemeiyue.entity.Schools;
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
		List<Activity> list = activityMapper.findActivityDesc(map);
		if(list!=null && list.size()>0) {
			return JSONUtil.transform(new ResultList(true,list));
		}
		return JSONUtil.transform(new ResultList(false,"暂无活动"));
	}

	@Override
	public ResultBean deleteById(int activityId) {
		if(activityMapper.deleteById(activityId) > 0) {
			return new ResultBean(true, "删出成功");
		}
		return new ResultBean(false, "删除失败");
	}

	@Override
	public ResultList getUsersByActivity(Activity activity) {
		Map<String, Object> map = new HashMap<>();
		map.put("activityId", activity.getId());
		List<ActivityUser> list = activityUserMapper.find(map);
		
		//抽取报名用户和签到信息
		List<ActivityUserModel> userList = new ArrayList<>();
		for (ActivityUser au : list) {
			ActivityUserModel aum = new ActivityUserModel();
			aum.setUserInfo(au.getUser());
			aum.setStatus(au.getStatus());
			userList.add(aum);
		}
		System.out.println(JSONUtil.transform(userList));
		return new ResultList(true, userList);
	}
	
	@Override
	public Activity selectById(int id) {
		
		return activityMapper.selectById(id);
	}

	@Override
	public ResultBean updateActivity(Activity activity) {
		if(activityMapper.update(activity) > 0) {
			return new ResultBean(true, "操作成功");
		}
		return new ResultBean(false, "操作失败");
	}
}
