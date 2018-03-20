package hemeiyue;

import java.util.ArrayList;
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

import com.hemeiyue.common.PeriodTime;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.RoomModel;
import com.hemeiyue.common.UpdateRoom;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Departments;
import com.hemeiyue.entity.Periods;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.service.RoomTypeService;
import com.hemeiyue.service.SchoolService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SchoolTest {

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
	}

}
