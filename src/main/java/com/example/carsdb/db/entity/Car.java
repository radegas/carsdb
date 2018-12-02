package com.example.carsdb.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The car record representation.
 * 
 * @author Jan Jancura
 */
@Data
@Entity
public class Car {

	private @Id @GeneratedValue Long id;
	private String manufacturer;
	private String type;
	private String registration;

  public Car() {
  }
  
	public Car(String manufacturer, String type, String registration) {
		this.manufacturer = manufacturer;
		this.type = type;
    this.registration = registration;
	}
}
