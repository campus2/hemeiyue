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
	
	/**
	 * 返回未过期的用户个人预定
	 * @param booking
	 * @return
	 */
	public List<Bookings> findMyBooksWithoutTimeOut(Bookings booking);
	
	/**
	 * 返回roomPeridId下的申请
	 * @param roomPeridId
	 * @return
	 */
	public List<Bookings> findBooksByRoomPeriod(Integer roomPeridId);

	/**
	 * 删除课室后，拒绝申请
	 * @param substring
	 * @return
	 */
	int updateByDelete(List<Integer> list);
}