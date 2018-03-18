package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;

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
}
