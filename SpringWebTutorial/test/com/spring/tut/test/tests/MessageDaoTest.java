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

import com.spring.tut.dao.MessagesDao;
import com.spring.tut.dao.OfferDAO;
import com.spring.tut.dao.UserDao;
import com.spring.tut.model.Message;
import com.spring.tut.model.Offers;
import com.spring.tut.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/spring/tut/test/config/test-config.xml",
		"classpath:com/spring/tut/springconfig/security-context.xml",
		"classpath:com/spring/tut/springconfig/dao-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {
	@Autowired
	private OfferDAO offersDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MessagesDao messageDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("johnwpurcell", "hellothere", "john@caveofprogramming.com", true, "ROLE_USER",
			"John Purcell");
	private User user2 = new User("richardhannay", "the39steps", "richard@caveofprogramming.com", true, "ROLE_ADMIN",
			"Richard Hannay");
	private User user3 = new User("suetheviolinist", "iloveviolins", "sue@caveofprogramming.com", true, "ROLE_USER",
			"Sue Black");
	private User user4 = new User("rogerblake", "liberator", "rog@caveofprogramming.com", false, "ROLE_USER", "Rog Blake");
	
	private Message message1 = new Message("Test Subject 1", "Test content 1", "Isaac Newton", "isaac@caveofprogramming.com", user1.getUsername());
	private Message message2 = new Message("Test Subject 2", "Test content 2", "Isaac Newton", "isaac@caveofprogramming.com", user1.getUsername());
	private Message message3 = new Message("Test Subject 3", "Test content 3", "Isaac Newton", "isaac@caveofprogramming.com", user2.getUsername());

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSaveRetrieve() {
		userDao.create(user1);
		userDao.create(user2);
		
		messageDao.saveOrUpdate(message1);
		messageDao.saveOrUpdate(message2);
		messageDao.saveOrUpdate(message3);
		
		List<Message> messages = messageDao.getMessages(user1.getUsername());
		assertEquals(2, messages.size());
		
		messages = messageDao.getMessages(user2.getUsername());
		assertEquals(1, messages.size());
	}
	
	@Test
	public void testRetrieveById() {
		userDao.create(user1);
		userDao.create(user2);
		
		messageDao.saveOrUpdate(message1);
		messageDao.saveOrUpdate(message2);
		messageDao.saveOrUpdate(message3);
		
		List<Message> messages = messageDao.getMessages(user1.getUsername());
		
		for(Message message: messages) {
			Message retrieved = messageDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
	}
	
	@Test
	public void testDelete() {
		userDao.create(user1);
		userDao.create(user2);
		
		messageDao.saveOrUpdate(message1);
		messageDao.saveOrUpdate(message2);
		messageDao.saveOrUpdate(message3);
		
		List<Message> messages = messageDao.getMessages(user1.getUsername());
		
		for(Message message: messages) {
			Message retrieved = messageDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
		
		Message toDelete = messages.get(1);
		
		assertNotNull("This message not deleted yet.", messageDao.getMessage(toDelete.getId()));
		
		messageDao.delete(toDelete.getId());
		
		assertNull("This message was deleted.", messageDao.getMessage(toDelete.getId()));
	}
}
