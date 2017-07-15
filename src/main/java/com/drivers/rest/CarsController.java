package com.drivers.rest;

import com.drivers.entities.Car;
import com.drivers.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dima on 15.07.2017.
 */
@RestController
@RequestMapping("/cars")
public class CarsController {
    private CarRepository carRepository;

    @Autowired
    public CarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findOne(id);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping
    public void createNewCar(@RequestBody Car car) {
        carRepository.save(car);
    }

    @PutMapping
    public ResponseEntity updateCar(@RequestBody Car car) {
        Car carFromDb = carRepository.findOne(car.getId());
        if (carFromDb == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        carRepository.save(car);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.delete(id);
    }
}
