package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ItemNotFoundException.class)
	public @ResponseBody ErrorResponse handleItemNotFoundException(ItemNotFoundException ex) {
		return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
	}

}
