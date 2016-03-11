package by.gstu.autobase.model;

import by.gstu.autobase.enumeration.TripStatusEnum;

/**
 * Created by Alexandr Kolymago on 15.01.2016.
 */
public class Order extends Entity {
    private String orderName;
    private double carSpeed;
    private double carCapacity;
    private TripStatusEnum status;

    public Order() {
    }

    public Order(int id, String orderName, double carSpeed, double carCapacity, TripStatusEnum status) {
        super(id);
        this.orderName = orderName;
        this.carSpeed = carSpeed;
        this.carCapacity = carCapacity;
        this.status = status;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(double carSpeed) {
        this.carSpeed = carSpeed;
    }

    public double getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(double carCapacity) {
        this.carCapacity = carCapacity;
    }

    public TripStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TripStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order #[" + super.getId() +
                "] orderName: '" + orderName + '\'' +
                ", carSpeed: " + carSpeed +
                ", carCapacity: " + carCapacity +
                ", status: " + status.toString().toLowerCase() +
                ';';
    }
}
