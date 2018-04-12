package com.hemeiyue.service;


import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;

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
	public ResultList getUsersByActivity(Activity activity);

	/**
	 * 根据id查询
	 * @param activityId
	 * @return
	 */
	public Activity selectById(int id);
	
	/**
	 * 根据Id返回活动信息
	 * @param id
	 * @param user
	 * @return
	 */
	public ResultBean findById(Integer id,Users user);
	
	/**
	 * 返回对应学校的活动列表
	 * @param school
	 * @return
	 */
	public String findArtivityList(Schools school);
	
	/**
	 * 用户扫码签到
	 * @param code
	 * @param activityId
	 * @param request
	 * @return
	 */
	public ResultBean updateWeChatScan(Users user, int activityId);

	/**
	 * 更新活动
	 * @param activity
	 * @return
	 */
	public ResultBean updateActivity(Activity activity);
	
	/**
	 * 用户参加活动
	 * @param activityId
	 * @param userId
	 * @return
	 */
	public String insertActivityApply(Integer activityId,Integer userId);
	
	/**
	 * 用户报名参加活动
	 * @param activityId
	 * @param school
	 * @param user
	 * @return
	 */
	public ResultBean insertSignUpActivity(Integer activityId,Schools school,Users user);
}
