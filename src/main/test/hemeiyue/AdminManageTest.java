package hemeiyue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.common.ResultBean;
import com.hemeiyue.entity.Admin;
import com.hemeiyue.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class AdminManageTest {

	@Autowired
	private AdminService adminService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void adminDeleteTest() {
		ResultBean result = adminService.deleteById(1);
		System.out.println(result.isResult()+":"+result.getMessage());
	}
	
	@Test
	public void adminModifyTest() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setParentId(0);
		admin.setAccount("admin");
		admin.setAdminName("cedo");
		admin.setPhone("1325508462");
		ResultBean result = adminService.updateAdmin(admin);
		System.out.println(result.isResult());
		System.out.println(result.getMessage());
	}
	
	@Test
	public void adminListTest() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setParentId(0);
		adminService.findByAdmin(admin);
	}
	

}