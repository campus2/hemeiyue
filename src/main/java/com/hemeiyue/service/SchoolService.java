package com.hemeiyue.service;

import java.util.Map;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;

public interface SchoolService {
	
	/**
	 * 根据id查询学校
	 * @param id
	 * @return
	 */
	public Schools selectById(int id);
	
	
	/**
	 * 根据id删除学校
	 * @param id
	 * @return
	 */
	public ResultBean deleteById(int id);
	
	/**
	 * 添加学校
	 * @param school
	 * @return
	 */
	public int insert(Schools school);
	
	/**
	 * 更新学校
	 * @param school
	 * @return
	 */
	public ResultBean update(Schools school);

	/**
	 * 根据指定的条件返回数据集合
	 * @param map 
	 * @return
	 */
	public String find(Map<String, Object> map);
	
	
	/**
	 * 查找学校是否被注册了
	 * @param school
	 * @return
	 */
	public ResultBean findSchool(String school);
	
	/**
	 * 模糊查询学校  结果集最多五条
	 * @param school
	 * @return
	 */
	public ResultBean selectSchool(String school);
	
	/**
	 * 通过学校名查询改学校是否存在（该学校已注册），如果存在，把openid，学校等信息放进数据库，并把学校名返回
	 * @param school
	 * @param user
	 * @return
	 */
	public ResultBean insertHandleSchool(String school,Users user);
	
}
