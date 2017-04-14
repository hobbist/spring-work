package com.spring.tut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.tut.dao.OfferDAO;
import com.spring.tut.model.Offers;

@Service("offersService")
public class OffersService {

	private OfferDAO dao;
	@Autowired
	public void setDao(OfferDAO dao) {
		this.dao = dao;
	}
	
	public List<Offers> getCurrent(){
		return dao.getOffers();
	}
	
	public boolean putNewOffer(Offers offer){
		return dao.create(offer);
	}

	public void throwTestException(){
		dao.getOffer(99999);
		
	}
	
}
