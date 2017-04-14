package com.spring.tut.dao;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.tut.model.Offers;
@Component("offersDao")
public class OfferDAO {
	//private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate jdbc;	

	@Autowired
	public void setDataSource(DataSource jdbc) {
		//this.jdbc = new JdbcTemplate(jdbc);
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offers> getOffers() {
		return jdbc.query("select * from offers", new BeanPropertyRowMapper<Offers>(Offers.class));
		/*return jdbc.query("select * from offers", new RowMapper<Offers>(){
			@Override
			public Offers mapRow(ResultSet resultSet, int rowCount) throws SQLException {
				Offers offer=new Offers();
				offer.setEmail(resultSet.getString("email"));
				offer.setId(resultSet.getInt("id"));
				offer.setText(resultSet.getString("text"));
				offer.setName(resultSet.getString("name"));
				return offer;
			}
		});*/
	}
	
	public boolean create(Offers offer){
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(offer);
		return jdbc.update("insert into offers (name,text,email) values (:name,:text,:email) ", source)==1;
	}
	public int delete(int id){
		
		// jdbc template jdbc.getJdbcOperations();
		MapSqlParameterSource source=new MapSqlParameterSource("id",id);
		return jdbc.update("delete from offers where id=:id", source);
	}
	
	public boolean update(Offers offer){
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set name=:name,text=:text,email=:email where id=:id", source)==1;
	}
	
	
	public int[] batchUpdate(List<Offers> offers){
		return jdbc.batchUpdate("insert into offers (name,text,email) values (:name,:text,:email) ", SqlParameterSourceUtils.createBatch(offers.toArray()));
	}
	
	@Transactional
	public int[] create(List<Offers> offers){
		return jdbc.batchUpdate("insert into offers (id,name,text,email) values (:id,:name,:text,:email) ", SqlParameterSourceUtils.createBatch(offers.toArray()));
	}
	public Offers getOffer(int id) {
		MapSqlParameterSource source=new MapSqlParameterSource("id", id);
		return jdbc.queryForObject("select * from offers where id=:id",source, new BeanPropertyRowMapper<Offers>(Offers.class));
		/*return jdbc.query("select * from offers", new RowMapper<Offers>(){
			@Override
			public Offers mapRow(ResultSet resultSet, int rowCount) throws SQLException {
				Offers offer=new Offers();
				offer.setEmail(resultSet.getString("email"));
				offer.setId(resultSet.getInt("id"));
				offer.setText(resultSet.getString("text"));
				offer.setName(resultSet.getString("name"));
				return offer;
			}
		});*/
	}
}
