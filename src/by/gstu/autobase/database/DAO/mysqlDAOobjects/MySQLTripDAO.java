package by.gstu.autobase.database.DAO.mysqlDAOobjects;

import by.gstu.autobase.database.ConnectorDB;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.enumeration.TripStatusEnum;
import by.gstu.autobase.model.Trip;
import by.gstu.autobase.util.CloseUtility;
import by.gstu.autobase.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TripDAO for MySQL
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class MySQLTripDAO implements TripDAO {

    private static final Logger logger = LogManager.getLogger();

    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_TRIPS = "SELECT_ALL_TRIPS";
    private static final String SQL_SELECT_TRIP_BY_ID = "SELECT_TRIP_BY_ID";
    private static final String SQL_SELECT_TRIP_BY_NAME = "SELECT_TRIP_BY_NAME";
    private static final String SQL_SELECT_TRIPS_BY_LOGIN = "SELECT_TRIPS_BY_NAME";
    private static final String SQL_DELETE_TRIP_BY_ID = "DELETE_TRIP_BY_ID";
    private static final String SQL_INSERT_TRIP = "INSERT_TRIP";
    private static final String SQL_UPDATE_TRIP = "UPDATE_TRIP";

    /**
     * Columns name
     */
    private static final String TRIP_ID = "tripId";
    private static final String TRIP_DATE = "tripDate";
    private static final String CAR_NAME = "carName";
    private static final String TRIP_NAME = "tripName";
    private static final String DRIVER_NAME = "driverName";

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
    public List<Trip> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Trip> trips = new ArrayList<>();
        Trip newTrip = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ALL_TRIPS));
            rs = st.executeQuery();
            while (rs.next()) {
                newTrip = new Trip();
                newTrip.setId(rs.getInt(TRIP_ID));
                newTrip.setTripDate(rs.getDate(TRIP_DATE));
                newTrip.setTripName(rs.getString(TRIP_NAME));
                newTrip.setDriverName(rs.getString(DRIVER_NAME));
                newTrip.setCarName(rs.getString(CAR_NAME));
                trips.add(newTrip);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all trips.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return trips;
    }

    @Override
    public Trip findTripById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Trip trip = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_TRIP_BY_ID));
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                trip = new Trip();
                trip.setId(rs.getInt(TRIP_ID));
                trip.setTripDate(rs.getDate(TRIP_DATE));
                trip.setTripName(rs.getString(TRIP_NAME));
                trip.setDriverName(rs.getString(DRIVER_NAME));
                trip.setCarName(rs.getString(CAR_NAME));
            }
            if (trip == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
            return trip;

        } catch (SQLException e) {
            logger.error("Error while finding trip with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return trip;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_TRIP_BY_ID));
            st.setInt(1, id);
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting trip with id " + id);
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
    public boolean delete(Trip trip) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_TRIP_BY_ID));
            st.setInt(1, trip.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return false;
    }

    @Override
    public boolean create(Trip trip) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_INSERT_TRIP), Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, trip.getTripDate());
            st.setString(2, trip.getTripName());
            st.setString(3, trip.getCarName());
            st.setString(4, trip.getDriverName());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                logger.trace(rs.getInt(1));
                trip.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
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
    public Trip update(Trip trip) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_UPDATE_TRIP), Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, trip.getTripDate());
            st.setString(2, trip.getTripName());
            st.setString(3, trip.getCarName());
            st.setInt(4, trip.getId());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            return trip;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return trip;
    }

    @Override
    public List<Trip> findTripsByLogin(String login) {
        List<Trip> trips = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        Trip trip = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_TRIPS_BY_LOGIN));
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                trip = new Trip();
                trip.setId(rs.getInt(TRIP_ID));
                trip.setTripDate(rs.getDate(TRIP_DATE));
                trip.setTripName(rs.getString(TRIP_NAME));
                trip.setDriverName(rs.getString(DRIVER_NAME));
                trip.setCarName(rs.getString(CAR_NAME));
                trips.add(trip);
            }
            return trips;
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return trips;
    }

    @Override
    public Trip findTripByName(String tripName) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Trip trip = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_TRIP_BY_NAME));
            st.setString(1, tripName);
            rs = st.executeQuery();
            while (rs.next()) {
                trip = new Trip();
                trip.setId(rs.getInt(TRIP_ID));
                trip.setTripDate(rs.getDate(TRIP_DATE));
                trip.setTripName(rs.getString(TRIP_NAME));
                trip.setDriverName(rs.getString(DRIVER_NAME));
                trip.setCarName(rs.getString(CAR_NAME));
            }
        }  catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return trip;
    }
}

