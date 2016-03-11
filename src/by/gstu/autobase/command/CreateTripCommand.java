package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.OrderDAO;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.enumeration.TripStatusEnum;
import by.gstu.autobase.model.Account;
import by.gstu.autobase.model.Car;
import by.gstu.autobase.model.Order;
import by.gstu.autobase.model.Trip;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 18.11.2015.
 */
public class CreateTripCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_ORDER_ID = "orderId";
    private static final String PARAM_NAME_DRIVERS = "drivers";
    private static final String PARAM_NAME_CHOSE_DRIVER = "chosenDriver";
    private static final String PARAM_NAME_NEW_TRIP = "newTrip";
    private static final String PARAM_NAME_TRIP_NAME = "tripName";

    /**
     * Messages, which will be print on jsp
     */
    private static final String SUCCESS_TRIP_MESSAGE = "message.success.add.trip";

    /**
     * URL, gets from mapping.properties
     */
    private static final String PATH_CREATE_TRIP_PAGE = "path.page.trip.create";
    private static final String PATH_ORDERS_PAGE = "path.page.orders";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private TripDAO tripDAO = mySql.getTripDAO();
    private CarDAO carDAO = mySql.getCarDAO();
    private OrderDAO orderDAO = mySql.getOrderDAO();
    private AccountDAO accountDAO = mySql.getAccountDAO();

    /**
     * If choosingDriver == null, then trip still not created and dispatcher offered to choose drivers from an suitableDrivers
     * driver, which car is meets the requirements from order;
     * If choosingDriver != null, then trip created.
     *
     * @param request
     * @return page - if(choosingDriver != null) redirect to orders page, else redirect to create trip page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String choosingDriver = request.getParameter(PARAM_NAME_CHOSE_DRIVER);
        if (choosingDriver != null) {
            String tripName = request.getParameter(PARAM_NAME_TRIP_NAME);
            String carName = getCarName(choosingDriver);
            Date tripDate = new Date(new java.util.Date().getTime());
            Trip trip = createTrip(tripName, choosingDriver, carName, tripDate);

            logger.info(trip + " was created.");

            request.setAttribute(PARAM_NAME_NEW_TRIP, getMessage(SUCCESS_TRIP_MESSAGE));
            page = MappingUtility.getInstance().getPath(PATH_ORDERS_PAGE);
        } else {
            int orderId = Integer.parseInt(request.getParameter(PARAM_NAME_ORDER_ID));
            Order order = orderDAO.findOrderById(orderId);
            double requiredSpeed = order.getCarSpeed();
            double requiredCapacity = order.getCarCapacity();
            String tripName = order.getOrderName();

            order.setStatus(TripStatusEnum.PERFORMED);
            orderDAO.update(order);

            List<Account> drivers = accountDAO.getAllDriversAccount();
            List<Account> suitableDrivers = new ArrayList<>();

            for (Account driver : drivers) {
                String driverCarName = driver.getCarName();
                Car car = carDAO.findCarByName(driverCarName);
                if (car.getSpeed() >= requiredSpeed && car.getCapacity() >= requiredCapacity) {
                    suitableDrivers.add(driver);
                }
            }

            request.setAttribute(PARAM_NAME_TRIP_NAME, tripName);
            request.setAttribute(PARAM_NAME_DRIVERS, suitableDrivers);

            page = MappingUtility.getInstance().getPath(PATH_CREATE_TRIP_PAGE);
        }
        return page;
    }

    private String getMessage(String message) {
        return MessageUtility.getInstance().getMessage(message);
    }

    private String getCarName(String driverName) {
        return accountDAO.findAccountByName(driverName).getCarName();
    }

    private Trip createTrip(String tripName, String driverName, String carName, Date tripDate) {
        Trip trip = new Trip();
        trip.setTripName(tripName);
        trip.setCarName(carName);
        trip.setTripDate(tripDate);
        trip.setDriverName(driverName);
        tripDAO.create(trip);
        return trip;
    }
}
