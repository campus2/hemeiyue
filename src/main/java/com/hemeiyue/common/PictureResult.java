package com.hemeiyue.common;

public class PictureResult extends ResultBean{
	
	/**
	 * 图片ID
	 */
	private Integer id;
	
	private String url;
	
	private String hrefUrl;
	
	public PictureResult(boolean result, String message, Integer id, String url, String hrefUrl) {
		super(result, message);
		this.id = id;
		this.url = url;
		this.hrefUrl = hrefUrl;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PictureResult(boolean result, String message, String url) {
		super(result, message);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setHrefUrl(String url) {
		this.url = url;
	}
}
