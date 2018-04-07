package com.hemeiyue.service;

import javax.servlet.http.HttpServletRequest;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.WechatPicture;

public interface WechatPictureService {
	
	/**
	 * 上传图片时保存在数据库中
	 * @param picture
	 * @return
	 */
	public ResultBean insert(WechatPicture picture,HttpServletRequest request);
	
	/**
	 * 返回所有的图片
	 * @return
	 */
	public ResultBean findAll();
	
	/**
	 * 修改图片对应的HrefUrl
	 * @return
	 */
	public ResultBean updateHrefUrl(WechatPicture wechatPicture);
	
	
	/**
	 * 把图片的状态设置为删除（0）
	 * @param id
	 * @return
	 */
	public ResultBean updateStatus(Integer id);
	
	/**
	 * 把小程序首页所有的信息返回
	 * @return
	 */
	public ResultBean getIndex();
	
}
