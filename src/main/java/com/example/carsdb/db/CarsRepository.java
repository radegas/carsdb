package com.example.carsdb.db;

import com.example.carsdb.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository of car records.
 * 
 * @author Jan Jancura
 */
public interface CarsRepository extends JpaRepository<Car, Long> {

}