package hemeiyue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemeiyue.eumn.Auth;

public class JSONTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void eumnTest() {
		System.out.println(Auth.operator.ordinal()>1);
	}

	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		mapper.setDateFormat(fmt);
		String str = mapper.writeValueAsString(new Date());
		System.out.println(str);
		Date date = mapper.readValue(str, Date.class);
		System.out.println(date);
	}

}
