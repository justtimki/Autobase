package by.gstu.autobase.util;

import by.gstu.autobase.database.ConnectorDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class CloseUtility {
    private static final Logger logger = LogManager.getLogger();
    private static CloseUtility instance;

    private CloseUtility() {
    }

    synchronized public static CloseUtility getInstance() {
        if (instance == null)
            instance = new CloseUtility();
        return instance;
    }

    public void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error("Can't close statement!");
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("Can't close result set!");
                e.printStackTrace();
            }
        }
    }

    public void close(Connection cn) {
        if (cn != null) {
            try {
                cn.close();
                ConnectorDB.getInstance().freeConnection(cn);
            } catch (SQLException e) {
                logger.error("Can't close connection!");
                e.printStackTrace();
            }
        }
    }
}
