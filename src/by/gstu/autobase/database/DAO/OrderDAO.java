package by.gstu.autobase.database.DAO;

import by.gstu.autobase.model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 15.01.2016.
 */
public interface OrderDAO {

    Connection getConnection() throws SQLException;

    List<Order> findAll();

    Order findOrderByName(String name);

    boolean delete(String name);

    boolean delete(Order order);

    boolean create(Order order);

    Order update(Order order);

    Order findOrderById(int id);
}
