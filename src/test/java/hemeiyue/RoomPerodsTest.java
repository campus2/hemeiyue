package hemeiyue;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.RoomTypes;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.service.RoomPeriodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RoomPerodsTest {
	
	@Autowired
	private RoomPeriodsService roomPeriodsService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void getPeriodTest() {
		Rooms room = new Rooms();
		room.setId(68);
		room.setRoom("2");
		room.setRoomType(new RoomTypes(5));
		String result = roomPeriodsService.getPeriod(room, "2018-04-13");
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		ResultBean result = roomPeriodsService.delete(5);
		System.out.println(result.isResult()+result.getMessage());
	}

	@Test
	public void test() throws ParseException {
//		Periods p = new Periods();
//		p.setAdmin(new Admin(14));
//		p.setPeriod("时间段二");
//		String startDate = "08:00";
//		String endDate = "09:00";
//        p.setBegintime(DateUtil.time2stamp(startDate));
//        p.setEndtime(DateUtil.time2stamp(endDate));
//        
//        periodsService.insertPeriods(p);
//        
//        System.out.println(p.getBegintime());
//        System.out.println(p.getEndtime());
//        
	}

}
