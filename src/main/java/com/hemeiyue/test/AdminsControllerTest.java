package com.hemeiyue.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class AdminsControllerTest {

	@Autowired
	private AdminService admin;
	
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testAdd() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testModify() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminList() {
		fail("Not yet implemented");
	}
//
	@Test
	public void testDelete() {
//		admin.find(new Admin());
	}

}
