package hemeiyue;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ActivityUserModel;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.entity.Users;
import com.hemeiyue.service.ActivityService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.ExcelUtil;

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
	
	@SuppressWarnings("unchecked")
	@Test
	public void listTest() {
		List<ActivityUserModel> list = (List<ActivityUserModel>) activityService.getUsersByActivity(new Activity(8)).getList();
		ExcelUtil.createActivityExcel("//media//cedo//644A01D2114857BF//outExcel.xls", list);
		System.out.println(list);
	}
	
	@Test
	public void insertTest() {
		Activity a = new Activity();
		a.setTitle("test");
		a.setContent("ggefa");
		a.setNumber(10);
		a.setDate(new Timestamp(new Date().getTime()));
		a.setTime(DateUtil.time2stamp("08:00"));
		a.setSchool(new Schools(21));
		a.setOwner(new Admin(14));
		a.setImageUrl("test");
		a.setAddress("af");
		activityService.insertActivity(a);
	}
	
	@Test
	public void signUpActivity() {
		Users user = new Users();
		user.setId(1);
		Schools school = new Schools();
		school.setId(25);
		System.out.println(activityService.insertSignUpActivity(19, school, user));
	}
}
