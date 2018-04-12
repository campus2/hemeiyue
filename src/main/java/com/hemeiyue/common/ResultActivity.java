package com.hemeiyue.common;

public class ResultActivity extends ResultBean{
	
	private ActivityShowModel activity;

	public ActivityShowModel getActivity() {
		return activity;
	}

	public void setActivity(ActivityShowModel activity) {
		this.activity = activity;
	}
	
	public ResultActivity(boolean result,ActivityShowModel activity) {
		super(result);
		this.activity = activity;
	}
}
