package com.hemeiyue.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.service.SchoolService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolServiceImplTest {
	
	@Autowired
	private SchoolService schoolService;
	
	@Test
	public void testFindSchool() {
		schoolService.findSchool("广东技术师范学院");
	}


}
