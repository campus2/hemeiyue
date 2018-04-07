
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.service.NotificationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class NotificationServiceImplTest {
	
	@Autowired
	private NotificationService notificationService;
	
	@Test
	public void testFindAll() {
		System.out.println(notificationService.findAll());
	}

}
