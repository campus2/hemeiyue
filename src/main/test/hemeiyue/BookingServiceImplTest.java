package hemeiyue;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hemeiyue.entity.Bookings;
import com.hemeiyue.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class BookingServiceImplTest {
	
	@Autowired
	private BookingService bookService;
	
	@Test
	public void testUpdateBook() {
		Bookings book = new Bookings();
		book.setId(1);
		book.setRemark("testtest");
		bookService.updateBook(book);
	}

	@Test
	public void testDeleteBook() {
		bookService.deleteBook(1);
	}

	@Test
	public void testApplyBook() {
		bookService.applyBook(1);
	}

	@Test
	public void testRefuseBook() {
		bookService.refuseBook(1);
	}

	@Test
	public void testRevokeBook() {
		bookService.revokeBook(1);
	}

}
