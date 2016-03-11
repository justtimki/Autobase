package by.gstu.autobase.command;

import by.gstu.autobase.util.MappingUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 19.11.2015.
 */
public class LogoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE = "path.page.login";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_USER = "user";

    /**
     * Executes, when user log out
     *
     * @param request
     * @return page - redirect to login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = MappingUtility.getInstance().getPath(LOGIN_PAGE);
        logger.info("User " + request.getSession().getAttribute(PARAM_NAME_USER) + " logout.");
        request.getSession().invalidate();
        return page;
    }
}
