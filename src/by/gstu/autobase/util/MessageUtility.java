package by.gstu.autobase.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Alexandr Kolymago on 25.11.2015.
 */
public class MessageUtility {
    private static MessageUtility instance;
    private ResourceBundle bundle;
    private static final String RESOURCE_NAME = "resources.message";

    synchronized public static MessageUtility getInstance() {
        if (instance == null)
            instance = new MessageUtility();
        return instance;
    }

    private MessageUtility() {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public void changeResources(Locale locale) {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
