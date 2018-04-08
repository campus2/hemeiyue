package hemeiyue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Users;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolServiceImplTest {
	
	@Autowired
	private SchoolService schoolService;
	
	@Test
	public void testSelectSchool() {
		System.out.println(JSONUtil.transform(schoolService.selectSchool("广东")));;
	}

	@Test
	public void testInsertHandleSchool() {
		String school = "广东工业大学";
		Users user = new Users();
		user.setId(1);
		user.setOpenId("123");
		System.out.println(JSONUtil.transform(schoolService.insertHandleSchool(school, user)));
	}

}
