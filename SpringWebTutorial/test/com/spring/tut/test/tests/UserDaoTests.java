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

import com.spring.tut.dao.UserDao;
import com.spring.tut.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/spring/tut/test/config/test-config.xml",
		"classpath:com/spring/tut/springconfig/security-context.xml",
		"classpath:com/spring/tut/springconfig/dao-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	@Autowired
	private UserDao dao;
	@Autowired
	private DataSource dataSource;
	private User user1 = new User("johnwpurcell","hellothere",
			"john@caveofprogramming.com", true, "ROLE_USER","John Purcell");
	private User user2 = new User("richardhannay",  "the39steps",
			"richard@caveofprogramming.com", true, "ROLE_ADMIN","Richard Hannay");
	private User user3 = new User("suetheviolinist", "iloveviolins",
			"sue@caveofprogramming.com", true, "ROLE_USER", "Sue Black");
	private User user4 = new User("rogerblake", "liberator",
			"rog@caveofprogramming.com", false, "user", "Rog Blake");
	@Before
	public void init() {
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");	
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");		
	}

	@Test
	public void testCreateRetrieve(){
		dao.create(user1);
		List<User> users=dao.getAllUsers();
		assertEquals("One User should be created", 1,users.size());
		assertEquals("Inserted matches retrieved",user1,users.get(0));
		dao.create(user2);
		dao.create(user3);
		dao.create(user4);
		
		assertEquals("4 Users should be created", 4,dao.getAllUsers().size());
		
	}
	@Test
	public void testExists(){
		dao.create(user2);
		dao.create(user3);
		dao.create(user4);
		assertTrue("User must exists",dao.exists(user2.getUsername()));
		assertFalse("User should not exists",dao.exists("kappp"));
	}	
}
