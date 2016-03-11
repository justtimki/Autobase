package by.gstu.autobase.model;

import by.gstu.autobase.enumeration.TripStatusEnum;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class Trip extends Entity implements Serializable {

    private Date tripDate;
    private String tripName;
    private String driverName;
    private String carName;

    public Trip() {
    }

    public Trip(int id, Date flightDate, String tripName, String driverName, String carName) {
        super(id);
        this.tripDate = flightDate;
        this.tripName = tripName;
        this.driverName = driverName;
        this.carName = carName;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Trip #" + super.getId() + " " + tripName + " date " + tripDate.toString() + ". Attached car " + carName;
    }
}
