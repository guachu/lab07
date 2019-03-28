/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services.contracts;

import edu.eci.models.Car;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jfmor
 */
public interface ICarServices {
    public List<Car> list();
    public Car createCar(Car car);
    public Car updateCar(Car car);
    public Car deleteCar(UUID id);
    public Car get(String name);
    public Car get(UUID id);
}
