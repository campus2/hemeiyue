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
import com.hemeiyue.entity.Schools;
import com.hemeiyue.service.AdminService;
import com.hemeiyue.util.JSONUtil;

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
	public void tentantTest() {
		Admin admin = new Admin();
		admin.setId(3);
		admin.setParentId(-1);
		ResultBean result = adminService.tenantMangerList(admin);
		System.out.println(JSONUtil.transform(result));
	}
	
	@Test
	public void loginTest() {
		Admin admin = new Admin();
		admin.setAccount("admin");
		admin.setPassword("admin");
		
		ResultBean a = adminService.login(admin);
		System.out.println(JSONUtil.transform(a));
//		adminService.findPassword(admin);
	}
	
	/*@Test
	public void schoolTotalTest() {
		System.out.println(adminService.getAllRooms(new Schools(1)));
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
	}*/
	

}
