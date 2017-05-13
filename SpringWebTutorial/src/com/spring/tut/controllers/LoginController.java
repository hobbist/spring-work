package com.spring.tut.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.tut.model.Message;
import com.spring.tut.model.User;
import com.spring.tut.service.UsersService;
import com.spring.tut.validators.FormValidationGroup;

@Controller
public class LoginController {
	private UsersService service;
	@Autowired
	public void setService(UsersService service) {
		this.service = service;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/newAccount")
	public String showNewAccount(Model model){
		model.addAttribute("user",new User());
		return "newAccount";
	}
	
	@RequestMapping("/createAccount")
	public String createAccount(){
		return "accountCreated";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users=service.getAllUsers();
		model.addAttribute("users",users);
		return "admin";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping(value="/createAccount" ,method=RequestMethod.POST)
	/*java 303	 */
	public String createAccount(@Validated(FormValidationGroup.class) User user,BindingResult result){
		String view="";
		if(result.hasErrors()){
			view="newAccount";
		}else{
			user.setEnabled(true);
			user.setAuthority("ROLE_USER");
			if(service.exists(user.getUsername())){
				result.rejectValue("username", "DuplicateKey.user.usename");
				return "newAccount";
			}
			System.out.println("validated");
			service.createNewUser(user);
			view="accountCreated";
		}
		System.out.println(user);
		return view;
	}
	@RequestMapping(value="/getMessages",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Map<String,Object> getMessages(Principal princicpal){
		List<Message> messages=null;
		if(princicpal==null){
			messages=new ArrayList<Message>();
		}
		else{
			String username=princicpal.getName();
			messages=service.getMessages(username);
		}
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("messages", messages);
		data.put("number",messages.size());
		return data;
	}
}
