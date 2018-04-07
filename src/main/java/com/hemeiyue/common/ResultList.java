package com.hemeiyue.common;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultList extends ResultBean{
	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	public ResultList() {
		
	}
	
	public ResultList(boolean result,List<?> list) {
		super(result);
		this.list = list;
	}
	
}
