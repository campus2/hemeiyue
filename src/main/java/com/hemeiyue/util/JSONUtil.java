package com.hemeiyue.util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemeiyue.common.SchoolModel;

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
