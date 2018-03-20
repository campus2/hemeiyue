package com.hemeiyue.dao;

import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.Schools;

public interface BookingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bookings record);

    int insertSelective(Bookings record);

    Bookings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bookings record);

    int updateByPrimaryKey(Bookings record);

    /**
     * 返回指定学校的申请记录数，学校可为空，为空则返回所有
     * @param school
     * @return
     */
	public Long getApplyCount(Schools school);
}