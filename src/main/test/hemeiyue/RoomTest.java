package hemeiyue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Rooms;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.service.RoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RoomTest {

	@Autowired
	private RoomPeriodsService roomPeriodsService;
	
	@Autowired
	private RoomService roomService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void roomList() {
//		String result = roomService.selectBySchoolId(21);
//		System.out.println(result);
	}
	
	
	@Test
	public void RooDetailTest() {
//		Rooms room = new Rooms();
//		room.setId(14);
//		String result = roomPeriodsService.findRoomDetail(room);
//		System.out.println(result);
	}

}
