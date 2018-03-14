package com.hemeiyue.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 返回JSON工具类
 * @author cedo
 *
 */
public class ResponseUtil {

	/**
	 * 将object转为JSON格式并写回前端
	 * @param response
	 * @param o
	 */
	public static void write(HttpServletResponse response,Object o) {
		response.setContentType("text/html;charset=utf-8");
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter out;
		try {
			String json = objectMapper.writeValueAsString(o);
			out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
