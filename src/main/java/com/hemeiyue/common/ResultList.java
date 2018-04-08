package com.hemeiyue.common;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultList extends ResultBean{
	private List<?> list;

	
	public ResultList(boolean result,List<?> list) {
		super(result);
		this.list = list;
	}

	public ResultList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultList(boolean result, String code, String message) {
		super(result, code, message);
		// TODO Auto-generated constructor stub
	}

	public ResultList(boolean result, String message) {
		super(result, message);
		// TODO Auto-generated constructor stub
	}

	public ResultList(boolean result) {
		super(result);
		// TODO Auto-generated constructor stub
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
}
