package com.spring.tut.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tut.model.User;
@Component(value="userDao")
public class UserDao {
	//private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate jdbc;	

	@Autowired
	public void setDataSource(DataSource jdbc) {
		//this.jdbc = new JdbcTemplate(jdbc);
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	@Transactional
	public boolean create(User user){
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password,email,enabled) values (:username,:password,:email,:enabled) ", source);
		return jdbc.update("insert into authorities (username,authority) values (:username,:authority) ", source)==1;
	}
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username= :username", new MapSqlParameterSource("username",username),Integer.class)>0;
	}
	public List<User> getAllUsers() {
		return jdbc.query("select * from users,authorities where users.username=authorities.username", new BeanPropertyRowMapper<User>(User.class));
	}
	
	
}