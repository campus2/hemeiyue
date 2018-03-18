package com.hemeiyue.service;

import java.util.List;

import com.hemeiyue.entity.SchoolAdmins;

public interface SchoolAdminsService {
	
	/**
	 * 添加学校管理员
	 * @param schoolAdmin
	 * @return
	 */
	public boolean add(SchoolAdmins schoolAdmin);
	
	/**
	 * 修改管理员信息
	 * @param schoolAdmin
	 * @return
	 */
	public boolean modify(SchoolAdmins schoolAdmin);
	
	/**
	 * 删除管理员
	 * @param schoolAdmin
	 * @return
	 */
	public boolean delete(SchoolAdmins schoolAdmin);
	
	/**
	 * 查找某个学校的管理员
	 * @param schoolId
	 * @return
	 */
	public List<SchoolAdmins> selectBySchoolId(Integer schoolId);
    
	/**
	 * 查找某个管理员管理的学校管理员
	 * @param parenetId
	 * @return
	 */
    public List<SchoolAdmins> selectByParentId(Integer parenetId);
    
    
}
