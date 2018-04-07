package com.hemeiyue.common;

public class ResultAddPeriod extends ResultBean {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ResultAddPeriod(boolean result, String message, Integer id) {
		super(result, message);
		this.id = id;
	}

	public ResultAddPeriod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultAddPeriod(boolean result, String code, String message) {
		super(result, code, message);
		// TODO Auto-generated constructor stub
	}

	public ResultAddPeriod(boolean result, String message) {
		super(result, message);
		// TODO Auto-generated constructor stub
	}

	public ResultAddPeriod(boolean result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
}
