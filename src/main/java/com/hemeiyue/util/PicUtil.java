package com.hemeiyue.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.hemeiyue.common.ResultBean;

/**
 * 图片上传工具
 * @author cedo
 *
 */
public class PicUtil {

	public ResultBean uploadPic(MultipartFile file, String location) {
		String localPath=this.getClass().getClassLoader().getResource("").getPath().replaceAll("/WEB-INF/classes/", location==null?"/assets/activityImage/":location);  
		System.out.println(localPath);
		//若没有这个目录则新建一个
		File dir = new File(localPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//定义 文件名  
	    String filename=null;
	    
	    //判断上传的文件是否为空
		if(!file.isEmpty()) {
			//生成uuid作为文件名称    
	          String uuid = UUID.randomUUID().toString().replaceAll("-","");
	          
	          //获得文件类型，  判断如果文件类型不为png或jpg，禁止上传    
	          String contentType=file.getContentType();
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
	        	  file.transferTo(new File(localPath+filename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				System.out.println("exception");
				return new ResultBean(false,"保存文件失败");
			}
	        String url = (location==null?"/assets/activityImage/":location+filename);
	        return new ResultBean(true, url);
		}
		return new ResultBean(false,"文件为空");
	}
}
