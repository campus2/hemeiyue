package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.AdminResult;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.AdminsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.JSONUtil;

@Service("adminService")
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

	/*
	 * 陈冬修改部分
	 * @see com.hemeiyue.service.AdminService#findByAdmin(com.hemeiyue.entity.Admin)
	 */
	
	@Override
	public String findByAdmin(Admin admin) {
		//校验当前登录者为学校总管理员
		if(admin.getParentId() > 0) return null;
		
		//返回子管理员数据
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", admin.getParentId());
		List<Admin> sonList = adminMapper.find(map);
		return JSONUtil.transform(sonList);
	}

	@Override
	public ResultBean updateAdmin(Admin admin) {
		//校验管理员是否存在
		if(adminMapper.checkAccount(admin.getAccount()) == null) return new ResultBean(false,"该管理员不存在");
		
		//更新
		if(adminMapper.updateAdmin(admin) > 0)
			return new ResultBean(true, "修改成功");
		else {
			return new ResultBean(false, "修改失败");
		}
	}
	
	@Override
	public ResultBean deleteById(int id) {
		ResultBean result = null;
		if(adminMapper.deleteByPrimaryKey(id) > 0) {
			result = new ResultBean(true, "删除成功");
		}else {
			result = new ResultBean(false, "删除失败");
		}
		return result;
	}
	
	/*
	 * 陈冬修改结束
	 */
}
