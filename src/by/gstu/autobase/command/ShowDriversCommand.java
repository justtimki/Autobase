package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.util.MappingUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 19.11.2015.
 */
public class ShowDriversCommand implements Command {

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_DISPATCHER_PAGE_PATH = "path.page.main.dispatcher";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_DRIVERS = "drivers";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private AccountDAO driverAccounts = mySql.getAccountDAO();

    /**
     * Show all drivers to dispatcher
     *
     * @param request
     * @return page - redirect to main dispatcher page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(PARAM_NAME_DRIVERS, driverAccounts.getAllDriversAccount());
        page = MappingUtility.getInstance().getPath(MAIN_DISPATCHER_PAGE_PATH);

        return page;
    }
}
