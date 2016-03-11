package by.gstu.autobase.database.DAO.mysqlDAOobjects;

import by.gstu.autobase.database.ConnectorDB;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.model.Car;
import by.gstu.autobase.util.CloseUtility;
import by.gstu.autobase.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of CarDAO for MySQL
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class MySQLCarDAO implements CarDAO {

    private static final Logger logger = LogManager.getLogger();

    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_AUTO = "SELECT_ALL_AUTO";
    private static final String SQL_SELECT_AUTO_BY_ID = "SELECT_AUTO_BY_ID";
    private static final String SQL_SELECT_AUTO_BY_NAME = "SELECT_AUTO_BY_NAME";
    private static final String SQL_DELETE_AUTO_BY_ID = "DELETE_AUTO_BY_ID";
    private static final String SQL_INSERT_AUTO = "INSERT_AUTO";
    private static final String SQL_UPDATE_AUTO = "UPDATE_AUTO";

    /**
     * Columns name
     */
    private static final String CAR_ID = "carId";
    private static final String CAR_NAME = "carName";
    private static final String SPEED = "speed";
    private static final String IS_HEALTHY = "isHealthy";
    private static final String CAPACITY = "capacity";

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
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        Car car = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ALL_AUTO), Statement.RETURN_GENERATED_KEYS);
            rs = st.executeQuery();
            while (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(CAR_ID));
                car.setCarName(rs.getString(CAR_NAME));
                car.setSpeed(rs.getFloat(SPEED));
                car.setIsHealthy(rs.getBoolean(IS_HEALTHY));
                car.setCapacity(rs.getFloat(CAPACITY));
                cars.add(car);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all cars.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return cars;
    }

    @Override
    public Car findCarById(int id) {
        PreparedStatement st = null;
        Car car = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_AUTO_BY_ID));
            st.setLong(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(CAR_ID));
                car.setCarName(rs.getString(CAR_NAME));
                car.setSpeed(rs.getFloat(SPEED));
                car.setIsHealthy(rs.getBoolean(IS_HEALTHY));
                car.setCapacity(rs.getFloat(CAPACITY));
            }
            if (car == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding car with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return car;
    }

    @Override
    public Car findCarByName(String name) {
        PreparedStatement st = null;
        Car car = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_AUTO_BY_NAME));
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(CAR_ID));
                car.setCarName(rs.getString(CAR_NAME));
                car.setSpeed(rs.getFloat(SPEED));
                car.setIsHealthy(rs.getBoolean(IS_HEALTHY));
                car.setCapacity(rs.getFloat(CAPACITY));
            }
            if (car == null) {
                logger.warn("Can't find record with name [" + name + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding car with name " + name);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return car;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_AUTO_BY_ID));
            st.setInt(1, id);
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting car with id " + id);
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
    public boolean delete(Car car) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_AUTO_BY_ID));
            st.setInt(1, car.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting car " + car);
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
    public boolean create(Car car) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_INSERT_AUTO), Statement.RETURN_GENERATED_KEYS);
            st.setString(1, car.getCarName());
            st.setFloat(2, car.getSpeed());
            st.setBoolean(3, car.isHealthy());
            st.setFloat(4, car.getCapacity());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                logger.trace(rs.getInt(1));
                car.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating car " + car);
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
    public Car update(Car car) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_UPDATE_AUTO));
            st.setString(1, car.getCarName());
            st.setFloat(2, car.getSpeed());
            st.setBoolean(3, car.isHealthy());
            st.setFloat(4, car.getCapacity());
            st.setInt(5, car.getId());
            st.executeUpdate();
            return car;
        } catch (SQLException e) {
            logger.error("Error while updating car " + car);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return car;
    }
}

