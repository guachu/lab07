package edu.eci.models;

import java.io.Serializable;
import java.util.UUID;

public class Car implements Serializable{
    private String name;
    private UUID id;
    private String licencePlate;
    private String brand;
    
    public Car(String name, UUID id, String licencePlate, String brand) {
        this.name = name;
        this.id = id;
        this.licencePlate = licencePlate;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    

    

}
