package by.gstu.autobase.database.DAO.mysqlDAOobjects;

import by.gstu.autobase.database.ConnectorDB;
import by.gstu.autobase.database.DAO.OrderDAO;
import by.gstu.autobase.enumeration.TripStatusEnum;
import by.gstu.autobase.model.Order;
import by.gstu.autobase.util.CloseUtility;
import by.gstu.autobase.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of OrderDAO for MySQL
 * Created by Alexandr Kolymago on 15.01.2016.
 */
public class MySQLOrderDAO implements OrderDAO {

    private static final Logger logger = LogManager.getLogger();
    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_ORDERS = "SELECT_ALL_ORDERS";
    private static final String SQL_SELECT_ORDER_BY_NAME = "SELECT_ORDER_BY_NAME";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT_ORDER_BY_ID";
    private static final String SQL_DELETE_ORDER_BY_NAME = "DELETE_ORDER_BY_NAME";
    private static final String SQL_INSERT_ORDER = "INSERT_ORDER";
    private static final String SQL_UPDATE_ORDER = "UPDATE_ORDER";

    /**
     * Columns name
     */
    private static final String ORDER_ID = "orderId";
    private static final String ORDER_NAME = "orderName";
    private static final String CAR_SPEED = "carSpeed";
    private static final String CAR_CAPACITY = "carCapacity";
    private static final String TRIP_STATUS = "status";

    /**
     * Gets connection from pool
     *
     * @return connection from pool
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectorDB.getInstance().getConnection();
        if(connection == null)
            throw new SQLException("Connection is null!");
        return connection;
    }

    @Override
    public List<Order> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        Order order = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ALL_ORDERS));
            rs = st.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(ORDER_ID));
                order.setCarCapacity(rs.getDouble(CAR_CAPACITY));
                order.setCarSpeed(rs.getDouble(CAR_SPEED));
                order.setOrderName(rs.getString(ORDER_NAME));
                order.setStatus(TripStatusEnum.valueOf(rs.getString(TRIP_STATUS)));
                orders.add(order);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Error while finding all orders.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return orders;
    }

    @Override
    public Order findOrderByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Order order = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ORDER_BY_NAME));
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(ORDER_ID));
                order.setCarCapacity(rs.getDouble(CAR_CAPACITY));
                order.setCarSpeed(rs.getDouble(CAR_SPEED));
                order.setOrderName(rs.getString(ORDER_NAME));
                order.setStatus(TripStatusEnum.valueOf(rs.getString(TRIP_STATUS)));
            }
            if (order == null) {
                logger.warn("Can't find record with name [" + name + "]!");
            }
            return order;
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Error while finding order with name " + name);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return order;
    }

    @Override
    public boolean delete(String name) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_ORDER_BY_NAME));
            st.setString(1, name);
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting order with name " + name);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_ORDER_BY_NAME));
            st.setString(1, order.getOrderName());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting order " + order);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return false;
    }

    @Override
    public boolean create(Order order) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_INSERT_ORDER), Statement.RETURN_GENERATED_KEYS);
            st.setString(1, order.getOrderName());
            st.setDouble(2, order.getCarSpeed());
            st.setDouble(3, order.getCarCapacity());
            st.setString(4, order.getStatus().toString());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                order.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating order " + order);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return false;
    }

    @Override
    public Order update(Order order) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_UPDATE_ORDER));
            st.setString(1, order.getOrderName());
            st.setDouble(2, order.getCarSpeed());
            st.setDouble(3, order.getCarCapacity());
            st.setString(4, order.getStatus().toString());
            st.setInt(5, order.getId());
            st.executeUpdate();
            return order;
        } catch (SQLException e) {
            logger.error("Error while updating order " + order);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return order;
    }

    @Override
    public Order findOrderById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Order order = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ORDER_BY_ID));
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(ORDER_ID));
                order.setCarCapacity(rs.getDouble(CAR_CAPACITY));
                order.setCarSpeed(rs.getDouble(CAR_SPEED));
                order.setOrderName(rs.getString(ORDER_NAME));
                order.setStatus(TripStatusEnum.valueOf(rs.getString(TRIP_STATUS)));
            }
            if (order == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
            return order;
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Error while finding order with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return order;
    }
}
