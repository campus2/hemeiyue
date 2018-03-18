package com.hemeiyue.dao;

import com.hemeiyue.entity.Periods;

public interface PeriodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Periods record);

    Periods selectById(Integer id);

    int updateByPrimaryKeySelective(Periods record);

    int updateByPrimaryKey(Periods record);
}