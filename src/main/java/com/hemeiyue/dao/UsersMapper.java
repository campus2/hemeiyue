package com.hemeiyue.dao;

import com.hemeiyue.common.UsersModel;
import com.hemeiyue.entity.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    Users selectByOpenId(String openid);
    
    UsersModel selectPersonalInfo(Integer id);
    
    int updatePersonalInfo(UsersModel user);
}