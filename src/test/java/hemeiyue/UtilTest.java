package hemeiyue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hemeiyue.util.DateUtil;

public class UtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String week = DateUtil.dateToWeek("2018-04-07");
		System.out.println(week);
	}

}
