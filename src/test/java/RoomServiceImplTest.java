import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RoomServiceImplTest {
	
	@Autowired
	private RoomService roomService;

	@Test
	public void testGetRoom() {
		Schools school = new Schools();
		school.setId(21);
		System.out.println(roomService.getRoom("会议室", school));;
	}

}
