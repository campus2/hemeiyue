package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;

public interface AdminService {
	public ResultBean login(Admin admin,HttpServletRequest request);
	
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
}
