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
 * 校验管理员权限
 * @author cedo
 *
 */
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		AuthLoginAnnotation auth = method.getMethod().getAnnotation(AuthLoginAnnotation.class);
		
		
		if(auth != null && auth.checkAuth()[0].getCode()!=Integer.MIN_VALUE) {
//			Admin admin = (Admin)request.getSession().getAttribute("currentAdmin");
			//部署时候改为上述代码
			Admin admin = (Admin)request.getServletContext().getAttribute("currentAdmin");
			if(admin == null) {
				ResponseUtil.write(response, new ResultBean(false, "1000","会话过期，请重新登录"));
				return false;
			}
			//判断权限
			for(int i=0; i<auth.checkAuth().length; i++) {
				if(admin!=null && admin.getParentId()==auth.checkAuth()[i].getCode()) {
					System.out.println(auth.checkAuth()[i]);
					return true;
				}else if(admin!=null && admin.getParentId()>=auth.checkAuth()[i].getCode()) {
					System.out.println("test");
					return true;
				}
			}
			ResponseUtil.write(response, new ResultBean(false,"404","页面不存在"));
			return false;
		}
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
