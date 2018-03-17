package com.hemeiyue.dao;

import com.hemeiyue.entity.Departments;

public interface DepartmentsMapper {
    int deleteById(Integer id);

    int insert(Departments record);

    Departments selectById(Integer id);

    int update(Departments record);
}