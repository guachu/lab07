/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jfmor
 */
@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository{

    public static List<Car> carsContainer;

    public static List<Car> getContainer() {
        if(CarMemoryRepository.carsContainer == null)
            CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }
    
    
    
    @Override
    public Car getCarByCarLicence(String carLicence) {
        return CarMemoryRepository.getContainer()
        .stream()
        .filter(c -> carLicence.equals(c.getLicencePlate()))
        .findFirst()
        .get();
    }

    @Override
    public List<Car> findAll() {
        return CarMemoryRepository.getContainer();
    }

    @Override
    public Car find(UUID id) {
        Optional<Car> answer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst();
        return answer.isPresent() ? answer.get() : null;
    }

    @Override
    public UUID save(Car entity) {
        CarMemoryRepository.getContainer().add(entity);
        return entity.getId();
    }

    @Override
    public void update(Car entity) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .map(c -> c.getId().equals(entity.getId()) ? entity : c)
                .collect(toList());
    }

    @Override
    public void delete(Car o) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getId().equals(o.getId()))
                .collect(toList());
    }

    @Override
    public void remove(Long id) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getId().equals(id))
                .collect(toList());
    }
    
}
