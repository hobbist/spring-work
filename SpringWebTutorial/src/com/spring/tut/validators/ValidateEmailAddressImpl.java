package com.spring.tut.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidateEmailAddressImpl implements ConstraintValidator<ValidateEmailAddress, String> {
private int min=0;
	@Override
	public void initialize(ValidateEmailAddress constraintAnnotation) {
		min=constraintAnnotation.min();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.length()<min){
			return false;
		}
		if(!EmailValidator.getInstance(false).isValid(email)){
			return false;
		}
		return true;
	}

}
