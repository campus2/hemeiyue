package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.SchoolAdmins;

public interface SchooladminsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolAdmins record);

    int insertSelective(SchoolAdmins record);

    SchoolAdmins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolAdmins record);

    int updateByPrimaryKey(SchoolAdmins record);
    
    List<SchoolAdmins> selectBySchoolId(Integer id);
    
    List<SchoolAdmins> selectByParentId(Integer id);
}