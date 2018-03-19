package hemeiyue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Rooms;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.ActivityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class ActivityTest {

	@Autowired
	private ActivityService activityService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void getAll() {
		String json = activityService.getUsersByActivity(new Activity(1));
		System.out.println(json);
	}
	
	@Test
	public void activityDeleteTest() {
		ResultBean result = activityService.deleteById(2);
		System.out.println(result.isResult()+":"+result.getMessage());
	}
	
	@Test  
	public void activityListTest() {
		String jsonList = activityService.findActivity(new Schools(1));
		System.out.println(jsonList);
	}
	
	@Test
	public void ActivityAddTest() {
		Activity ac = new Activity();
		Rooms room = new Rooms();
		room.setId(1);
		ac.setAddress(room);
		ac.setTitle("the first activity!");
		ac.setContent("This is the content");
		ac.setCount(30);
		ac.setDate(new Date());
		ac.setTime("8:00");
		ac.setStatus(1);
		
		Admin admin = new Admin();
		admin.setId(1);
		ac.setOwner(admin);
		ac.setSchool(new Schools(1));
		
		ResultBean result = activityService.insertActivity(ac);
		System.out.println(result.isResult()+":"+result.getMessage());
	}
}
