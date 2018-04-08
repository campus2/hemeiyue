package com.hemeiyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hemeiyue.entity.Periods;

public interface PeriodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Periods record);

    Periods selectById(Integer id);

    int updateByPrimaryKeySelective(Periods record);

    int updateByPrimaryKey(Periods record);

    /**
     * 批量插入
     * @param pList
     * @return
     */
	int insertList(@Param("list")List<Periods> list);
}