package by.gstu.autobase.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Alexandr Kolymago on 07.12.2015.
 */
public class TranslateUtility {
    private static TranslateUtility instance;
    private static final String RESOURCE_NAME = "resources.translate";
    private ResourceBundle bundle;

    synchronized public static TranslateUtility getInstance() {
        if (instance == null)
            instance = new TranslateUtility();
        return instance;
    }

    private TranslateUtility() {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public void changeResources(Locale locale) {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
