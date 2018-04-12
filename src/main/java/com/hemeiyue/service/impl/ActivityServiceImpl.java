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
import com.hemeiyue.common.ResultActivity;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.ResultUser;
import com.hemeiyue.common.ResultUserInfoNull;
import com.hemeiyue.dao.ActivityMapper;
import com.hemeiyue.dao.ActivityUserMapper;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.ActivityUser;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.JSONUtil;
import com.hemeiyue.util.StringUtil;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{
	
	private static final String LOCALURL = "https://mcd.ngrok.xiaomiqiu.cn/hemeiyue" ;

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
		if(id == null || id == 0 || user.getId() == 0) {
			return new ResultBean(false,"user为空");
		}
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
		if(act.getImageUrl() != null || !act.getImageUrl().isEmpty()) {
			act.setImageUrl(LOCALURL + act.getImageUrl());
		}
		return new ResultActivity(true, act);
	}

	@Override
	public String findArtivityList(Schools school) {
		Activity activity = new Activity();
		activity.setDate(new Timestamp(System.currentTimeMillis()));
		activity.setSchool(school);
		List<ActivityModel> list =  activityMapper.findArtivityList(activity);
		if(list == null || list.size() == 0) {
			return JSONUtil.transform(new ResultBean(false));
		}
		for (ActivityModel activityModel : list) {
			if(activityModel.getImageUrl() != null || !activityModel.getImageUrl().isEmpty()) {
				activityModel.setImageUrl(LOCALURL+activityModel.getImageUrl());
			}
		}
		return JSONUtil.transform(list);
	}
	
	
	@Override
	public ResultBean updateWeChatScan(Users user, int activityId) {
		Map<String,Object> map = new HashMap<>();
		map.put("activityId", activityId);
		map.put("userId", user.getId());
		map.put("status", 0);
		List<ActivityUser> list = activityUserMapper.find(map);
		if(list.size() == 1) {				//用户参加了该活动
			ActivityUser activityUser = new ActivityUser();
			activityUser.setId(list.get(0).getId());
			activityUser.setSignTime(new Timestamp(System.currentTimeMillis()));	//插入签到时间
			activityUserMapper.updateSignTime(activityUser);
			user = usersMapper.selectByPrimaryKey(user.getId());
			return new ResultUser(true, user);
		}
		return new ResultBean(false,"扫码签到失败");
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

	@Override
	public ResultBean insertSignUpActivity(Integer activityId, Schools school, Users user) {
		if(school == null || user == null ) {
			return new ResultBean(false,"信息不完整");
		}
		if(StringUtil.StringIsNot(user.getClassRoom()) ||StringUtil.StringIsNot(user.getStudentNum())
				|| StringUtil.StringIsNot(user.getUserName())) {
			return new ResultUserInfoNull(false, true);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("activityId", activityId);
		map.put("school", school);
		if( activityMapper.find(map) == null || activityMapper.find(map).size() == 0) {			//判断活动是否存在
			return new ResultBean(false,"学校没有该活动");
		}
		Activity activity = activityMapper.find(map).get(0);
		
		if(activityUserMapper.findCountActivity(activityId) >= activity.getNumber()) {		//活动人数已满
			return new ResultBean(false, "人数已达上限");
		}
		if( activity.getSchool().getId() != school.getId()) {			//非本校学生不可参加
			return new ResultBean(false, "非本校学生不可参加");
		}
		
		map.put("userId", user.getId());
		if(activityUserMapper .find(map).size() != 0) {
			return new ResultBean(false, "不能重复报名");
		}
		ActivityUser activityUser = new ActivityUser();
		activityUser.setStatus(0);
		activityUser.setUser(user);
		activityUser.setActivity(activity);
		if(activityUserMapper.insert(activityUser) == 1) {
			return new ResultBean(true,"报名成功");
		}
		return new ResultBean(false, "报名失败");
	}
}
