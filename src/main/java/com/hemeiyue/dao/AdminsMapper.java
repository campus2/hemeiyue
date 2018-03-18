package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.Admin;

public interface AdminsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin login(Admin admin);
    
    List<Admin> selectAll(Admin admin);
    
    Admin checkAccount(String account);
    
    List<Admin> selecTenant(Integer regStatus);
    
    int updateStatus(Integer id,Integer status);
}