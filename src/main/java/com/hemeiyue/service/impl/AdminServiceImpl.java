package com.hemeiyue.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.dao.AdminsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;

@Service("admin")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminsMapper adminMapper;
	
	@Override
	public void add(Admin admin) {
		// TODO Auto-generated method stub
		adminMapper.insertSelective(admin);
	}

	@Override
	public boolean login(Admin admin, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Admin currentAdmin;
		currentAdmin = adminMapper.login(admin);
		if(currentAdmin != null) {
			request.getSession().setAttribute("currentAdmin", currentAdmin);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Admin admin) {
		// TODO Auto-generated method stub
		if(adminMapper.selectByPrimaryKey(admin.getId()) != null) {
			adminMapper.updateByPrimaryKeySelective(admin);
			return true;
		}
		return false;
	}

	@Override
	public List<Admin> find(Admin admin) {
		// TODO Auto-generated method stub
		List<Admin> list;
		list = adminMapper.selectAll(admin);
		return list;
	}

	@Override
	public Admin current(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		return adminMapper.selectByPrimaryKey(currentAdmin.getId());
	}

	@Override
	public Admin verify(Admin currentAdmin, Admin applicant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Admin admin) {
		// TODO Auto-generated method stub
		admin.setStatus(2);
		if(adminMapper.selectByPrimaryKey(admin.getId()) != null) {
			adminMapper.updateByPrimaryKeySelective(admin);
			return true;
		}
		return false;
	}

}
