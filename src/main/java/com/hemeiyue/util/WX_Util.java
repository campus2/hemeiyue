package com.hemeiyue.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hemeiyue.common.Id;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.TemplantList;
import com.hemeiyue.common.Template;
import com.hemeiyue.common.WX_page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WX_Util {
	
//	private String access_Token = getToken().get("access_Token");
	
	/**
	 * 获取模板列表
	 * @param access_Token
	 * @param offset
	 * @param count
	 * @return
	 */
	public static ResultBean getTemplateList(String access_Token,Integer offset,Integer count ) {
		
		//发送请求获取模板信息json数据
		String url = "https://api.weixin.qq.com/cgi-bin/wxopen/template/list?access_token=ACCESS_TOKEN";
		String realUrl = url.replace("ACCESS_TOKEN", access_Token);
		WX_page page = new WX_page("0","20");
		String jsonPage = JSONObject.fromObject(page).toString();
		JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "POST", jsonPage);
		
		//模板信息转为list
		if(null != jsonObject && 0 == jsonObject.getInt("errcode")) {
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			@SuppressWarnings("unchecked")
			List<TemplantList> list =(List<TemplantList>) JSONArray.toCollection(jsonArray,TemplantList.class);
			ResultList result = new ResultList();
			result.setResult(true);
			result.setMessage("获取列表成功");
			result.setList(list);
			return result;
		}else {
			return new ResultBean(false, "获取模板列表失败");
		}
	}
	
	/**
	 * 获取特定模板
	 * @param access_Token
	 * @param templateId
	 * @return
	 */
	public static String getTemplate(String access_Token,String templateId) {
		String url = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/get?access_token=ACCESS_TOKEN";
		String realUrl = url.replace("ACCESS_TOKEN", access_Token);
		Id id = new Id();
		id.setId(templateId);
		JSONObject json = JSONObject.fromObject(id);
		System.out.println(json);
		JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "POST", json.toString());
		return jsonObject.toString();
	}
	
	/**
	 * 发送模板消息
	 * @param template
	 * @return
	 */
	public static ResultBean sendTemplate(String access_Token,Template template) {
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
		String realUrl = url.replace("ACCESS_TOKEN", access_Token);
		String json = JSONObject.fromObject(template).toString();
		JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "POST", json.toString());
		
		//根据返回值返回发送结果
		if(null != jsonObject) {
			if(0 == jsonObject.getInt("errcode")) {
				return new ResultBean(true, "发送模板成功");
			}
			if(40037 == jsonObject.getInt("errcode")) {
				return new ResultBean(false, "template_id不正确");
			}
			if(41028 == jsonObject.getInt("errcode")) {
				return new ResultBean(false, "form_id不正确，或者过期");
			}
			if(41029 == jsonObject.getInt("errcode")) {
				return new ResultBean(false, "form_id已被使用");
			}
			if(41030 == jsonObject.getInt("errcode")) {
				return new ResultBean(false, "page不正确");
			}
			if(45009 == jsonObject.getInt("errcode")) {
				return new ResultBean(false, "接口调用超过限额（目前默认每个帐号日调用限额为100万");
			}
		}
		return new ResultBean(false, "发送模板失败");
		
	}
	
	/**
	 * 获取openId
	 * @param code  前端返回code
	 * @return
	 */
	public static Map<String, String> getOpenId(String code) {

		String APPID = null;        	//小程序AppId
		String APPSecrect = null; 		//小程序APPSecrect
		Map<String, String> map = new HashMap<>();
		
		Properties pro = new Properties();
		InputStream in;
		
		try {
			in = WX_Util.class.getClassLoader().getResourceAsStream("wechat.properties");
			pro.load(in);
			in.close();
			APPID = pro.getProperty("APPID");
			APPSecrect = pro.getProperty("APPSecrect");
			
			String url = "https://api.weixin.qq.com/sns/jscode2session?"
					+ "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
			String realUrl = url.replace("APPID", APPID). 
				       replace("SECRET", APPSecrect).replace("JSCODE", code);
			JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "GET", null);
			
			map.put("openid", jsonObject.getString("openid"));
			map.put("session_key", jsonObject.getString("session_key"));

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 后台获取token
	 * @return
	 */
	public static Map<String,String> getToken(){
		String APPID = null;        	//小程序AppId
		String APPSecrect = null; 		//小程序APPSecrect
		Map<String, String> map = new HashMap<>();
		
		Properties pro = new Properties();
		InputStream in;
		
		
		try {
			in = WX_Util.class.getClassLoader().getResourceAsStream("wechat.properties");
			pro.load(in);
			in.close();
			APPID = pro.getProperty("APPID");
			APPSecrect = pro.getProperty("APPSecrect");
			System.out.println(pro.getProperty("APPID"));
			System.out.println(pro.getProperty("APPSecrect"));
			
			String url = "https://api.weixin.qq.com/cgi-bin/token?"
					+ "grant_type=client_credential&"
					+ "appid=APPID&"
					+ "secret=APPSECRET";
			String realUrl = url.replace("APPID", APPID). 
				       replace("APPSECRET", APPSecrect);
			System.out.println(realUrl);
			JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "GET", null);
			
			//access_token和expires_in存进properties文件
			if(jsonObject.get("errcode") == null) {
				map.put("APPID", APPID);
				map.put("APPSecrect", APPSecrect);
				map.put("access_token",jsonObject.getString("access_token") );
				map.put("expires_in", jsonObject.getString("expires_in"));
			}else {
				map.put("errcode", jsonObject.getString("errcode"));
				map.put("errmsg", jsonObject.getString("errmsg"));
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 更新wechat.properties中的Token
	 * @return
	 */
	public static boolean updateToekn() {
		Map<String,String> map = getToken();
		OutputStream os = null;
		if(map.get("errcode") == "40013") {
			return false;
		}else {
			System.out.println("test");
			try {
				String path = WX_Util.class.getResource("/wechat.properties").getPath();
				os = new FileOutputStream(path);
				Properties pro = new Properties();
				pro.setProperty("access_token",map.get("access_token"));
				pro.setProperty("APPID", map.get("APPID"));
				pro.setProperty("APPSecrect", map.get("APPSecrect"));
				System.out.println(pro.get("access_token"));
				pro.store(os, "update '" +"access_token"+"' value" );
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		updateToekn();
//		System.out.println(getToken().get("access_token"));
	}
}	
