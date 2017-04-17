package com.spring.tut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import com.spring.tut.dao.UserDao;
import com.spring.tut.model.User;
@Service(value="usersService")
public class UsersService {
	private UserDao dao;
	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	public boolean createNewUser(User user){
		return dao.create(user);
	}

	public boolean exists(String username) {
		
		return dao.exists(username);
	}
	@Secured(value="ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return dao.getAllUsers();
	}
	

}
