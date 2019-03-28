/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jfmor
 */
@Component
public class CarServices implements ICarServices{

    @Autowired
    @Qualifier("CarMemoryRepository")
    private ICarRepository carRepository;
    
    @Override
    public Car createCar(Car car) {
        if(null == car.getId())
            throw new RuntimeException("Id invalid");
        else if(carRepository.find(car.getId()) != null)
            throw new RuntimeException("The car exists");
        else
            carRepository.save(car);
        return car;
    }

    @Override
    public Car updateCar(Car car) {
        carRepository.update(car);
        return carRepository.getCarByCarLicence(car.getLicencePlate());
    }

    @Override
    public Car deleteCar(UUID id) {
        Car carroAEliminar = carRepository.find(id);
        carRepository.delete(carroAEliminar);
        return carroAEliminar;
    }

    @Override
    public List<Car> list() {
        return carRepository.findAll();
    }

    @Override
    public Car get(String licence) {
        return carRepository.getCarByCarLicence(licence);
    }

    @Override
    public Car get(UUID id) {
        return carRepository.find(id);
    }
    
}
