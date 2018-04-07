package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.common.BookingModel;
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
	
	/**
	 * 返回申请列表
	 * @param schoolId
	 * @return
	 */
	public List<Bookings> findAllBooks(Integer schoolId);
	
	/**
	 * 返回申请列表
	 * @param userId
	 * @return
	 */
	public List<Bookings> findMyBooks(Integer userId);
	
	public List<Bookings> findSamePeriodBooks(Integer roomPeriodId);
	
	public List<BookingModel> findBookingModels(Bookings booking);
}