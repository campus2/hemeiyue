import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.service.RoomPeriodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class RoomPeriodsServiceImplTest {
	
	@Autowired
	private RoomPeriodsService roomPeriodsService;
	
	@Test
	public void testGetPeriod() {
		System.out.println(roomPeriodsService.getPeriod(new Date()));
	}

}
