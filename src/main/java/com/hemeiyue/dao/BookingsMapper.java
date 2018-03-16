package com.hemeiyue.dao;

import com.hemeiyue.entity.Bookings;

public interface BookingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bookings record);

    int insertSelective(Bookings record);

    Bookings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bookings record);

    int updateByPrimaryKey(Bookings record);
}