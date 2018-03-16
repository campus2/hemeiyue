package com.hemeiyue.dao;

import com.hemeiyue.entity.Departments;

public interface DepartmentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Departments record);

    int insertSelective(Departments record);

    Departments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);
}