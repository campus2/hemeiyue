package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.ActivityUser;

public interface ActivityUserMapper {

	/**
	 * 根据指定条件返回数据
	 * @param map
	 * @return
	 */
	public List<ActivityUser> find(Map<String, Object> map);
	
	/**
	 * 添加活动报名用户信息
	 * @param activityUser
	 * @return
	 */
	public int insert(ActivityUser activityUser);
	
	/**
	 * 活动参加人数
	 * @param id
	 * @return
	 */
	public int findCountActivity(Integer id);
	
	public int updateSignTime(ActivityUser activityUser);

}
