package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

    private String dbUrl = "jdbc:postgresql://ec2-54-225-95-183.compute-1.amazonaws.com:5432/ddm2mdl9lqea9l";

    @Autowired
    private DataSource dataSource;

    @Override
    public User getUserByUserName(String userName) {
        String query = "SELECT * FROM \"users\" WHERE \"users\".name=" + userName;
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement(); // instruccion con la conexion
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta en la base de datos pro la conexion
            User user = null;
            while (rs.next()) {
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setId(UUID.fromString(rs.getString("id")));
            }
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users=new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(UUID id) {
        String query = "SELECT * FROM \"users\" WHERE id='" + id.toString() + "'";
            try (Connection connection = dataSource.getConnection()) {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    User user = null;
                    while (rs.next()) {
                            user = new User();
                            user.setName(rs.getString("name"));
                            user.setId(UUID.fromString(rs.getString("id")));				
                    }
                    return user;
            } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
            }
    }

    @Override
    public UUID save(User entity) {
        String insert = "INSERT INTO \"users\" (name, id) VALUES ('" + entity.getName() +  "','" + entity.getId().toString() + "')";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(insert);
            return entity.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        String update = "UPDATE \"users\" SET name='" + entity.getName() + "' WHERE id='" + entity.getId().toString()+"'";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User o) {
        String delete = "DELETE FROM \"users\" WHERE id='" + o.getId().toString()+"'";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(delete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        String delete = "DELETE FROM \"users\" WHERE id='" + id+"'";
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(delete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
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
}
