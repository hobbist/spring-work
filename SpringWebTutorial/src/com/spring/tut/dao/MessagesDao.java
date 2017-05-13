package com.spring.tut.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tut.model.Message;
import com.spring.tut.model.User;

@Repository
@Component("messageDao")
@Transactional
public class MessagesDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Message> getMessages() {
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Message> query=builder.createQuery(Message.class);
		Root<Message> root=query.from(Message.class);
		query.select(root);
		List<Message> result=session().createQuery(query).getResultList();
		return result;
	}
	public List<Message> getMessages(String username) {
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Message> query=builder.createQuery(Message.class);
		Root<Message> root=query.from(Message.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		List<Message> result=session().createQuery(query).getResultList();
		return result;
	}
	
	public boolean delete(int id){
		Query query=session().createQuery("delete from Message where id=:id");
		query.setParameter("id",id);
		return query.executeUpdate()==1;
	}
	
	public void saveOrUpdate(Message message){
		session().saveOrUpdate(message);
	}
	public Message getMessage(int id) {
		Message result=null;
		try{
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Message> query=builder.createQuery(Message.class);
		Root<Message> root=query.from(Message.class);
		query.select(root).where(builder.equal(root.get("id"), id));
		result=session().createQuery(query).getSingleResult();
		}catch(NoResultException exp){
			System.out.println("no result forund for id"+id);
		}
		return result;
	}
}
