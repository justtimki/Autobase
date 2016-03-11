package by.gstu.autobase.model;

import java.io.Serializable;

/**
 * Created by Alexandr Kolymago on 05.11.2015.
 */
public class Car extends Entity implements Serializable {

    private String carName;
    private float speed;
    private boolean isHealthy = true;
    private float capacity;

    public Car() {
    }

    public Car(int id, String carName, float speed, boolean isHealthy, float capacity) {
        super(id);
        this.carName = carName;
        this.speed = speed;
        this.isHealthy = isHealthy;
        this.capacity = capacity;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Car #" + getId() + "[" + carName + "] Speed - " + speed + " Healthy - " + isHealthy + " Capacity " + capacity + "\n";
    }
}
