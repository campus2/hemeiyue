package com.hemeiyue.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ActivityModel;
import com.hemeiyue.common.ActivityShowModel;
import com.hemeiyue.common.ActivityUserModel;
import com.hemeiyue.common.ActivityUserShowModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.ResultObeject;
import com.hemeiyue.dao.ActivityMapper;
import com.hemeiyue.dao.ActivityUserMapper;
import com.hemeiyue.dao.UsersMapper;
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
	
	@Autowired
	private UsersMapper usersMapper;
	
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
	public ResultBean findById(Integer id,Users user) {
		Activity activity = new Activity();
		activity.setId(id);
		activity.setTime(new Timestamp(System.currentTimeMillis()));
		ActivityShowModel act = activityMapper.findActivityDetail(activity);
		if(act == null)
			return new ResultBean(false);
		Map<String,Object> map  = new HashMap<>();
		map.put("activityId", id);
		map.put("userId", user.getId());
		if(activityUserMapper.find(map).size() == 1) {
			act.setStatus(1);
		}else {
			act.setStatus(0);
		}
		return new ResultObeject(true, act);
	}

	@Override
	public String findArtivityList(Schools school) {
		List<ActivityModel> list =  activityMapper.findArtivityList(school.getId());
		if(list == null || list.size() == 0) {
			return JSONUtil.transform(new ResultBean(false));
		}
		return JSONUtil.transform(list);
	}
	
	
	@Override
	public String updateWeChatScan(Users user, int activityId) {
		Map<String,Object> map = new HashMap<>();
		map.put("activityId", activityId);
		map.put("userId", user.getId());
		map.put("status", 1);
		List<ActivityUser> list = activityUserMapper.find(map);
		if(list.size() == 1) {				//用户参加了该活动
			ActivityUser activityUser = new ActivityUser();
			activityUser.setId(list.get(0).getId());
			activityUser.setSignTime(new Timestamp(System.currentTimeMillis()));	//插入签到时间
			activityUserMapper.updateSignTime(activityUser);
			user = usersMapper.selectByPrimaryKey(user.getId());
			return JSONUtil.transform(new ResultObeject(true, user));
		}
		return JSONUtil.transform(new ResultBean(false));
	}
	
	public ResultBean updateActivity(Activity activity) {
		if(activityMapper.update(activity) > 0) {
			return new ResultBean(true, "操作成功");
		}
		return new ResultBean(false, "操作失败");
	}

	@Override
	public String insertActivityApply(Integer activityId, Integer userId) {
		Integer sign = activityUserMapper.findCountActivity(activityId);
		if(sign >= activityMapper.findById(activityId).getCount()) {
			return JSONUtil.transform(new ResultBean(false,"报名人数已满"));
		}
		ActivityUserShowModel activityUser = new ActivityUserShowModel();
		activityUser.setActivityId(activityId);
		activityUser.setStatus(0);
		activityUser.setUserId(userId);
		if(activityUserMapper.insertByActivityUserShowModel(activityUser) == 1) {
			return JSONUtil.transform(new ResultBean(true));
		}
		return JSONUtil.transform(new ResultBean(false));
	}
}
