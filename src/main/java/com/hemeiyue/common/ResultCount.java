package com.hemeiyue.common;

import java.util.Map;

public class ResultCount extends ResultBean{

	private Map<String, Long> count;
	
	public ResultCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultCount(boolean result, String code, String message) {
		super(result, code, message);
		// TODO Auto-generated constructor stub
	}

	public Map<String, Long> getCount() {
		return count;
	}

	public void setCount(Map<String, Long> count) {
		this.count = count;
	}

	
}
