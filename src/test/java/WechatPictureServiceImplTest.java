
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.service.WechatPictureService;
import com.hemeiyue.util.JSONUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class WechatPictureServiceImplTest {
	
	@Autowired
	private WechatPictureService wechatPictureService;
	
	@Test
	public void testGetIndex() {
		System.out.println(JSONUtil.transform(wechatPictureService.getIndex()));
	}

}
