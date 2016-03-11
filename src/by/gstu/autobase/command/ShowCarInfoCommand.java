package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.model.Car;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 16.01.2016.
 */
public class ShowCarInfoCommand implements Command {

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_CAR_NAME = "carName";
    private static final String PARAM_NAME_CAR_SPEED = "speed";
    private static final String PARAM_NAME_CAR_CAPACITY = "capacity";
    private static final String PARAM_NAME_CAR_IS_HEALTHY = "isHealthy";
    private static final String PARAM_NAME_USER = "user";

    /**
     * Messages, which will be print on jsp
     */
    private static final String MESSAGE_YES = "message.yes";
    private static final String MESSAGE_NO = "message.no";

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_CAR_PAGE_PATH = "path.page.car";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private AccountDAO accountDAO = mySql.getAccountDAO();
    private CarDAO carDAO = mySql.getCarDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_USER);

        String carName = accountDAO.findAccountByName(login).getCarName();
        Car car = carDAO.findCarByName(carName);

        if (car != null) {
            request.setAttribute(PARAM_NAME_CAR_NAME, car.getCarName());
            request.setAttribute(PARAM_NAME_CAR_SPEED, car.getSpeed());
            request.setAttribute(PARAM_NAME_CAR_CAPACITY, car.getCapacity());
            String isHealthy = car.isHealthy() ? getMessage(MESSAGE_YES) : getMessage(MESSAGE_NO);
            request.setAttribute(PARAM_NAME_CAR_IS_HEALTHY, isHealthy);
        }
        page = MappingUtility.getInstance().getPath(MAIN_CAR_PAGE_PATH);
        //page = "/jsp/car/carInfo.jsp";

        return page;
    }

    private String getMessage(String message) {
        return MessageUtility.getInstance().getMessage(message);
    }
}
