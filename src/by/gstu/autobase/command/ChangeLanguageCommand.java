package by.gstu.autobase.command;

import by.gstu.autobase.util.MappingUtility;
import by.gstu.autobase.util.MessageUtility;
import by.gstu.autobase.util.TranslateUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Alexandr Kolymago on 09.12.2015.
 */
public class ChangeLanguageCommand implements Command {

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE = "path.page.login";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_LANG = "lang";

    /**
     * Languages
     */
    private static final String ENG = "eng";
    private static final String RUS = "rus";

    /**
     * Change language of all project
     *
     * @param request
     * @return page - redirect to login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = MappingUtility.getInstance().getPath(LOGIN_PAGE);
        String lang = request.getParameter(PARAM_NAME_LANG);

        switch (lang) {
            case ENG:
                changeLanguage("en", "US");
                break;
            case RUS:
                changeLanguage("ru", "RU");
                break;
        }

        return page;
    }

    private void changeLanguage(String lang, String country) {
        MessageUtility.getInstance().changeResources(new Locale(lang, country));
        TranslateUtility.getInstance().changeResources(new Locale(lang, country));
    }
}
