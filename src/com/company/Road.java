package com.company;

import java.util.Random;

public class Road {
    private Vehicle vehicle;
    public Vehicle[][] roadArray;

    public Road() {
        // Initiates number of road segments
        roadArray = new Vehicle[1][randomRoadLength()];
    }

    public Vehicle getVehicle() {

        Vehicle rVehicle = vehicle;
        vehicle = null;
        return rVehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.roadArray[0][0] = vehicle;
    }

    public void displayRoad() {
        for (Vehicle[] vehicleArray: roadArray) {
            for (Vehicle vehicle: vehicleArray) {
                if (vehicle != null) {
                    System.out.print(vehicle.getType() + " | ");
                } else {
                    System.out.print("_ | ");
                }

            }
        }
    }

    /*
    Randoms the length of each road
     */
    private int randomRoadLength() {
        return (new Random().nextInt(15-6+1) + 6);
    }

    /*
    Move Vehicle 1 slot up
     */
    public void moveVehicle() {
        System.out.println();
        for (int i=(roadArray[0].length-1);i>0;i--) {
            roadArray[0][i] = roadArray[0][i-1];
            roadArray[0][i-1] = null;

        }
    }
}
