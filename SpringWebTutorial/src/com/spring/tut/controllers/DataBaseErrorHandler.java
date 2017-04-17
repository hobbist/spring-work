package com.spring.tut.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataBaseErrorHandler {
	@ExceptionHandler(DataAccessException.class)
	public String handleDataBaseException(DataAccessException exp){
		exp.printStackTrace();
		return "error";
	}
}
