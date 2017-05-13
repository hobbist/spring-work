package com.spring.tut.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.tut.model.Offers;
import com.spring.tut.model.User;

public class OfferRowmapper implements RowMapper<Offers> {

	@Override
	public Offers mapRow(ResultSet resultSet, int arg1) throws SQLException {
		User user=new User();
		user.setAuthority(resultSet.getString("authority"));
		user.setEmail(resultSet.getString("email"));
		user.setEnabled(true);
		user.setName(resultSet.getString("name"));
		user.setUsername(resultSet.getString("username"));
		
		Offers offer=new Offers();
		offer.setId(resultSet.getInt("id"));
		offer.setText(resultSet.getString("text"));
		offer.setUser(user);

		return offer;
	}

}
