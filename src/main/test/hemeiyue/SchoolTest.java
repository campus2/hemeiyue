package hemeiyue;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.service.RoomTypeService;
import com.hemeiyue.service.SchoolService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolTest {

	static {
		int x = 5;
	}
	static int x, y;
	
	@Resource
	private SchoolService schoolService;
	
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void schoolTest() throws ParseException {
//		Schools s = new Schools("测试", 0);
//		int id = schoolService.insert(s);
//		System.out.println(s.getId());
		System.out.println(DateUtil.date());
		System.out.println(DateUtil.time());
		System.out.println(DateUtil.dateTime().toString());
	}
	
//	@Test
//	public void schoolTest() {
//		ResultBean result = schoolService.findSchool("广东技术师范学院");
//		System.out.println(JSONUtil.transform(result));
//		
//		Map<String, Object> map = new  HashMap<>();
//		map.put("school", "广东技术师范学院");
//		String list = schoolService.find(map);
//		System.out.println(list);
//	}
	
	/*@Test
	public void roomDelete() {
		RoomTypes roomType = new RoomTypes(1);
		Schools school = new Schools(1);
		roomType.setSchool(school);
		
		roomService.deleteByTypeAndName(roomType, "????");
	}
	
	@Test
	public void roomModifyTest() {
		UpdateRoom updateRoom = new UpdateRoom();
		updateRoom.setOldRoom("????");
		updateRoom.setNewRoom("102");
		
		roomService.updateRoom(updateRoom);
	}
	
	@Test
	public void roomListTest() {
		System.out.println(roomService.selectBySchoolId(1));
	}
	
	@Test
	public void roomAddTest() {
		RoomModel roomModel = new RoomModel();
		roomModel.setRoomName("202");
		roomModel.setRoomType("????????");
		
		Rooms room = new Rooms();
		room.setRoom(roomModel.getRoomName());
		room.setRoomType(new RoomTypes(3));
		room.setSchool(new Schools(1));
		room.setDepartment(new Departments(1));
		room.setStatus(1);
		
		Periods p = new Periods();
		p.setBegintime(new Date());
		p.setEndtime(new Date());
		p.setPeriod("afternoon");
		Admin admin = new Admin();
		admin.setId(1);
		p.setAdmin(admin);
		
		PeriodTime pt = new PeriodTime();
		pt.setPeriod(p);
		pt.setRoom(room);
		pt.setStatus(1);
		pt.setRoom(room);
		
		List<PeriodTime> ptList = new ArrayList<>();
		ptList.add(pt);
		ptList.add(pt);
		roomModel.setPeriod(ptList);
		
		
		ResultBean result = roomService.insertRoomModel(roomModel, room);
		System.out.println(result.isResult());
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
	}*/

}
