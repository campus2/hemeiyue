package com.hemeiyue.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ActivityShowModel;
import com.hemeiyue.common.PictureResult;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultImgAndAct;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.ActivityMapper;
import com.hemeiyue.dao.WechatPictureMapper;
import com.hemeiyue.entity.WechatPicture;
import com.hemeiyue.service.WechatPictureService;
import com.hemeiyue.util.StringUtil;

@Service("wechatPictureService")
public class WechatPictureServiceImpl implements WechatPictureService{
	
	private final static  String LOCALURL = "https://mcd.ngrok.xiaomiqiu.cn/hemeiyue";
	
	@Autowired
	private WechatPictureMapper wechatPictureMapper;
	
	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public ResultBean insert(WechatPicture wechatPicture,HttpServletRequest request) {
		String localPath=this.getClass().getClassLoader().getResource("").getPath().replaceAll("/WEB-INF/classes/", "/assets/wechatImage/");  
		System.out.println(localPath);
		//若没有这个目录则新建一个
		File dir = new File(localPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//定义 文件名  
	    String filename=null;
	    
	    //判断上传的文件是否为空
		if(!wechatPicture.getFile().isEmpty()) {
			//生成uuid作为文件名称    
	          String uuid = UUID.randomUUID().toString().replaceAll("-","");
	          
	          //获得文件类型，  判断如果文件类型不为png或jpg，禁止上传    
	          String contentType=wechatPicture.getFile().getContentType();
	          System.out.println(contentType);
	          if(contentType.indexOf("png") == -1 && contentType.indexOf("jpg") == -1
	        		  && contentType.indexOf("jpeg") == -1) {
	        	  return new ResultBean(false, "上传的图片格式必须为png或jpg");
	          }
	          //获得文件后缀名   
	          String suffixName=contentType.substring(contentType.indexOf("/")+1);  
	          //得到 文件名  
	          filename=uuid+"."+suffixName;   
	          //文件保存路径  
	          try {
	        	  System.out.println(localPath+filename);
				wechatPicture.getFile().transferTo(new File(localPath+filename));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("exception");
				return new ResultBean(false,"保存文件失败");
			}
	        wechatPicture.setUrl("/assets/wechatImage/"+filename);
	        wechatPicture.setStatus(1);
	        if(wechatPictureMapper.insert(wechatPicture) == 1) {
	        	return new PictureResult(true, "上传成功", 
	        			wechatPicture.getId(),wechatPicture.getUrl(),wechatPicture.getHrefUrl());
	        }
		}
		return new ResultBean(false,"文件为空");
	}

	@Override
	public ResultBean findAll() {
		ResultList result = new ResultList();
		result.setResult(true);
		result.setList(wechatPictureMapper.findAll());
		return result;
	}

	@Override
	public ResultBean updateHrefUrl(WechatPicture wechatPicture) {
		if(wechatPictureMapper.updateHrefUrl(wechatPicture) == 1) {
			return new ResultBean(true,"添加成功");
		}
		return new ResultBean(false,"添加失败");
	}

	@Override
	public ResultBean updateStatus(Integer id) {
		if(wechatPictureMapper.updateStatus(id) == 1) {
			return new ResultBean(true,"删除成功");
		}
		return new ResultBean(false,"删除失败");
	}

	@Override
	public ResultBean getIndex() {
		List<WechatPicture> weChatPicture = wechatPictureMapper.findAll();
		List<ActivityShowModel> activityList = activityMapper.findAll();
		if(weChatPicture == null || weChatPicture.size() == 0 
				|| activityList == null || activityList.size() == 0) {
			return new ResultBean(false);
		}
		if(weChatPicture.size() > 5) {
			weChatPicture = weChatPicture.subList(0, 4);
		}
		if(activityList.size() > 6) {
			activityList.subList(0, 5);
		}
		List<ActivityShowModel> resultList = new ArrayList<>();
		for (ActivityShowModel activityModel : activityList) {
			if(!StringUtil.StringIsNot(activityModel.getImageUrl()))
				activityModel.setImageUrl(LOCALURL+activityModel.getImageUrl());
			if(!activityModel.getDate().before(new Timestamp(System.currentTimeMillis())))		//判断是否过期
				resultList.add(activityModel);
		}
		for (WechatPicture wePicture : weChatPicture) {
			if(!StringUtil.StringIsNot(wePicture.getUrl()))
					wePicture.setUrl(LOCALURL+ wePicture.getUrl());
//			if(wePicture.getHrefUrl().isEmpty() || "".equals(wePicture.getHrefUrl())) {
//				wePicture.setHrefUrl(null);
//				System.out.println(wePicture.getUrl());
//			}
		}
		return new ResultImgAndAct(true, weChatPicture, resultList);
	}
	
}
