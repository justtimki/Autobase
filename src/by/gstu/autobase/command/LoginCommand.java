package by.gstu.autobase.command;

import by.gstu.autobase.database.DAO.AccountDAO;
import by.gstu.autobase.database.DAO.factory.DAOFactory;
import by.gstu.autobase.enumeration.ClientTypeEnum;
import by.gstu.autobase.helper.LoginHelper;
import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_DRIVER_PAGE_PATH = "path.page.main.driver";
    private static final String MAIN_DISPATCHER_PAGE_PATH = "path.page.main.dispatcher";
    private static final String LOGIN_PAGE = "path.page.login";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_LOGIN_ERROR = "login_error";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    /**
     * Messages, which will be print on jsp
     */
    private static final String LOGIN_ERROR = "message.error.login.not.found";

    private DAOFactory mysql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private AccountDAO accountDAO = mysql.getAccountDAO();

    /**
     * If it's driver-account, redirect to page main driver page, else redirect to main dispatcher page.
     * Create session for this account.
     *
     * @param request
     * @return page - if account exist redirect to page, else prints error message.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginHelper.getInstance().checkAccount(login, pass)) {
            request.getSession().setAttribute(PARAM_NAME_USER, login);

            if (accountDAO.findAccountByName(login).isDispatcher()) {
                page = MappingUtility.getInstance().getPath(MAIN_DISPATCHER_PAGE_PATH);
                request.getSession().setAttribute("clientType", ClientTypeEnum.DISPATCHER);
            } else {
                page = MappingUtility.getInstance().getPath(MAIN_DRIVER_PAGE_PATH);
                request.getSession().setAttribute("clientType", ClientTypeEnum.DRIVER);
            }
            logger.info("User " + login + " log in.");
        } else {
            logger.warn("User isn't logged in.");
            page = MappingUtility.getInstance().getPath(LOGIN_PAGE);
            request.setAttribute(PARAM_NAME_LOGIN_ERROR, MessageUtility.getInstance().getMessage(LOGIN_ERROR));
        }

        return page;
    }
}
