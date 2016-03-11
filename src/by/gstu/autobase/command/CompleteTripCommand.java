package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.OrderDAO;
import by.gstu.autobase.database.DAO.TripDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.enumeration.TripStatusEnum;
import by.gstu.autobase.model.Order;
import by.gstu.autobase.model.Trip;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 25.11.2015.
 */
public class CompleteTripCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * URL, gets from mapping.properties
     */
    private static final String COMPLETE_TRIP_PAGE_PATH = "path.complete.trip";

    /**
     * Params from jsp page
     */
    private static final String TRIP_NAME_PARAM = "tripName";
    private static final String PARAM_NAME_TRIP_NOT_COMPLETE = "tripNotComplete";
    private static final String PARAM_NAME_COMPLETE = "tripComplete";

    /**
     * Messages, which will be print on jsp
     */
    private static final String TRIP_NOT_COMPLETE_ERROR = "message.error.trip.not.complete";
    private static final String COMPLETE_MESSAGE = "message.trip.complete";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private TripDAO tripDAO = mySql.getTripDAO();
    private OrderDAO orderDAO = mySql.getOrderDAO();

    /**
     * Complete (delete from database) trip
     *
     * @param request
     * @return page - redirect to main driver page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String tripName = request.getParameter(TRIP_NAME_PARAM);
        Trip trip = findTrip(tripName);
        if (trip == null) {
            logger.error("Trip name is empty.");
            request.setAttribute(PARAM_NAME_TRIP_NOT_COMPLETE, getMessage(TRIP_NOT_COMPLETE_ERROR));
        } else {
            tripDAO.delete(trip);
            request.setAttribute(PARAM_NAME_COMPLETE, tripName + " " + getMessage(COMPLETE_MESSAGE));
            Order order = orderDAO.findOrderByName(tripName);
            order.setStatus(TripStatusEnum.DONE);
            orderDAO.update(order);
            logger.info("Order " + tripName + " status changing on: " + order.getStatus());
            logger.info("Trip completed " + tripName);
        }

        page = MappingUtility.getInstance().getPath(COMPLETE_TRIP_PAGE_PATH);

        return page;
    }

    private Trip findTrip(String tripName) {
        if (tripName.isEmpty())
            return null;
        return tripDAO.findTripByName(tripName);
    }

    private String getMessage(String message) {
        return MessageUtility.getInstance().getMessage(message);
    }
}
