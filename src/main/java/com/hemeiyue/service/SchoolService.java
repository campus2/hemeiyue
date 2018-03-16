package com.hemeiyue.service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Schools;

public interface SchoolService {
	
	/**
	 * 根据id查询学校
	 * @param id
	 * @return
	 */
	public Schools selectById(int id);
	
	//   updateByIdSelective updateById
	
	/**
	 * 根据id删除学校
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	
	/**
	 * 添加学校
	 * @param school
	 * @return
	 */
	public ResultBean insert(Schools school);
	
	/**
	 * 更新学校
	 * @param school
	 * @return
	 */
	public int update(Schools school);
	
	

}
