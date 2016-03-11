package by.gstu.autobase.database.DAO.mysqlDAOobjects;

import by.gstu.autobase.database.ConnectorDB;
import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.model.Account;
import by.gstu.autobase.util.CloseUtility;
import by.gstu.autobase.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of AccountDAO for MySQL
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class MySQLAccountDAO implements AccountDAO {

    private static final Logger logger = LogManager.getLogger();

    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT_ALL_ACCOUNTS";
    private static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT_ACCOUNT_BY_ID";
    private static final String SQL_SELECT_ACCOUNT_BY_NAME = "SELECT_ACCOUNT_BY_NAME";
    private static final String SQL_SELECT_ACCOUNT_BY_CAR = "SELECT_ACCOUNT_BY_CAR";
    private static final String SQL_SELECT_DRIVERS_ACCOUNTS = "SELECT_DRIVERS_ACCOUNTS";
    private static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE_ACCOUNT_BY_ID";
    private static final String SQL_INSERT_ACCOUNT = "INSERT_ACCOUNT";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE_ACCOUNT";

    /**
     * Columns name
     */
    private static final String ACCOUNT_ID = "accountId";
    private static final String ACCOUNT_NAME = "accountName";
    private static final String IS_DISPATCHER = "isDispatcher";
    private static final String PASSWORD = "password";
    private static final String CAR = "car";

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
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Account acc = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ALL_ACCOUNTS), Statement.RETURN_GENERATED_KEYS);
            rs = st.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt(ACCOUNT_ID));
                acc.setAccountName(rs.getString(ACCOUNT_NAME));
                acc.setPassword(rs.getString(PASSWORD));
                acc.setIsDispatcher(rs.getBoolean(IS_DISPATCHER));
                if (!acc.isDispatcher()) {
                    acc.setCarName(rs.getString(CAR));
                }
                accounts.add(acc);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all accounts.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return accounts;
    }

    @Override
    public Account findAccountById(int id) {
        PreparedStatement st = null;
        Account acc = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ACCOUNT_BY_ID));
            st.setLong(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt(ACCOUNT_ID));
                acc.setAccountName(rs.getString(ACCOUNT_NAME));
                acc.setPassword(rs.getString(PASSWORD));
                acc.setIsDispatcher(rs.getBoolean(IS_DISPATCHER));
                if (!acc.isDispatcher()) {
                    acc.setCarName(rs.getString(CAR));
                }
            }
            if (acc == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return acc;
    }

    @Override
    public Account findAccountByName(String login) {
        PreparedStatement st = null;
        Account acc = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ACCOUNT_BY_NAME));
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt(ACCOUNT_ID));
                acc.setAccountName(rs.getString(ACCOUNT_NAME));
                acc.setPassword(rs.getString(PASSWORD));
                acc.setIsDispatcher(rs.getBoolean(IS_DISPATCHER));
                if (!acc.isDispatcher()) {
                    acc.setCarName(rs.getString(CAR));
                }
            }
            if (acc == null) {
                logger.warn("Can't find record with login [" + login + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account with login " + login);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return acc;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_ACCOUNT_BY_ID));
            st.setInt(1, id);
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting account with id " + id);
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
    public boolean delete(Account account) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_DELETE_ACCOUNT_BY_ID));
            st.setInt(1, account.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting account " + account);
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
    public boolean create(Account account) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_INSERT_ACCOUNT), Statement.RETURN_GENERATED_KEYS);
            st.setString(1, account.getAccountName());
            st.setString(2, account.getPassword());
            st.setString(3, account.getCarName());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                logger.trace(rs.getInt(1));
                account.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating account " + account);
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
    public Account update(Account account) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_UPDATE_ACCOUNT));
            st.setString(1, account.getAccountName());
            st.setString(2, account.getPassword());
            st.setBoolean(3, account.isDispatcher());
            st.setString(4, account.getCarName());
            st.setInt(5, account.getId());
            st.executeUpdate();
            return account;
        } catch (SQLException e) {
            logger.error("Error while updating account " + account);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return account;
    }

    @Override
    public List<Account> getAllDriversAccount() {
        List<Account> accountList = new ArrayList<>();
        Account acc = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_DRIVERS_ACCOUNTS));
            rs = st.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt(ACCOUNT_ID));
                acc.setAccountName(rs.getString(ACCOUNT_NAME));
                acc.setPassword(rs.getString(PASSWORD));
                acc.setIsDispatcher(rs.getBoolean(IS_DISPATCHER));
                if (!acc.isDispatcher()) {
                    acc.setCarName(rs.getString(CAR));
                }
                accountList.add(acc);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all driver's accounts");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return accountList;
    }

    @Override
    public Account findAccountByCar(String carName) {
        PreparedStatement st = null;
        Account acc = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getInstance().getQuery(SQL_SELECT_ACCOUNT_BY_CAR));
            st.setString(1, carName);
            rs = st.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getInt(ACCOUNT_ID));
                acc.setAccountName(rs.getString(ACCOUNT_NAME));
                acc.setPassword(rs.getString(PASSWORD));
                acc.setIsDispatcher(rs.getBoolean(IS_DISPATCHER));
                if (!acc.isDispatcher()) {
                    acc.setCarName(rs.getString(CAR));
                }
            }
            if (acc == null) {
                logger.warn("Can't find record with car [" + carName + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account by car name " + carName);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.getInstance().close(st);
            if (rs != null)
                CloseUtility.getInstance().close(rs);
            if (connection != null)
                CloseUtility.getInstance().close(connection);
        }
        return acc;
    }
}
