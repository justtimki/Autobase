package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.model.Account;
import by.gstu.autobase.model.Car;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 23.11.2015.
 */
public class UpdateCarCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_CAR_PAGE_PATH = "path.page.car";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_CAR_IS_HEALTHY = "isHealthy";
    private static final String PARAM_NAME_CAR_UPDATE = "successCarUpdate";
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_YES = "yes";
    private static final String PARAM_NAME_NO = "no";

    /**
     * Messages, which will be print on jsp
     */
    private static final String SUCCESS_CAR_UPDATE_MESSAGE = "message.success.car.update";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private CarDAO carDAO = mySql.getCarDAO();
    private AccountDAO accountDAO = mySql.getAccountDAO();

    /**
     * Executes, when user update car information
     *
     * @param request
     * @return page - redirect to main driver page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String isCarHealthy = request.getParameter(PARAM_NAME_CAR_IS_HEALTHY);
        String login = (String) request.getSession().getAttribute(PARAM_NAME_USER);
        Account account = accountDAO.findAccountByName(login);
        Car car = carDAO.findCarByName(account.getCarName());

        if (isCarHealthy.equals(PARAM_NAME_YES)) {
            car.setIsHealthy(true);
        } else if (isCarHealthy.equals(PARAM_NAME_NO)) {
            car.setIsHealthy(false);
        }

        carDAO.update(car);
        request.setAttribute(PARAM_NAME_CAR_UPDATE, MessageUtility.getInstance().getMessage(SUCCESS_CAR_UPDATE_MESSAGE));
        logger.info(car + " was updated.");

        page = MappingUtility.getInstance().getPath(MAIN_CAR_PAGE_PATH);
        return page;
    }
}
