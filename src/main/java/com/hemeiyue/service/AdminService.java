package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;

public interface AdminService {
	/**
	 * 管理员登录
	 * @param admin
	 * @param request
	 * @return
	 */
	public ResultBean login(Admin admin,HttpServletRequest request);
	
	/**
	 * 管理员信息注册
	 * @param admin
	 * @param request
	 * @return
	 */
	public ResultBean insert(Admin admin,HttpServletRequest request);
	
	/**
	 * 返回全部注册中的租户
	 * @param request
	 * @return
	 */
	public ResultBean tenantApplyList(HttpServletRequest request);
	
	/**
	 * 同意该租户的申请
	 * @param id
	 * @return
	 */
	public ResultBean tenantApply(Integer id);
	
	/**
	 * 异步校验账号是否已存在
	 * @param account
	 * @return
	 */
	public ResultBean validationAccount(String account);
	
	/**
	 * 删除注册中的租户
	 * @param id
	 * @return
	 */
	public ResultBean deleteTenant(int id);
	
	/**
	 * 返回已注册成功的租户的列表
	 * @param request
	 * @return
	 */
	public ResultBean tenantMangerList(HttpServletRequest request);
	
	/**
	 * 停用租户
	 * @param id
	 * @return
	 */
	public ResultBean suspendedTenant(int id);
	
	/**
	 * 恢复停用的租户
	 * @param id
	 * @return
	 */
	public ResultBean restoreTenant(int id);

	public ResultBean register(Admin admin);

	/**
	 * 返回指定管理员手下的子管理员
	 * @param admin
	 * @return
	 */
	
	public String findByAdmin(Admin admin);

	/**
	 * 修改admin信息
	 * @param admin
	 * @return
	 */
	public ResultBean updateAdmin(Admin admin);

	/**
	 * 根据id删除管理员
	 * @param id
	 * @return
	 */
	public ResultBean deleteById(int id);

	/**
	 * 查询指定学校的用户人数
	 * @param school 可为空，空时返回所有用户的
	 * @return 
	 */
	public String getUserCount(Schools school);

	/**
	 * 返回指定学校的申请总数，为空则返回全部
	 * @param school
	 * @return
	 */
	public String getAllApply(Schools school);

	/**
	 * 返回学校记录总数
	 * @return
	 */
	public String getAllSchools();

	/**
	 * 返回指定学校的课室记录数
	 * @return
	 */
	public String getAllRooms(Schools school);
}
