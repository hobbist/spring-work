package com.spring.tut.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.tut.dao.OfferDAO;
import com.spring.tut.dao.UserDao;
import com.spring.tut.model.Offers;
import com.spring.tut.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/spring/tut/test/config/test-config.xml",
		"classpath:com/spring/tut/springconfig/security-context.xml",
		"classpath:com/spring/tut/springconfig/dao-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTests {
	@Autowired
	private OfferDAO offersDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("johnwpurcell", "hellothere", "john@caveofprogramming.com", true, "ROLE_USER",
			"John Purcell");
	private User user2 = new User("richardhannay", "the39steps", "richard@caveofprogramming.com", true, "ROLE_ADMIN",
			"Richard Hannay");
	private User user3 = new User("suetheviolinist", "iloveviolins", "sue@caveofprogramming.com", true, "ROLE_USER",
			"Sue Black");
	private User user4 = new User("rogerblake", "liberator", "rog@caveofprogramming.com", false, "user", "Rog Blake");
	private Offers offer1 = new Offers("This is a test offer.", user1);
	private Offers offer2 = new Offers("This is another test offer.", user1);
	private Offers offer3 = new Offers("This is yet another test offer.", user2);
	private Offers offer4 = new Offers("This is a test offer once again.", user3);
	private Offers offer5 = new Offers("Here is an interesting offer of some kind.", user3);
	private Offers offer6 = new Offers("This is just a test offer.", user3);
	private Offers offer7 = new Offers("This is a test offer for a user that is not enabled.", user4);

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		List<Offers> offers1 = offersDao.getOffers();
		assertEquals("offers must be 1", 1, offers1.size());
		assertEquals("Retrieved offer must be same as inserted one", offer1, offers1.get(0));
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		List<Offers> offers = offersDao.getOffers();
		assertEquals("offers must be 6", 6, offers.size());

	}

	@Test
	public void testGetByUserName() {
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		List<Offers> offers = offersDao.getOffers(user3.getUsername());
		assertEquals("three offers exists for user", 3, offers.size());
		List<Offers> offers2 = offersDao.getOffers("falseTest");
		assertEquals("Zero offers exists for user", 0, offers2.size());
		List<Offers> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("One offers exists for user", 1, offers3.size());
		List<Offers> offers4 = offersDao.getOffers(user4.getUsername());
		assertEquals("Null offers exists for user", 0, offers4.size());
	}

	@Test
	public void testUpdate() {
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		offer3.setText("this offer has new text than earlier");
		offersDao.saveOrUpdate(offer3);

		Offers retrieved = offersDao.getOffer(offer3.getId());
		assertEquals("should be updated", offer3, retrieved);
		System.out.println(offer3.getText());
	}

	@Test
	public void testDelete() {
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		Offers retrieved1 = offersDao.getOffer(offer1.getId());
		assertNotNull("should not be null", retrieved1);
		offersDao.delete(offer1.getId());
		Offers retrieved2 = offersDao.getOffer(offer1.getId());
		assertNull("should be null", retrieved2);

	}

	@Test
	public void testGetByID() {
		userDao.create(user1);
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer7);
		Offers retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("must be equal", offer1, retrieved1);
		assertNull("must be null", offersDao.getOffer(offer7.getId()));

	}

}
