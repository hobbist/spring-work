package com.spring.tut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public boolean putNewOffer(Offers offer){
		dao.saveOrUpdate(offer);
		return true;
	}

	public boolean hasOffer(String name) {
		if(name==null){
			return false;
		}
		List<Offers> offers=dao.getOffers(name);
		if(offers.size()==0){
			return false;
		}
		return true;
	}

	public Offers getOffer(String username) {
		if(username==null){
			return null;
		}
		List<Offers> offers=dao.getOffers(username);
		if(offers==null||offers.isEmpty()){
			return null;
		}
		return offers.get(0);
	}

	public void saveOrUpdate(Offers offers) {
		/*if(offers.getId()!=0){
			dao.update(offers);
		}else{
			dao.create(offers);
		}*/
		dao.saveOrUpdate(offers);
	}

	public void delete(int id) {
		dao.delete(id);
		
	}
}
