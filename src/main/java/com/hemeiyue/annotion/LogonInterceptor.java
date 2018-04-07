package com.hemeiyue.annotion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.util.ResponseUtil;

/**
 * 判断登录状态
 * @author cedo
 *
 */
public class LogonInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		AuthLoginAnnotation auth = method.getMethod().getAnnotation(AuthLoginAnnotation.class);
		
		if(auth != null && auth.checkLogin()) {
			System.out.println("logon test");
//			Admin currentAdmin = (Admin)request.getSession().getAttribute("currentAdmin");
			Admin currentAdmin = (Admin)request.getServletContext().getAttribute("currentAdmin");
			if(currentAdmin==null) {
				System.out.println("请先登录");
				ResponseUtil.write(response, new ResultBean(false, "请先登录"));
				return false;
			}else {
				return true;
			}
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
