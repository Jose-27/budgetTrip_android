package com.example.joe.budgettrip.model;

/**
 * Created by joe on 1/7/17.
 */

public class flights {

    private String origin;
    private String destination;
    private  String mileage;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
