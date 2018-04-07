package com.hemeiyue.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 基础包
 * @author cedo
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultBean {

	//请求结果
	private boolean result;
	
	//错误代码
	private String code;
	
	//错误信息
	private String message;
	
	public ResultBean(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	
	public ResultBean(boolean result,String code,String message) {
		super();
		this.result = result;
		this.message = message;
		this.code = code;
	}

	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultBean(boolean result) {
		super();
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
