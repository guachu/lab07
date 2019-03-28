package edu.eci.models;

import java.io.Serializable;
import java.util.UUID;

public class Car implements Serializable{

    private UUID id;
    private String licencePlate;
    private String brand;
    
    public Car(){
        
    }
    
    public Car( UUID id, String licencePlate, String brand) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.brand = brand;
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
