package com.hemeiyue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;

public interface AdminsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectById(Integer id);
    
    Admin selectAdminById(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin login(Admin admin);
    
    List<Admin> selectAll(Admin admin);
    
    Admin checkAccount(String account);
    
    List<Admin> selecTenant(@Param("parentId")Integer parentId,@Param("regStatus")Integer regStatus);
    
    int updateStatus(Admin admin);

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
	 * 查找管理员密码
	 * @param admin
	 * @return
	 */
	public Admin findPassword(Admin admin);

	/**
	 * 查询指定学校的用户人数
	 * @param school 可为空，空时返回所有用户的人数
	 * @return 
	 */
	public Long getUserCount(Schools school);

	/**
	 * 返回租户管理列表
	 * @param id
	 * @param i
	 * @return
	 */
	List<Admin> selecTenantManageList(@Param("parentId")Integer parentId, @Param("regStatus")int regStatus);
	
	public List<Admin> findAdmin(Map<String, Object> map);

	/**
	 * 返回管理员人数
	 * @param school
	 * @return
	 */
	Long getAdminCount(Schools school);
	
}