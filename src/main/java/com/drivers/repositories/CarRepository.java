package com.drivers.repositories;

import com.drivers.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dima on 15.07.2017.
 */
public interface CarRepository extends JpaRepository<Car,Long>{
}
