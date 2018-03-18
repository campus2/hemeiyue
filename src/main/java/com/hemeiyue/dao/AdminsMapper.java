package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

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
    
    /**
     * 根据map中的条件返回指定的数据集
     * @param map
     * @return
     */
	public List<Admin> find(Map<String, Object> map);
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	public int updateAdmin(Admin admin);
}