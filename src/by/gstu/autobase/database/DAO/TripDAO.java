package by.gstu.autobase.database.DAO;

import by.gstu.autobase.model.Trip;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public interface TripDAO {

    Connection getConnection() throws SQLException;

    List<Trip> findAll();

    Trip findTripById(int id);

    boolean delete(int id);

    boolean delete(Trip trip);

    boolean create(Trip trip);

    Trip update(Trip trip);

    List<Trip> findTripsByLogin(String login);

    Trip findTripByName(String orderName);
}
