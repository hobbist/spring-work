package com.spring.tut.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDataBaseException(DataAccessException exp){
		exp.printStackTrace();
		return "error";
	}
	@ExceptionHandler (AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException exp){
		exp.printStackTrace();
		return "denied";
	}
		
	
}
