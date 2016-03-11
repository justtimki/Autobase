package by.gstu.autobase.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */

/**
 * Interface for commands
 */
public interface Command {
    String execute(HttpServletRequest request);
}
