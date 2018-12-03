package com.example.carsdb.rest;

import com.example.carsdb.db.CarsRepository;
import com.example.carsdb.db.entity.Car;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller implementation.
 * 
 * @author janjancura
 */
@RestController
public class CarsController {

	private final CarsRepository carsRepository;
  
	public CarsController(CarsRepository repository) {
		this.carsRepository = repository;
	}

  /**
   * Returns a list of car records.
   * 
   * @return A list of car records.
   */
	@GetMapping("/cars")
	public List<Car> getCars() {
		return carsRepository.findAll();
	}

  /**
   * Sets list of car records.
   * 
   * @param cars A list of car records.
   */
	@PutMapping("/cars")
	public void setCars(@RequestBody List<Car> cars) {
		carsRepository.deleteAll();
		carsRepository.saveAll(cars);
	}

  /**
   * Adds one card record.
   * 
   * @param car A car record to be added.
   * @return A car record.
   */
	@PostMapping("/cars")
	public Car addCar(@RequestBody Car car) {
		return carsRepository.save(car);
	}

  /**
   * Removes one car record.
   * 
   * @param id the id of car record to be deleted
   */
	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable Long id) {
    try {
      carsRepository.deleteById(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new CarNotFoundException(id);
    }
	}

  /**
   * Returns one car record.
   * 
   * @param id The id of car record to be returned.
   * 
   * @return The card record.
   */
	@GetMapping("/cars/{id}")
	public Car getCar(@PathVariable Long id) {
		return carsRepository.findById(id)
			.orElseThrow(() -> new CarNotFoundException(id));
	}

  /**
   * Updates car record with given id.
   * 
   * @param newCar The new car record.
   * @param id Id of car that will be updated.
   * @return Updated card record.
   */
	@PutMapping("/cars/{id}")
	public Car updateCar(@RequestBody Car newCar, @PathVariable Long id) {
		return carsRepository.findById(id)
			.map(car -> {
        if(newCar.getManufacturer() != null) {
          car.setManufacturer(newCar.getManufacturer());
        }
        if(newCar.getRegistration() != null) {
  				car.setRegistration(newCar.getRegistration());
        }
        if(newCar.getType() != null) {
  				car.setType(newCar.getType());
        }
				return carsRepository.save(car);
			})
			.orElseThrow(() -> new CarNotFoundException(id));
	}

  /**
   * Removes all records.
   */
	@DeleteMapping("/cars")
	public void deleteAll() {
		carsRepository.deleteAll();
	}
}