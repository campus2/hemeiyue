package com.hemeiyue.entity;

import java.util.Date;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hemeiyue.util.DateYYMMddJSONDeserializer;
import com.hemeiyue.util.DateYYMMddJSONSerializer;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Admin {
	
    @Null
	private Integer id;
    
    @Size(min=5,max=11,message="账号必须5~11位",groups= {com.hemeiyue.entity.validation.AdminLogin.class,com.hemeiyue.entity.validation.AdminRegister.class})
    private String account;
    
    @Size(min=5,max=11,message="密码必须5~11位",groups= {com.hemeiyue.entity.validation.AdminLogin.class,com.hemeiyue.entity.validation.AdminRegister.class})
    private String password;
    
    @NotEmpty(message="名字不可为空",groups= {com.hemeiyue.entity.validation.AdminRegister.class,com.hemeiyue.entity.validation.AdminModify.class})
    private String adminName;

    @Null
    private String signature;

    @Null
    private String avatar;

    @Null
    private String phone;
    	
    @Email(message="邮箱格式不正确",groups= {com.hemeiyue.entity.validation.AdminRegister.class,com.hemeiyue.entity.validation.AdminModify.class})
    private String email;
    
    @Null
    private Integer parentId;
    
    /**
     * 0：不可用，1：可用
     */
    @Null
    private Integer status;
    @Null
    private String salt;
    @Null
    private Schools school;
    @Null
    private Integer regStatus;
    
    @Null
    @JsonDeserialize(using=DateYYMMddJSONDeserializer.class)
	@JsonSerialize(using=DateYYMMddJSONSerializer.class)
    private Date date;
    
    public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(Integer regStatus) {
		this.regStatus = regStatus;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
    
    public Schools getSchool() {
        return school;
    }

    public void setSchool(Schools school) {
        this.school = school;
    }
}