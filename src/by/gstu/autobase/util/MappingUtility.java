package by.gstu.autobase.util;

import java.util.ResourceBundle;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public class MappingUtility {
    private static MappingUtility instance;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.mapping");

    synchronized public static MappingUtility getInstance() {
        if (instance == null)
            instance = new MappingUtility();
        return instance;
    }

    private MappingUtility() {
    }

    public String getPath(String key) {
        return bundle.getString(key);
    }

}
