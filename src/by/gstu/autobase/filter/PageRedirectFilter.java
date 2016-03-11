package by.gstu.autobase.filter;

import by.gstu.autobase.enumeration.ClientTypeEnum;
import by.gstu.autobase.enumeration.PermissibleLinksEnum;
import by.gstu.autobase.util.MappingUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexandr Kolymago on 07.12.2015.
 */
@WebFilter(filterName = "PageRedirectFilter", urlPatterns = {"/jsp/*"}, initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private String indexPath;
    private static final String PARAM_USER = "user";
    private static final String PARAM_CLIENT_TYPE = "clientType";
    private static final String PATH_MAIN_DRIVER_PAGE = "path.page.main.driver";
    private static final String PATH_MAIN_DISPATCHER_PAGE = "path.page.main.dispatcher";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(false);
        String user = (String) session.getAttribute(PARAM_USER);
        ClientTypeEnum clientType = (ClientTypeEnum) session.getAttribute(PARAM_CLIENT_TYPE);
        String page = httpRequest.getRequestURI();

        if (clientType == null) {
            clientType = ClientTypeEnum.GUEST;
            session.setAttribute(PARAM_CLIENT_TYPE, clientType);
        }
        logger.trace(user + " with status " + clientType.name() + " try to visit page " + page);

        if (clientType == ClientTypeEnum.GUEST) {
            if (!isPermissible(page, httpRequest.getContextPath(), clientType)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
            }
        } else if (clientType == ClientTypeEnum.DISPATCHER) {
            if (!isPermissible(page, httpRequest.getContextPath(), clientType)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + MappingUtility.getInstance().getPath(PATH_MAIN_DISPATCHER_PAGE));
            }
        } else if (clientType == ClientTypeEnum.DRIVER) {
            if (!isPermissible(page, httpRequest.getContextPath(), clientType)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + MappingUtility.getInstance().getPath(PATH_MAIN_DRIVER_PAGE));
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter("INDEX_PATH");
    }

    private boolean isPermissible(String link, String context, ClientTypeEnum clientType) {
        String path;
        for (PermissibleLinksEnum permissibleLink : PermissibleLinksEnum.values()) {
            path = context + permissibleLink.getPath(clientType);
            if (link.equals(path)) {
                return true;
            }
        }
        return false;
    }
}
