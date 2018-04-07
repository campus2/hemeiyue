
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Users;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.JSONUtil;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolServiceImplTest {
	
	@Autowired
	private SchoolService schoolService;
	
	@Test
	public void testSelectSchool() {
		System.out.println(JSONUtil.transform((schoolService.selectSchool("广东"))));
		
	}

	@Test
	public void testHandleSchool() {
		Users user = new Users();
		user.setOpenId("123");
		System.out.println(JSONObject.fromObject(schoolService.insertHandleSchool("广东工业大学", user)));
	}

}
