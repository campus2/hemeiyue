package com.hemeiyue.dao;

import com.hemeiyue.entity.SchoolAdmins;

public interface SchooladminsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolAdmins record);

    int insertSelective(SchoolAdmins record);

    SchoolAdmins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolAdmins record);

    int updateByPrimaryKey(SchoolAdmins record);
}