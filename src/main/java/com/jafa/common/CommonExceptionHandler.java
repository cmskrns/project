package com.jafa.common;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CommonExceptionHandler {

	//400에러
	@ExceptionHandler(value =  {TypeMismatchException.class,BindException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String badRequestHanler() {
		return "errorPage/_400";
	}
	
	//404
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String notFoundPage(NoHandlerFoundException exception, Model model) {
		model.addAttribute("url", exception.getRequestURL());
		return "errorPage/_404";
	}
}
