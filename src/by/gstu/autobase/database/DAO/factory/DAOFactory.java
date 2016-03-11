package by.gstu.autobase.database.DAO.factory;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.database.DAO.OrderDAO;

/**
 * Handle new instance of factory.
 * Created by Alexandr Kolymago on 06.11.2015.
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;

    public abstract AccountDAO getAccountDAO();

    public abstract TripDAO getTripDAO();

    public abstract CarDAO getCarDAO();

    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(int factoryNum) {
        switch (factoryNum) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                throw new NullPointerException("No available factory.");
        }
    }

}
