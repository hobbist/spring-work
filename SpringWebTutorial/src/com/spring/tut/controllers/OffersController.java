package com.spring.tut.controllers;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
/*
 * Setting up MVC project Spring:-
 * 1.Maven to include dependencies
 * 2.DispatcherServlet in web.xml(some name=XXX)
 * 3.create context xml with name XXX-servlet.xml
 * 4.java class annotation and component scan for same
 * 5.Jsp resource resolver to set prefix and suffix
 * 6.Jndi-java naming and directory interface
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.tut.model.Offers;
import com.spring.tut.service.OffersService;
import com.spring.tut.validators.FormValidationGroup;
/**
 *  controller for offers activities such as fetch offers and create offers
 * @author kapil
 *
 */
@Controller
public class OffersController {
	@Autowired
	private OffersService service;
	
	@RequestMapping("/offers")
	public String showOffers(Model model){
		List<Offers> list=service.getCurrent();
		model.addAttribute("offers",list);
		return "offers";
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String showTest(Model model,@RequestParam("id") String id){
		System.out.println("id is "+id);
		return "home";
	}
	
	@RequestMapping("/createOffers")
	public String createOffers(Model model,Principal principal){
		Offers offer=null;
		if(principal!=null){
			String username=principal.getName();
			 offer=service.getOffer(username);
		}
		if(offer==null){
			offer=new Offers();
		}
		model.addAttribute("offers", offer);
		return "createOffer";
	}
	
	@RequestMapping(value="/doCreate" ,method=RequestMethod.POST)
	/*java 303	 */
	public String doCreate(Model model,@Validated(value=FormValidationGroup.class) Offers offers,BindingResult result,Principal principal,@RequestParam(value="delete",required=false)String delete){
		String view="";
		if(result.hasErrors()){
			view="createOffer";
		}else{
			if(delete==null){
				String username=principal.getName();
				offers.getUser().setUsername(username);
				System.out.println("validated");
				service.saveOrUpdate(offers);
				view="offerCreated";
			}else{
				service.delete(offers.getId());
				view="offerDeleted";
			}
		}
		System.out.println(offers);
		return view;
	}
	
}
