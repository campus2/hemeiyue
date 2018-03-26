package hemeiyue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hemeiyue.common.SchoolModel;
import com.hemeiyue.util.APIUtil;
import com.hemeiyue.util.JSONUtil;

public class HttpClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "广东技术师范学院");
		String content = APIUtil.API("schoolAPI", params);
		List<SchoolModel> list = JSONUtil.transToModels(content,new TypeReference<List<SchoolModel>>(){});
		for (SchoolModel schoolModel : list) {
			System.out.println(schoolModel.getName());
		}
	}

}
