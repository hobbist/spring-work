package com.spring.tut.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tut.model.User;

@Repository
@Transactional
@Component(value="userDao")
public class UserDao {
	//private JdbcTemplate jdbc;
	//private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*@Autowired
	public void setDataSource(DataSource jdbc) {
		//this.jdbc = new JdbcTemplate(jdbc);
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}*/
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	@Transactional
	public void create(User user){
		/*MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("name", user.getName());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());*/
		//return jdbc.update("insert into users (username,password,email,enabled,name,authority) values (:username,:password,:email,:enabled,:name,:authority) ", params)==1;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}
	public boolean exists(String username) {
		User user=getUser(username);
		return user!=null;
		//return jdbc.queryForObject("select count(*) from users where username= :username", new MapSqlParameterSource("username",username),Integer.class)>0;
	}
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		//return jdbc.query("select * from users", new BeanPropertyRowMapper<User>(User.class));
		return session().createQuery("from User").list();
	}
	public User getUser(String username) {
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<User> query=builder.createQuery(User.class);
		Root<User> root=query.from(User.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		User user=session().createQuery(query).uniqueResult();
		return user;
		
	}
	
	
}