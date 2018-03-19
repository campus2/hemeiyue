package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.Activity;

public interface ActivityMapper {

	/**
	 * 添加活动
	 * @param activity
	 * @return
	 */
	public int insert(Activity activity);

	/**
	 * 根据指定条件返回活动信息
	 * @param map
	 * @return
	 */
	public List<Activity> find(Map<String, Object> map);

	/**
	 * 根据id删除活动
	 * @param activityId
	 * @return
	 */
	public int deleteById(int id);

}
