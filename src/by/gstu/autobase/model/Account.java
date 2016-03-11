package by.gstu.autobase.model;

import java.io.Serializable;

/**
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class Account extends Entity implements Serializable {

    private String accountName;
    private String password;
    private boolean isDispatcher;
    private String carName;

    public Account() {
    }

    public Account(int id, String accountName, String password, String carName) {
        super(id);
        this.accountName = accountName;
        this.password = password;
        this.carName = carName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDispatcher() {
        return isDispatcher;
    }

    public void setIsDispatcher(boolean isDispatcher) {
        this.isDispatcher = isDispatcher;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Override
    public String toString() {
        if (isDispatcher)
            return "Account: [" + accountName + "] Id: [" + super.getId() + "] Dispatcher!\n";
        else
            return "Account: [" + accountName + "] Id: [" + super.getId() + "] Driver with car " + carName + "\n";
    }
}
