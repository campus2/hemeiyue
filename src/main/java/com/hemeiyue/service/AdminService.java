package com.hemeiyue.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.hemeiyue.entity.Admin;

public interface AdminService {
	
	/**
	 * 添加管理员
	 * @param admin
	 */
	public void add(Admin admin);
	
	/**
	 * 管理员登录
	 * @param account
	 * @param password
	 * @param request
	 * @return
	 */
	public boolean login(Admin admin,HttpServletRequest request);
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	public boolean update(Admin admin);
	
	/**
	 * 
	 * @param admin
	 * @return
	 */
	public List<Admin> find(Admin admin);
	
	/**
	 * 返回当前管理员
	 * @param request
	 * @return
	 */
	public Admin current(HttpServletRequest request);
	
	/**
	 * 管理员审核
	 * @param currentAdmin
	 * @param applicant
	 * @return
	 */
	public Admin verify(Admin currentAdmin,Admin applicant);
	
	/**
	 * 删除管理员
	 * @param admin
	 * @return
	 */
	public boolean delete(Admin admin);
}
