package com.example.carsdb.rest;

/**
 * Some car was not found... 
 * 
 * @author Jan Jancura
 */
class CarNotFoundException extends RuntimeException {

	CarNotFoundException(Long id) {
		super("Could not find the car " + id);
	}
}
