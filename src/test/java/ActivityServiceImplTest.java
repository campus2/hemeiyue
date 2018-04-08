
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class ActivityServiceImplTest {
	
	@Autowired 
	private ActivityService activityServiceImpl;

	@Test
	public void testSelectById() {
		Users user = new Users();
		user.setId(2);
		System.out.println(JSONUtil.transform(activityServiceImpl.findById(1,user)));
	}
	
	@Test
	public void findActivity() {
		Schools school = new Schools();
		school.setId(21);
		System.out.println(activityServiceImpl.findActivity(school));
	}
	
	@Test
	public void findArtivityList() {
//		Schools school = new Schools();
//		school.setId(21);
//		System.out.println(activityServiceImpl.findArtivityList(school));
		Users user = new Users();
		user.setId(2);
		System.out.println(activityServiceImpl.updateWeChatScan(user,1));;
	}
}
