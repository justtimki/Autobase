package by.gstu.autobase.database.DAO.factory;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.database.DAO.OrderDAO;
import by.gstu.autobase.database.DAO.mysqlDAOobjects.MySQLAccountDAO;
import by.gstu.autobase.database.DAO.mysqlDAOobjects.MySQLCarDAO;
import by.gstu.autobase.database.DAO.mysqlDAOobjects.MySQLTripDAO;
import by.gstu.autobase.database.DAO.mysqlDAOobjects.MySQLOrderDAO;

/**
 * Factory for MySQL, handle DAO
 * Created by Alexandr Kolymago on 06.11.2015.
 */
public class MySQLDAOFactory extends DAOFactory {

    @Override
    public AccountDAO getAccountDAO() {
        return new MySQLAccountDAO();
    }

    @Override
    public TripDAO getTripDAO() {
        return new MySQLTripDAO();
    }

    @Override
    public CarDAO getCarDAO() {
        return new MySQLCarDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySQLOrderDAO();
    }
}
