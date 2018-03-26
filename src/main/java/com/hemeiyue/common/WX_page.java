package com.hemeiyue.common;

public class WX_page {
	private String offset;
	
	private String count;
	
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public WX_page(String offset,String count) {
		this.offset = offset;
		this.count = count;
	}
}
