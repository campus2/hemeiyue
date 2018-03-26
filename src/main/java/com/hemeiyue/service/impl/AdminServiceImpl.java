package com.hemeiyue.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.AdminResult;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.AdminsMapper;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.dao.RoomsMapper;
import com.hemeiyue.dao.SchoolsMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.MD5;
import com.hemeiyue.util.PhoneFormatCheckUtils;
import com.hemeiyue.util.SendMailUtil;
import com.hemeiyue.util.JSONUtil;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminsMapper adminMapper;
	
	@Autowired
	private BookingsMapper bookingMaper;
	
	@Autowired
	private SchoolsMapper schoolsMapper;
	
	@Autowired
	private RoomsMapper roomMapper;
	
	public ResultBean login(Admin admin) {
		String salt = adminMapper.checkAccount(admin.getAccount()).getSalt();
		admin.setPassword(MD5.MD5encoder(admin.getPassword()+ salt));
		Admin currentAdmin = adminMapper.login(admin);
		AdminResult result = new AdminResult();
		result.setResult(true);
		return result;
	}
	
	/**
	 * 登录
	 */
	public ResultBean login(Admin admin, HttpServletRequest request) {
		//获取对应账号的管理员信息
		String salt = adminMapper.checkAccount(admin.getAccount()).getSalt();
		System.out.println(salt);
		System.out.println(admin.getAccount() + "   " + admin.getPassword());
		admin.setPassword(MD5.MD5encoder(admin.getPassword()+ salt));
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
				adminMapper.selectById(currentAdmin.getParentId()).getStatus() == 1){
			return new ResultBean(false, "该管理员的上级不可用");
		}
		
		request.getSession().setAttribute("currentAdmin", currentAdmin);
		request.getSession().setAttribute("school", currentAdmin.getSchool());
		
		int authority;
		if(currentAdmin.getParentId() < 0) {
			authority = -1;
		}else if (currentAdmin.getParentId() == 0) {
			authority = 0;
		}else {
			authority = 1;
		}
		AdminResult result = new AdminResult();
		result.setResult(true);
		result.setAuthority(authority);
		result.setAdmin(currentAdmin);
		return result;
	}

	@Override
	public ResultBean insert(Admin admin,HttpServletRequest request) {
		if(adminMapper.checkAccount(admin.getAccount()) != null) {
			return new ResultBean(false, "该账号已存在");
		}
		//获取当前管理员
		Admin currentAdmin = (Admin) request.getSession().getAttribute("currentAdmin");
		
		if(currentAdmin == null) {
			admin.setParentId(0);
		}else{
			admin.setParentId(currentAdmin.getId());
		}
		
		//检查电话号码
		if(!PhoneFormatCheckUtils.isPhoneLegal(admin.getPhone())) {
			return new ResultBean(false, "电话号码格式错误");
		}
		
		//设置状态为注册中
		admin.setRegStatus(0);
		admin.setStatus(0);
		
		//设置salt 0~1000
		int salt = (int) (Math.random()*1000);
		admin.setSalt(String.valueOf(salt));
		System.out.println(salt);
		
		//把加盐后的密码存进去
		String newPassword = MD5.MD5encoder(admin.getPassword()+salt);
		admin.setPassword(newPassword);
		if(adminMapper.insertSelective(admin) == 1) {
			SendMailUtil send = new SendMailUtil(admin.getEmail(), "注册成功", admin.getAdminName() + "已注册成功，请耐心等待管理员确认");
			send.start();
			return new ResultBean(true, "注册成功");
		}else {
			return new ResultBean(false, "注册失败");
		}
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
			return new ResultBean(false,"该账号已被注册");
		}
		return new ResultBean(true,"该账号可用");
	}

	@Override
	public ResultBean deleteTenant(int id) {
		if(adminMapper.selectById(id).getRegStatus()==1) {
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
		Admin admin = new Admin();
		admin.setId(id);
		admin.setStatus(0);
		if(adminMapper.selectById(id).getRegStatus() == 1 &&
				adminMapper.updateStatus(admin)== 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}

	@Override
	public ResultBean restoreTenant(int id) {
		Admin admin = new Admin();
		admin.setId(id);
		admin.setStatus(1);
		if(adminMapper.selectById(id).getRegStatus() == 1 &&
				adminMapper.updateStatus(admin)== 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
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
		if(adminMapper.deleteByPrimaryKey(id) > 0) {
			return new  ResultBean(true, "删除成功");
		}else {
			return new ResultBean(false, "删除失败");
		}
	}

	@Override
	public String getUserCount(Schools school) {
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		Long count = adminMapper.getUserCount(school);
		return String.valueOf(count);
	}
	
	@Override
	public String getAllApply(Schools school) {
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		Long count = bookingMaper.getApplyCount(school);
		return String.valueOf(count);
	}
	
	@Override
	public String getAllSchools() {
		Map<String, Object> map = new HashMap<>();
		Long count = schoolsMapper.getTotal(map);
		return String.valueOf(count);
	}
	
	@Override
	public String getAllRooms(Schools school) {
		Map<String, Object> map = new HashMap<>();
		map.put("school", school);
		Long count = roomMapper.getTotal(map);
		return String.valueOf(count);
	}
	
	/*
	 * 陈冬修改结束
	 */
	@Override
	public ResultBean register(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultBean findPassword(Admin admin) {
		String salt = adminMapper.selectById(admin.getId()).getSalt();
		admin.setPassword(MD5.MD5encoder(admin.getPassword()+ salt));
		if(adminMapper.findPassword(admin) != null) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}
}
