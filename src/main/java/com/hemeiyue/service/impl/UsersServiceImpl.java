package com.hemeiyue.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.ResultSchool;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.dao.MessagesMapper;
import com.hemeiyue.dao.UsersMapper;
import com.hemeiyue.entity.Bookings;
import com.hemeiyue.entity.Messages;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;
import com.hemeiyue.util.WX_Util;

@Service("userService")
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private MessagesMapper messagesMappers;
	
	@Autowired
	private BookingsMapper bookingsMapper;
	
	@Override
	public ResultBean login(String code,HttpServletRequest request) {
		Map<String, String> map = WX_Util.getOpenId(code);
		String openId = map.get("openId");
		String session_key = map.get("session_key");
		
		//如果openId为空，返回false
		if(openId == null) {
			return new ResultBean(false,"没有openId");
		}
		
		Users user = usersMapper.selectByOpenId(openId);
		if (user == null) {		//本地数据库不存在该用户（第一次登录）
			user = new Users();
			user.setOpenId(openId);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("session_key", session_key);
			return new ResultBean(false);
		}else {							//用户非第一次登录
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute(session_key, session_key);
			request.getSession().setAttribute("school", user.getSchool());
			return new ResultSchool(true, user.getSchool().getSchool());
		}
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

	@Override
	public ResultList userMessage(Integer userId) {
		List<Messages> list = messagesMappers.findUserMessage(userId);
		ResultList result = new ResultList();
		if(list != null && list.size() > 0) {
			result.setResult(true);
			result.setList(list);
			return result;
		}
		result.setResult(false);
		return result;
	}

	@Override
	public String reserve(Integer userId) {
		
		return null;
	}

}
