package by.gstu.autobase.helper;

import by.gstu.autobase.command.Command;
import by.gstu.autobase.command.EmptyCommand;
import by.gstu.autobase.enumeration.CommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class RequestHelper {

    private static final String PARAM_NAME_COMMAND = "command";
    private static final Logger logger = LogManager.getLogger();

    private static RequestHelper instance;

    synchronized public static RequestHelper getInstance() {
        if (instance == null)
            instance = new RequestHelper();
        return instance;
    }

    private RequestHelper() {
    }

    /**
     * Takes action from request and return command depending from request parameter (action)
     *
     * @param request - request from controller
     * @return command
     */
    public Command defineCommand(HttpServletRequest request) {
        Command command = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty())
            return command;
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
            command = commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Not found action with name: " + action);
            logger.error(e.getMessage());
        }
        return command;
    }
}
