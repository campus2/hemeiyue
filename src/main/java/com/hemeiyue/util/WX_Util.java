package com.hemeiyue.util;

import java.util.List;

import com.hemeiyue.common.Id;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.common.TemplantList;
import com.hemeiyue.common.Template;
import com.hemeiyue.common.WX_page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WX_Util {
	
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
	
	public static String getOpenId(String APPID,String APPSecrect,String code) {
		String grant_type = "authorization_code";
		String url = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
		String realUrl = url.replace("APPID", APPID). 
			       replace("SECRET", APPSecrect).replace("JSCODE", code).  
			       replace("grant_type", grant_type);
		JSONObject jsonObject = HttpUtil.httpRequest(realUrl, "GET", null);
		return jsonObject.getString("openid");
	}
	
	public static void main(String[] args) {
		System.out.println(getTemplate("8_3R2g1OBlwfHgp4__-tJHhgjIkvPvKZa7nWBSyc56PpynA1GSxijtea38Qw_6saNzHrkeVCvR_GHQrBBnIkJUtPdmtunEzNhCoojiSNJ1ySNUTDGkwuEz5JDCSa_LEoaRr6vKSNoqarIOua7EWBVcAIANSM", "AT0002"));;
	}
}	
