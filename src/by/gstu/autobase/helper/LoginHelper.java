package by.gstu.autobase.helper;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.model.Account;

/**
 * Created by Alexandr Kolymago on 03.12.2015.
 */
public class LoginHelper {

    private static LoginHelper instance;

    private LoginHelper() {
    }

    synchronized public static LoginHelper getInstance() {
        if (instance == null)
            instance = new LoginHelper();
        return instance;
    }

    /**
     * Check account by login and password
     *
     * @param login    - login
     * @param password - password
     * @return true - if account is found , false - if account isn't found
     */
    public boolean checkAccount(String login, String password) {
        DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AccountDAO accountDAO = mySql.getAccountDAO();
        for (Account item : accountDAO.findAll()) {
            if (item.getAccountName().equals(login) && item.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check account by login
     *
     * @param login - login
     * @return true - if account is found , false - if account isn't found
     */
    public boolean checkLogin(String login) {
        DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AccountDAO accountDAO = mySql.getAccountDAO();
        for (Account item : accountDAO.findAll()) {
            if (item.getAccountName().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
