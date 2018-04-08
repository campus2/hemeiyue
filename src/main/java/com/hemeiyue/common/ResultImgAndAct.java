package com.hemeiyue.common;

import java.util.List;

import com.hemeiyue.entity.WechatPicture;

public class ResultImgAndAct extends ResultBean{
	
	private List<WechatPicture> weChatPicture;
	
	private List<ActivityModel> activityList;
	
	
	public List<WechatPicture> getWeChatPicture() {
		return weChatPicture;
	}

	public void setWeChatPicture(List<WechatPicture> weChatPicture) {
		this.weChatPicture = weChatPicture;
	}

	public List<ActivityModel> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityModel> activityList) {
		this.activityList = activityList;
	}

		
	public ResultImgAndAct(boolean result,List<WechatPicture> weChatPicture,List<ActivityModel> activityList) {
		super(result);
		this.activityList = activityList;
		this.weChatPicture = weChatPicture;
	}
}
