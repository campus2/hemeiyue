package com.hemeiyue.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.WechatPictureMapper;
import com.hemeiyue.entity.WechatPicture;
import com.hemeiyue.service.WechatPictureService;

@Service("wechatPictureService")
public class WechatPictureServiceImpl implements WechatPictureService{
	
	@Autowired
	private WechatPictureMapper wechatPictureMapper;

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
	          if(contentType.indexOf("png") == -1 && contentType.indexOf("jpg") == -1) {
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
	        wechatPicture.setUrl(localPath+filename);
	        wechatPicture.setStatus(1);
	        if(wechatPictureMapper.insert(wechatPicture) == 1) {
	        	return new ResultBean(true);
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
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}

	@Override
	public ResultBean updateStatus(Integer id) {
		if(wechatPictureMapper.updateStatus(id) == 1) {
			return new ResultBean(true);
		}
		return new ResultBean(false);
	}
	
}
