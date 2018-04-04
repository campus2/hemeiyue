package com.hemeiyue.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;



public class WX_OpenIdUtil {
	
	public static String getOpendId(String code) {
		String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=grant_type";
		String APPID = "";
		String APPSecrect = "";
		String grant_type = "authorization_code";
		String requestUrl = WX_URL.replace("APPID", APPID). 
			       replace("SECRET", APPSecrect).replace("JSCODE", code).  
			       replace("grant_type", grant_type); 
		
		String result = get(requestUrl);
		JSONObject  json = JSONObject.fromObject(result);
		String openId =String.valueOf(json.get("openid"));
		return openId;
	}
	
	//向URL发送GET请求
	public static String get(String url) {
		String result = "";  
	    BufferedReader in = null;  
	    InputStream is = null;  
	    InputStreamReader isr = null;  
	    try {  
	        URL realUrl = new URL(url);  
	        URLConnection conn = realUrl.openConnection();  
	        conn.connect();  
//	        Map<String, List<String>> map = conn.getHeaderFields();  
	        is = conn.getInputStream();  
	        isr = new InputStreamReader(is);  
	        in = new BufferedReader(isr);  
	        String line;  
	        while ((line = in.readLine()) != null) {  
	            result += line;  
	        }  
	    } catch (Exception e) {  
	        // 异常记录  
	    	e.printStackTrace();
	    } finally {  
	        try {  
	            if (in != null) {  
	                in.close();  
	            }  
	            if (is != null) {  
	                is.close();  
	            }  
	            if (isr != null) {  
	                isr.close();  
	            }  
	        } catch (Exception e2) {  
	            // 异常记录  
	        }  
	    }  
	    return result;  
	}
}
