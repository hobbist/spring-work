package com.spring.tut.dao;



import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tut.dao.rowmapper.OfferRowmapper;
import com.spring.tut.model.Offers;
import com.spring.tut.model.User;
@Repository
@Component("offersDao")
@Transactional
public class OfferDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Offers> getOffers() {
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Offers> query=builder.createQuery(Offers.class);
		Root<Offers> root=query.from(Offers.class);
		Join<Offers,User> offers=root.join("user");
		query.select(root).where(builder.equal(offers.get("enabled"), true));
		List<Offers> result=session().createQuery(query).getResultList();
		return result;
		//return jdbc.query("select * from offers,users where offers.username=users.username and users.enabled=true", new OfferRowmapper());
	}
	public List<Offers> getOffers(String username) {
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Offers> query=builder.createQuery(Offers.class);
		Root<Offers> root=query.from(Offers.class);
		Join<Offers,User> offers=root.join("user");
		query.select(root).where(builder.and(builder.equal(offers.get("enabled"), true)),builder.equal(offers.get("username"), username));
		List<Offers> result=session().createQuery(query).getResultList();
		return result;
		//return jdbc.query("select * from offers,users where offers.username=users.username and users.enabled=true and offers.username=:username",new MapSqlParameterSource("username",username), new OfferRowmapper());
	}
	
	public boolean delete(int id){
		Query query=session().createQuery("delete from Offers where id=:id");
		query.setParameter("id",id);
		//query.executeUpdate();
		return query.executeUpdate()==1;
		/*MapSqlParameterSource source=new MapSqlParameterSource("id",id);
		return jdbc.update("delete from offers where id=:id", source);*/
	}
	
	public void saveOrUpdate(Offers offer){
		session().saveOrUpdate(offer);
	}
	public Offers getOffer(int id) {
		Offers result=null;
		try{
		CriteriaBuilder builder=session().getCriteriaBuilder();
		CriteriaQuery<Offers> query=builder.createQuery(Offers.class);
		Root<Offers> root=query.from(Offers.class);
		Join<Offers,User> offers=root.join("user");
		query.select(root).where(builder.and(builder.equal(offers.get("enabled"), true)),builder.equal(root.get("id"), id));
		 result=session().createQuery(query).getSingleResult();
		}catch(NoResultException exp){
			System.out.println("no result forund for id"+id);
		}
		return result;
/*		MapSqlParameterSource source=new MapSqlParameterSource("id", id);
		return jdbc.queryForObject("select * from offers,users where id=:id and users.username=offers.username and users.enabled=true",source,new OfferRowmapper());
*/	}
}
