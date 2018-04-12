package hemeiyue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.PeriodAddModel;
import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultMap;
import com.hemeiyue.dao.RoomtypeMapper;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.RoomPeriodsService;
import com.hemeiyue.service.RoomService;
import com.hemeiyue.service.RoomTypeService;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RoomTest {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private RoomPeriodsService roomPeriodsService;
	
	@Autowired
	private RoomtypeMapper roomTypeMapper;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void roomTypeFind() {
		Map<String, Object> map = new HashMap<>();
		map.put("roomType", "课室");
		map.put("school", new Schools(25));

		List<RoomTypes> rtList = roomTypeMapper.find(map);
		
	}
	
	@Test
	public void roomAddPeriod() {
		Admin admin = new Admin(14);
		PeriodAddModel model = new PeriodAddModel();
		model.setBeginTime("18:00");
		model.setEndTime("19:00");
		model.setWeeks("7");
		model.setRoomName("二教609");
		model.setRoomType("会议室");
		
		Rooms room = new Rooms();
		room.setSchool(admin.getSchool());
		room.setRoomType(roomTypeService.selectBySchoolAndRoomType(admin.getSchool(), model.getRoomType()));
		room.setRoom(model.getRoomName());
		room.setStatus(1);
		 
		ResultBean r = roomService.addRoomPeriod(model, room, admin);
		System.out.println(r.isResult()+r.getMessage());
	}

	@Test
	public void roomDetailTest() {
		RoomTypes roomTypes = roomTypeService.selectBySchoolAndRoomType(new Schools(21), "会议室");
		System.out.println(roomTypes.getId());
		Rooms room = new Rooms("二教609", roomTypes, new Schools(21));
		ResultMap r = roomPeriodsService.findRoomDetail(room);
		System.out.println(JSONUtil.transform(r));
	}
	
//	@Test
//	public void test() {
//		RoomModel rm = new RoomModel();
//		rm.setRoomName("二教测试61");
//		rm.setRoomType("会议室");
//		List<PeriodModel> list = new ArrayList<>();
//		PeriodModel p = new PeriodModel();
//		p.setBeginTime("08:00");
//		p.setEndTime("10:00");
//		
//		list.add(p);
//		list.add(p);
//		list.add(p);
//		rm.setPeriod(list);
//		Rooms room = new Rooms();
//		room.setRoom(rm.getRoomName());
//		RoomTypes roomType = roomTypeService.selectBySchoolAndRoomType(new Schools(21), rm.getRoomType());
//		room.setRoomType(roomType);
//		room.setSchool(new Schools(21));
//		room.setStatus(1);
//	
//		roomService.insertRoomModel(rm, room, new Admin(14));
//	}

}
