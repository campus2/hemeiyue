package com.hemeiyue.common;

import com.hemeiyue.entity.WechatPicture;

public class PictureResult extends ResultBean{
	
	private Integer id;
	
	private String url;
	
	private String hrefUrl;
	
	private WechatPicture wechatPicture;
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	public WechatPicture getWechatPicture() {
		return wechatPicture;
	}

	public void setWechatPicture(WechatPicture wechatPicture) {
		this.wechatPicture = wechatPicture;
	}
}
