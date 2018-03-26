package com.hemeiyue.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AdminModel {

	@Size(min=5,max=11,message="账号必须5~11位",groups= {com.hemeiyue.entity.validation.AdminLogin.class,com.hemeiyue.entity.validation.AdminRegister.class})
    private String account;
    
    @Size(min=5,max=11,message="密码必须5~11位",groups= {com.hemeiyue.entity.validation.AdminLogin.class,com.hemeiyue.entity.validation.AdminRegister.class})
    private String password;
    
    @NotEmpty(message="名字不可为空",groups= {com.hemeiyue.entity.validation.AdminRegister.class})
    private String adminName;

    @Null
    private String signature;

    @Null
    private String avatar;

    @Null
    private String phone;
    	
    @Email(message="邮箱格式不正确",groups= {com.hemeiyue.entity.validation.AdminRegister.class})
    private String email;
    @Null
    private Integer parentId;
    @Null
    private Integer status;
    @Null
    private String salt;
    @NotNull
    private String school;
    @Null
    private Integer regStatus;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
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
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Integer getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(Integer regStatus) {
		this.regStatus = regStatus;
	}
    
    
}
