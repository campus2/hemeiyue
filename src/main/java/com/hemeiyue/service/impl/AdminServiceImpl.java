package com.hemeiyue.service.impl;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.AdminResult;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.AdminsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.MD5;

@Service("admin")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminsMapper adminMapper;
	
	/**
	 * 登录
	 */
	public ResultBean login(Admin admin, HttpServletRequest request) {
		//获取对应账号的管理员信息
		admin.setPassword(MD5.MD5encoder(admin.getPassword()+admin.getSalt()));
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
	public ResultBean insert(Admin admin,HttpServletRequest request) {
		//获取当前管理员
		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		
		if(currentAdmin.getParentId() > 1) {
			return new ResultBean(false, "学校管理员没有权限执行该操作");
		}else{
			admin.setParentId(currentAdmin.getId());
		}
		
		//设置状态为注册中
		admin.setRegStatus(0);
		admin.setStatus(0);
		
		//设置salt
		Random random = new Random();
		admin.setSalt(String.valueOf(random.nextInt()));
		
		//把加盐后的密码存进去
		String newPassword = MD5.MD5encoder(admin.getPassword()+admin.getSalt());
		admin.setPassword(newPassword);
		adminMapper.insertSelective(admin);
		return new ResultBean(true, "新管理员信息已录入");
	}
	
	@Override
	public ResultBean tenantApplyList(HttpServletRequest request) {
		ResultList resultList = new ResultList();
		//获取当前管理员
		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getId() != 0) {
			return new ResultBean(false, "非超级管理员不可操作");
		}
		resultList.setList(adminMapper.selecTenant(0));
		return resultList;
	}

	@Override
	public ResultBean tenantApply(Integer id) {
		Admin admin = new Admin();
		admin.setId(id);
		admin.setStatus(1);
		admin.setRegStatus(1);
		if(adminMapper.updateByPrimaryKeySelective(admin) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}


	@Override
	public ResultBean validationAccount(String account) {
		if(adminMapper.checkAccount(account) != null) {
			return new ResultBean(false);
		}
		return new ResultBean(true);
	}

	@Override
	public ResultBean deleteTenant(int id) {
		if(adminMapper.selectByPrimaryKey(id).getRegStatus()==1) {
			return new ResultBean(false);
		}
		if(adminMapper.deleteByPrimaryKey(id) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}

	@Override
	public ResultBean tenantMangerList(HttpServletRequest request) {
		ResultList resultList = new ResultList();
		//获取当前管理员
		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		if(currentAdmin.getId() != 0) {
			return new ResultBean(false, "非超级管理员不可操作");
		}
		resultList.setList(adminMapper.selecTenant(1));
		return resultList;
	}

	@Override
	public ResultBean suspendedTenant(int id) {
		if(adminMapper.selectByPrimaryKey(id).getRegStatus() == 1 &&
				adminMapper.updateStatus(id, 0)== 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}

	@Override
	public ResultBean restoreTenant(int id) {
		if(adminMapper.selectByPrimaryKey(id).getRegStatus() == 1 &&
				adminMapper.updateStatus(id, 1)== 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}
}
