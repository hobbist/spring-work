package com.spring.tut.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.tut.model.Offers;
import com.spring.tut.service.OffersService;

@Controller
public class HomeController {
	private static Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private OffersService service;

	@RequestMapping("/")
	public String showHome(Model model,Principal principal) {
		logger.info("Showing Home page...");
		List<Offers> list = service.getCurrent();
		model.addAttribute("offers", list);
		boolean hasOffers=false;
		if(principal!=null){
			hasOffers=service.hasOffer(principal.getName());
		}
		model.addAttribute("hasOffer", hasOffers);
		return "home";
	}
}
