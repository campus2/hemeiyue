
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.Application;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.BookingService;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class BookingServiceImplTest {
	
	@Autowired
	private BookingService bookingService;
	
	@Test
	public void testCancelReserve() {
		System.out.println(bookingService.updateCancelReserve(1));
	}
	
	@Test
	public void testInsertRoomApply() {
		Application application = new Application();
		Users user = new Users();
		Schools school = new Schools();
		application.setBookingDate(new Timestamp(2018, 4, 22, 12, 0, 0, 0));
		application.setRoomPeriodId(1);
		user.setId(1);
		school.setId(21);
		System.out.println(JSONUtil.transform(bookingService.insertRoomApply(application, user, school)));
	}

}
