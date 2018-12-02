package com.example.carsdb.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdvice for CarNotFoundException.
 * 
 * @author Jan Jancura
 */
@ControllerAdvice
class CarNotFoundControllerAdvice {

	@ResponseBody
	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(CarNotFoundException ex) {
		return ex.getMessage();
	}
}