package com.hemeiyue.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * @author cedo
 *
 */
public class JSONUtil {

	/**
	 * Object转Json，不去掉null
	 * @param obj
	 * @return
	 */
	public static String transformWithNull(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String result = objectMapper.writeValueAsString(obj);
			//设置时间解析
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			objectMapper.setDateFormat(fmt);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("转为JSON数据失败");
			return null;
		}
	}
	
	/**
	 * Object转Json，同时去掉null，日期格式化
	 * @param obj
	 * @return
	 */
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

	/**
	 * 将json数据转为指定泛型的对象
	 * @param content
	 * @param ref
	 * @return
	 */
	public static <T> T transToModels(String content, TypeReference<T> ref) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			T array = objectMapper.readValue(content, ref);
			return array;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} 
	}
}
