package com.hemeiyue.common;

/**
 * 停用/恢复租户结果model
 * @author cedo
 *
 */
public class ResultTenant extends ResultBean{

	private int status;
	
	private Tenant tenant;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
}
