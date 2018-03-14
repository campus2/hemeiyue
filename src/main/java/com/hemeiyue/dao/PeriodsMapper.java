package com.hemeiyue.dao;

import com.hemeiyue.entity.Period;

public interface PeriodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Period record);

    int insertSelective(Period record);

    Period selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Period record);

    int updateByPrimaryKey(Period record);
}