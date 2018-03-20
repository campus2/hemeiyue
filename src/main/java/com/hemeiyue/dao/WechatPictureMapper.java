package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.WechatPicture;

public interface WechatPictureMapper {
	
	public int insert(WechatPicture picture);
	
	public List<WechatPicture> findAll();
	
	public int updateHrefUrl(WechatPicture wechatPicture);
	
	public int updateStatus(Integer id);
}
