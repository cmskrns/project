package com.jafa.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.jafa.common.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{
	
	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation){
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valid = true;
		
		try {
			Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			valid = firstObj != null && secondObj != null && firstObj.equals(secondObj);
			if (!valid) {
				context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName)
				.addConstraintViolation().disableDefaultConstraintViolation();
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valid;
	}
}
