package hemeiyue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Departments;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.service.SchoolService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolTest {

	@Resource
	private SchoolService schoolService;
	
	@Autowired
	private RoomService roomService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void roomListTest() {
		System.out.println(roomService.selectBySchoolId(1));
	}

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<>();
		map.put("start", 1);
		map.put("size", 4);
		System.out.println(schoolService.find(map));
		
	}
	
	@Test
	public void roomTest() {
		Rooms room = new Rooms();
		room.setRoom("添加课室");
		Departments d = new Departments();
		d.setId(1);
		Schools s = new Schools();
		s.setId(1);
		room.setDepartment(d);
		room.setSchool(s);
		RoomTypes roomType = new RoomTypes();
		roomType.setId(1);
		room.setRoomType(roomType);
		room.setStatus(1);
		roomService.insert(room);
	}

}
