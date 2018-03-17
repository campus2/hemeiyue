package com.hemeiyue.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/department")
public class DepartmentController {
//
//	@Autowired
//	private DepartmentService departmentService;
	
	/**
	 * 返回某个学校的部门信息
	 * @param schoolId
	 * @param response
	 * @return
	 */
	public String list(@RequestParam("schoolId")String schoolId, 
			HttpServletResponse response) {
		
		return null;
	}
	
}
