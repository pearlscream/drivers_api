package com.drivers.rest;

import com.drivers.entities.Car;
import com.drivers.entities.Driver;
import com.drivers.repositories.CarRepository;
import com.drivers.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dima on 15.07.2017.
 */
@RestController
@RequestMapping("/drivers")
public class DriversController {
    private DriverRepository driverRepository;
    private CarRepository carRepository;

    @Autowired
    public DriversController(DriverRepository driverRepository, CarRepository carRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return driverRepository.findOne(id);
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @PostMapping
    public void createNewCar(@RequestBody Driver driver) {
        for (Car car : driver.getCars()) {
            carRepository.save(car);
        }
        driverRepository.save(driver);
    }

    @PutMapping
    public ResponseEntity updateCar(@RequestBody Driver driver) {
        Driver driverFromDb = driverRepository.findOne(driver.getId());
        if (driverFromDb == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        driverRepository.delete(id);
    }
}
