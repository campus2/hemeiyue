package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Schools;

public interface ActivityService {

	/**
	 * 添加活动
	 * @param activity
	 * @return
	 */
	public ResultBean insertActivity(Activity activity);

	/**
	 * 返回指定学校的活动信息
	 * @param school
	 * @return
	 */
	public String findActivity(Schools school);

	/**
	 * 根据id删除活动
	 * @param activityId
	 * @return
	 */
	public ResultBean deleteById(int activityId);

	/**
	 * 返回指定活动的报名用户
	 * @param activity
	 * @return
	 */
	public String getUsersByActivity(Activity activity);

	/**
	 * 根据id查询
	 * @param activityId
	 * @return
	 */
	public Activity selectById(int id);

	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	public ResultBean updateActivity(Activity activity);

}
