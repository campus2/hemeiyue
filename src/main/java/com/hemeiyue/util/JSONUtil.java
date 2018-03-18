package com.hemeiyue.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * @author cedo
 *
 */
public class JSONUtil {
	
	public static String transform(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		//设置null值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		//设置时间解析
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		objectMapper.setDateFormat(fmt);
		try {
			String result = objectMapper.writeValueAsString(obj);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("转为JSON数据失败");
			return null;
		}
	}
}