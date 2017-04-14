package com.spring.tut.model;

import javax.validation.constraints.NotNull;
/*
 * Javax validation constraints documentation
 */
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.spring.tut.validators.ValidateEmailAddress;

@Component("offer")
public class Offers {
private int id;
@Size(min=5,max=12,message="Name Field exceeds max size permitted")
private String name;
@NotNull
//@Pattern(regexp=".*\\@.*\\..*",message="Email validation failed")
@ValidateEmailAddress(message="email entered is not valid")
private String email;
@Size(min=1,max=100,message="Text Field exceeds max size permitted")
private String text;
public Offers() {	
}
public Offers(int id, String name, String email, String text) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.text = text;
}
public Offers(String name, String email, String text) {
	super();
	this.name = name;
	this.email = email;
	this.text = text;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
@Override
public String toString() {
	return "Offers [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
}
}
