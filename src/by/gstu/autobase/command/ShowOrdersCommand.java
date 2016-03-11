package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.OrderDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.model.Order;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 17.01.2016.
 */
public class ShowOrdersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_NO_ORDERS = "noOrders";
    private static final String PARAM_NAME_WAITING_ORDERS = "waitingOrders";
    private static final String PARAM_NAME_PERFORMED_ORDERS = "performedOrders";
    private static final String PARAM_NAME_DONE_ORDERS = "doneOrders";

    /**
     * URL, gets from mapping.properties
     */
    private static final String ORDERS_PAGE_PATH = "path.page.orders";

    /**
     * Messages, which will be print on jsp
     */
    private static final String MESSAGE_NO_ORDERS = "message.no.orders";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private OrderDAO orderDAO = mySql.getOrderDAO();

    /**
     * Show orders. Orders are sorted by their status.
     *
     * @param request
     * @return - redirect to orders page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Order> orders = orderDAO.findAll();
        ArrayList<Order> performedOrders = new ArrayList<>();
        ArrayList<Order> waitingOrders = new ArrayList<>();
        ArrayList<Order> doneOrders = new ArrayList<>();

        if (orders.size() > 0) {
            for (Order item : orders) {
                switch (item.getStatus()) {
                    case PERFORMED:
                        performedOrders.add(item);
                        break;
                    case WAITING:
                        waitingOrders.add(item);
                        break;
                    case DONE:
                        doneOrders.add(item);
                        break;
                }
            }
            request.setAttribute(PARAM_NAME_WAITING_ORDERS, waitingOrders);
            request.setAttribute(PARAM_NAME_DONE_ORDERS, doneOrders);
            request.setAttribute(PARAM_NAME_PERFORMED_ORDERS, performedOrders);
        } else {
            request.setAttribute(PARAM_NAME_NO_ORDERS, getMessage(MESSAGE_NO_ORDERS));
        }

        page = MappingUtility.getInstance().getPath(ORDERS_PAGE_PATH);

        return page;
    }

    private String getMessage(String message) {
        return MessageUtility.getInstance().getMessage(message);
    }
}
