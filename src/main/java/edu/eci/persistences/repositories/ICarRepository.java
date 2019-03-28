/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences.repositories;


import edu.eci.models.Car;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jfmor
 */
@Repository
public interface ICarRepository extends DAO<Car, UUID>{
    Car getCarByCarLicence(String carLicence);
}
