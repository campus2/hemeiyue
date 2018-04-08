package com.hemeiyue.common;

import java.util.Map;

public class ResultMap extends ResultBean{
	
	private Map<?,?> data;
	
		
	public ResultMap(boolean result, Map<?, ?> data) {
		super(result);
		this.data = data;
	}

	public ResultMap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultMap(boolean result, String message) {
		super(result, message);
	}

	public ResultMap(boolean result) {
		super(result);
	}

	public Map<?, ?> getMap() {
		return data;
	}

	public void setMap(Map<?, ?> data) {
		this.data = data;
	}

	
	
}
