package hemeiyue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultList;
import com.hemeiyue.dao.BookingsMapper;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.BookingService;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class BookingTest {
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private BookingsMapper bookingsMapper;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void bookingTest() {
//		ResultList list = (ResultList) bookingService.findAllBooks(new Schools(21));
//		System.out.println(JSONUtil.transform(list));
	}

	@Test
	public void roomDeleteTest() {
		List<Integer> ids = new ArrayList<>();
		ids.add(238);
		ids.add(239);
		bookingsMapper.updateByDelete(ids);
	}
	

}
