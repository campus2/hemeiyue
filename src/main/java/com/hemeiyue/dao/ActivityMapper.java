package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.common.ActivityModel;
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

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Activity selectById(int id);
	
	/**
	 * 返回所有活动
	 * @return
	 */
	public List<ActivityModel> findAll();
	
	/**
	 * 根据Id返回model
	 * @param id
	 * @return
	 */
	public ActivityModel findById(Integer id);
	
	/**
	 * 返回学校活动列表
	 * @param schoolId
	 * @return
	 */
	public List<ActivityModel> findArtivityList(Integer schoolId);
	
	/**
	 * 返回未过期的活动信息
	 * @param activity
	 * @return
	 */
	public ActivityModel findActivityDetail(Activity activity);
	
}
