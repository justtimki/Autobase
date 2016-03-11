package by.gstu.autobase.database;

import by.gstu.autobase.util.ConfigurationUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Connection pool.
 * Created by Alexandr Kolymago on 06.11.2015.
 */
public class ConnectorDB {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Parameters of database connection
     */
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_POOLSIZE = "db.poolsize";

    private static ConnectorDB instance;
    private static List<Connection> freeConnections = new ArrayList<>();
    private String driverName;
    private String user;
    private String pass;
    private String url;
    private int poolSize;
    private Driver driver;
    private int count;

    /**
     * Constructor.
     * Gets values from database.properties and initialize variables.
     * Fill connection pool.
     */
    private ConnectorDB() {
        driverName = ConfigurationUtility.getInstance().getValue(DB_DRIVER);
        user = ConfigurationUtility.getInstance().getValue(DB_USER);
        pass = ConfigurationUtility.getInstance().getValue(DB_PASSWORD);
        url = ConfigurationUtility.getInstance().getValue(DB_URL);
        poolSize = Integer.parseInt(ConfigurationUtility.getInstance().getValue(DB_POOLSIZE));
        try {
            driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            logger.error("Error while registering driver! " + e.getMessage());
        }
        count = 0;
        Connection connection;
        for (int i = 0; i < poolSize; i++) {
            connection = newConnection();
            if(connection == null)
                return;
            freeConnections.add(connection);
        }
    }

    static synchronized public ConnectorDB getInstance() {
        if (instance == null) {
            logger.info("Connection to DataBase...");
            instance = new ConnectorDB();
        }

        return instance;
    }

    /**
     * Method to get connections.
     *
     * @return Connection
     */
    public synchronized Connection getConnection() {
        Connection connection = null;
        if (freeConnections.size() > 0) {
            connection = freeConnections.get(0);
            freeConnections.remove(0);
            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                connection = getConnection();
            }
            logger.trace("get connection");
            return connection;
        }
        if (count < poolSize) {
            connection = newConnection();
            if(connection != null) {
                logger.info("New connection created.");
                count++;
            }
        }
        return connection;
    }

    /**
     * Method to create new connection object.
     *
     * @return Connection.
     */
    private Connection newConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return connection;
    }

    /**
     * Method to add free connections in to pool.
     *
     * @param connection
     */
    public synchronized void freeConnection(Connection connection) {
        freeConnections.add(connection);
        count--;
        notifyAll();
    }

    /**
     * Method to destroy all connections.
     */
    public void destroy() {
        closeAll();
        try {
            DriverManager.deregisterDriver(driver);
            logger.debug("Destroy all connections.");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method to close all resources
     */
    private synchronized void closeAll() {
        for (Connection item : freeConnections) {
            try {
                item.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        freeConnections.clear();
    }

    @Override
    public void finalize() {
        try {
            super.finalize();
            destroy();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
