
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ApplyData;
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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testInsertRoomApply() {
		ApplyData application = new ApplyData();
		Users user = new Users();
		Schools school = new Schools();
		application.setBookingDate(new Timestamp(118, 4, 22, 12, 0, 0, 0));
		application.setPeriodId(1);
		application.setRoomName("一教会议室");
		application.setRoomType("会议室");
		user.setId(1);
		school.setId(25);
		System.out.println(JSONUtil.transform(bookingService.insertRoomApply(application, user, school)));
	}

}
