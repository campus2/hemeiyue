package com.hemeiyue.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.AdminResult;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.AdminsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;

@Service("admin")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminsMapper adminMapper;
	
	@Override
	/**
	 * 登录
	 */
	public ResultBean login(Admin admin, HttpServletRequest request) {
		//获取对应账号的管理员信息
		Admin currentAdmin = adminMapper.login(admin);
		
		//检查账号与账户是否正确
		if(currentAdmin == null) {
			return new ResultBean(false, "用户或密码错误");
		}
		
		//检查该管理员是否被封停
		if(currentAdmin.getStatus() != 1) {
			return new ResultBean(false, "该管理员不可用");
		} 
		
		//检查该管理员的上级是否被封停
		if(currentAdmin.getParentId() > 1 && 
				adminMapper.selectByPrimaryKey(currentAdmin.getParentId()).getStatus() == 1){
			return new ResultBean(false, "该管理员的上级不可用");
		}
		
		request.getSession().setAttribute("currentAdmin", currentAdmin);
		
		int authority;
		if(currentAdmin.getParentId() < 0) {
			authority = 0;
		}else if (currentAdmin.getParentId() == 0) {
			authority = 1;
		}else {
			authority = 2;
		}
		AdminResult result = new AdminResult();
		result.setResult(true);
		result.setAuthority(authority);
		return result;
	}

	@Override
	public ResultBean register(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}
}
