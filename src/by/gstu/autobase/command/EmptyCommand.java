package by.gstu.autobase.command;

import by.gstu.autobase.util.MappingUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class EmptyCommand implements Command {

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE_PATH = "path.page.login";

    /**
     * Executes when command is empty
     *
     * @param request
     * @return page - redirect to login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return MappingUtility.getInstance().getPath(LOGIN_PAGE_PATH);
    }
}
