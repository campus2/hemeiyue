package com.hemeiyue.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class AdminServiceImplTest {
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testLogin() {
		
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testTenantApplyList() {
		fail("Not yet implemented");
	}

	@Test
	public void testTenantApply() {
		int id = 1;
		adminService.tenantApply(id);
	}

	@Test
	public void testValidationAccount() {
		String account = "super";
		adminService.validationAccount(account);
	}

	@Test
	public void testDeleteTenant() {
		fail("Not yet implemented");
	}

	@Test
	public void testTenantMangerList() {
		
	}

	@Test
	public void testSuspendedTenant() {
		int id = 1;
		adminService.suspendedTenant(id);
	}

	@Test
	public void testRestoreTenant() {
		int id = 1;
		adminService.restoreTenant(id);
	}
	
	@Test
	public void testFindPassword() {
		Admin admin = new Admin();
		admin.setId(16);
		admin.setPassword("987654321");
		adminService.findPassword(admin);
	}
}
