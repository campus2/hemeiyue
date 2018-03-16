package com.hemeiyue.dao;

import com.hemeiyue.entity.Schools;

public interface SchoolsMapper {
    
	public int deleteById(Integer id);

    public int insert(Schools record);

    public Schools selectById(Integer id);

    public int update(Schools record);
}