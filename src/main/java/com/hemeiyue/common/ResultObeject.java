package com.hemeiyue.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultObeject extends ResultBean{
	private Object object;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public ResultObeject(boolean result,Object object) {
		super(result);
		this.object = object;
	}
}
