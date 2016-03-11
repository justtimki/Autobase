package by.gstu.autobase.database.DAO;

import by.gstu.autobase.model.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public interface CarDAO {

    Connection getConnection() throws SQLException;

    List<Car> findAll();

    Car findCarById(int id);

    Car findCarByName(String name);

    boolean delete(int id);

    boolean delete(Car car);

    boolean create(Car car);

    Car update(Car car);
}
