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
public class RoomTypeTest {

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
	public void deleteRoomTypeTest() {
		RoomTypes roomType = new RoomTypes("????????", new Schools(1));
		roomTypeService.delete(roomType);
	}

}
