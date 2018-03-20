package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.Schools;

public interface SchoolsMapper {
    
	public int deleteById(Integer id);

    public int insert(Schools record);

    public Schools selectById(Integer id);

    public int update(Schools record);
    
    /**
	 * 根据指定的条件返回数据集合
	 * @param map 
	 * @return
	 */
	public List<Schools> find(Map<String, Object> map);

	/**
	 * 返回符合条件的记录总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	
	public int findSchool(String school);
}