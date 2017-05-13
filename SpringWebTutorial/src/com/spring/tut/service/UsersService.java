package com.spring.tut.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

import com.spring.tut.dao.MessagesDao;
import com.spring.tut.dao.UserDao;
import com.spring.tut.model.Message;
import com.spring.tut.model.User;
@Service(value="usersService")
public class UsersService {
	private static Logger LOGGER=Logger.getLogger(UsersService.class);
	@Autowired
	private UserDao dao;
	@Autowired
	private MessagesDao msgDao;
	
	public boolean createNewUser(User user){
		dao.create(user);
		return true;
	}

	public boolean exists(String username) {
		
		return dao.exists(username);
	}
	@Secured(value="ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return dao.getAllUsers();
	}
	
	public void sendMessage(Message message){
		System.out.println(message);
		msgDao.saveOrUpdate(message);
	}
	
	public User getUser(String username){
		return dao.getUser(username);
	}

	public List<Message> getMessages(String username) {
		LOGGER.info("fetching messages for username:"+username);
		return msgDao.getMessages(username);
	}
	

}
