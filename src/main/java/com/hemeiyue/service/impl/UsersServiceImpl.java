package com.hemeiyue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;

@Service("userService")
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public ResultBean login(String openid) {
		ResultBean result = new ResultBean();
		if(usersMapper.selectByOpenId(openid) != null) {
			result.setResult(true);
			result.setMessage("该用户曾经登录过");
		}else {
			Users user = new Users();
			user.setOpenId(openid);
			usersMapper.insertSelective(user);
			result.setResult(false);
			result.setMessage("该用户第一次登录，已完成注册");
		}
		return result;
	}

	@Override
	public ResultBean update(Users user) {
		ResultBean result = new ResultBean();
		if(usersMapper.updateByPrimaryKeySelective(user) == 1) {
			result.setResult(true);
			result.setMessage("修改成功");
		}else {
			result.setResult(false);
			result.setMessage("修改失败");
		}
		return result;
	}

	@Override
	public ResultBean delete(Users user) {
		ResultBean result = new ResultBean();
		if(usersMapper.deleteByPrimaryKey(user.getId()) == 1) {
			result.setResult(true);
			result.setMessage("删除成功");
		}else {
			result.setResult(false);
			result.setMessage("删除失败");
		}
		return result;
	}

}
