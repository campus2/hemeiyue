
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class UsersServiceImplTest {
	
	@Autowired
	private UsersService usersService;

	@Test
	public void testReserve() {
		System.out.println(usersService.reserve(1));
	}
	
	@Test
	public void testReserveHistory() {
		System.out.println(usersService.reserveHistory(2));
	}
	
	@Test
	public void PersonalInfo() {
		System.out.println(usersService.selectPersonalInfo(1));
	}
	
//	@Test
//	public void updatePersonalInfo() {
//		UsersModel user = new UsersModel();
//		user.setId(1);
//		user.setClassroom("class1");
//		user.setEmail("cedo");
//		System.out.println(JSONUtil.transform(usersService.updatePersonalInfo(user)));
//	}
	
	@Test
	public void getApplyInfo() {
		Users user = new Users();
		Schools school = new Schools();
		user.setId(1);
		school.setId(25);
		user.setSchool(school);
		System.out.println(usersService.getApplyInfo(user));
	}
}
