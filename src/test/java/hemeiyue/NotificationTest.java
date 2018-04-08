package hemeiyue;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.common.ResultList;
import com.hemeiyue.entity.Activity;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.entity.Notification;
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.NotificationService;
import com.hemeiyue.util.DateUtil;
import com.hemeiyue.util.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class NotificationTest {
	
	@Autowired
	private NotificationService notificationService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void listTest() {
		ResultList rl = (ResultList) notificationService.findAll(21);
		System.out.println(JSONUtil.transform(rl));
	}
	
//	@Test
//	public void addTest() {
//		Notification notification = new Notification();
//		notification.setTitle("test");
//		notification.setContent("测试内容");
//		ResultBean list = notificationService.insert(notification, new Schools(21));
//		System.out.println(list.getMessage());
//	}
	
	

}
