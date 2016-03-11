package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.CarDAO;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.model.Car;
import by.gstu.autobase.model.Trip;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 18.11.2015.
 */
public class ShowTripsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_NO_TRIPS = "noTrips";
    private static final String PARAM_NAME_TRIPS = "trips";
    private static final String PARAM_NAME_USER = "user";

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_DRIVER_PAGE_PATH = "path.page.main.driver";

    /**
     * Messages, which will be print on jsp
     */
    private static final String MESSAGE_NO_TRIPS = "message.no.trips";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private TripDAO tripDAO = mySql.getTripDAO();

    /**
     * Finds trips for driver
     *
     * @param request
     * @return page - redirect to main driver page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = (String) request.getSession().getAttribute(PARAM_NAME_USER);

        /* List with trips for this driver-account */
        List<Trip> trips = tripDAO.findTripsByLogin(login);

        if (trips.size() > 0) {
            request.setAttribute(PARAM_NAME_TRIPS, trips);
        } else {
            request.setAttribute(PARAM_NAME_NO_TRIPS, getMessage(MESSAGE_NO_TRIPS));
        }
        page = MappingUtility.getInstance().getPath(MAIN_DRIVER_PAGE_PATH);

        return page;
    }
    private String getMessage(String message) {
        return MessageUtility.getInstance().getMessage(message);
    }
}
