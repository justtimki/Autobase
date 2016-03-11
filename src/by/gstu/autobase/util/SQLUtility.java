package by.gstu.autobase.util;

import java.util.ResourceBundle;

/**
 * Created by Alexandr Kolymago on 08.11.2015.
 */
public class SQLUtility {

    private static SQLUtility instance;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.sql");

    private SQLUtility() {
    }

    synchronized public static SQLUtility getInstance() {
        if (instance == null)
            instance = new SQLUtility();
        return instance;
    }

    public String getQuery(String query) {
        return bundle.getString(query);
    }
}
