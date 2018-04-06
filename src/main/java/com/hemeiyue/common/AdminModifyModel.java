package com.hemeiyue.common;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AdminModifyModel {

	private Integer id;
    
    private String password;
    
	@NotEmpty(message="名字不可为空",groups= {com.hemeiyue.entity.validation.AdminRegister.class})
    private String adminName;

	@NotEmpty
    private String phone;
    	
    @Email(message="邮箱格式不正确",groups= {com.hemeiyue.entity.validation.AdminRegister.class})
    private String email;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	  public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
    
}
