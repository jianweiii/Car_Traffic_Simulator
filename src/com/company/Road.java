package com.company;

public class Road {
    private Vehicle vehicle;
    private int[][] roadArray;

    public Road() {
        
    }

    public Vehicle getVehicle() {

        Vehicle rVehicle = vehicle;
        vehicle = null;
        return rVehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
