package com.hemeiyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hemeiyue.common.ResultBean;

/**
 * 404，500错误
 * @author cedo
 *
 */
@Controller
public class ErrorController {

	@RequestMapping("/error_404")
	@ResponseBody
	public ResultBean error404() {
		return new ResultBean(false, "404", "页面找不到");
	}
	
	@RequestMapping("/error_403")
	@ResponseBody
	public ResultBean error403() {
		return new ResultBean(false, "403", "资源不可用");
	}
	
	@RequestMapping("/error_500")
	@ResponseBody
	public ResultBean error500() {
		return new ResultBean(false, "500", "服务错误，已通知管理员");
	}
}
