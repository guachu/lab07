/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author jfmor
 */
public class CarPostgresRepository implements ICarRepository{
    private String dbUrl = "jdbc:postgresql://ec2-54-225-95-183.compute-1.amazonaws.com:5432/ddm2mdl9lqea9l";

    @Autowired
    private DataSource dataSourceCar;

    

    @Bean
    public DataSource dataSourceCar() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setDriverClassName("org.postgresql.Driver");
            config.setUsername("zqcbaifthkvbtq");
            config.setPassword("46f2dd43260eb9ac4aa9b5f828d1c92c3352c149a1851ae43badd11456adbf36");
            config.setMaximumPoolSize(1000);
            return new HikariDataSource(config);
        }
    }

    @Override
    public Car getCarByCarLicence(String carLicence) {
        String query = "SELECT * FROM \"cars\" WHERE \"cars\".licencePlate=" + carLicence;
        try (Connection connection = dataSourceCar.getConnection()) {
            Statement stmt = connection.createStatement(); // instruccion con la conexion
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta en la base de datos pro la conexion
            Car car = null;
            while (rs.next()) {
                    car = new Car();
                    car.setBrand(rs.getString("Brand"));
                    car.setId(UUID.fromString(rs.getString("id")));
                    car.setLicencePlate(rs.getString("licencePlate"));
            }
            return car;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        String query = "SELECT * FROM cars";
        List<Car> cars = new ArrayList<>();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car = new Car();
                car.setId(UUID.fromString(rs.getString("id")));
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate(rs.getString("licence"));
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car find(UUID id) {
        String query = "SELECT * FROM cars WHERE id = " + id;
        
        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);            
            Car car = new Car();
            car.setId(UUID.fromString(rs.getString("id")));
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate("licence");
            return car;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID save(Car entity) {
        String query = "INSERT INTO cars (id, licence, brand) VALUES("+entity.getId().toString()+","+entity.getLicencePlate()+","+entity.getBrand()+")";

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return UUID.fromString(rs.getString("id"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car entity) {
        String query = "UPDATE cars SET licence = "+entity.getLicencePlate()+", brand = "+entity.getBrand()+" WHERE id = " + entity.getId().toString();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car o) {
        String query = "DELETE FROM users WHERE id = "+ o.getId().toString();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = "DELETE FROM users WHERE id = "+ UUID.fromString(id.toString());

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
