package by.gstu.autobase.util;

import java.util.ResourceBundle;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class ConfigurationUtility {

    private static ConfigurationUtility instance;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.database");

    private ConfigurationUtility() {
    }

    synchronized public static ConfigurationUtility getInstance() {
        if (instance == null)
            instance = new ConfigurationUtility();

        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
