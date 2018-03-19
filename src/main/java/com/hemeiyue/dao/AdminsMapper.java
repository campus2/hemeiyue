package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;

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

	/**
	 * 查询指定学校的用户人数
	 * @param school 可为空，空时返回所有用户的人数
	 * @return 
	 */
	public Long getUserCount(Schools school);
}