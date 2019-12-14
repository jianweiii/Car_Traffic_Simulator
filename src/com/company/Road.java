package com.company;

import java.util.Random;

public class Road {
    private Vehicle vehicle;
    private Road nextRoad = null;
    private Vehicle[][] roadArray;
    private int roadLength;

    public Road() {
        // Initiates number of road segments
        this.roadLength = randomRoadLength();
        this.roadArray = new Vehicle[1][roadLength];
    }

    public Vehicle getVehicle() {

        Vehicle rVehicle = vehicle;
        vehicle = null;
        return rVehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.roadArray[0][0] = vehicle;
    }

    /*
    Set adjacent road
     */
    public void setNextRoad(Road nextRoad) {
        this.nextRoad = nextRoad;
    }

    public int getRoadLength() {
        return roadLength;
    }

    /*
        Display road conditions
         */
    public void displayRoad() {
        // Display current road
        for (Vehicle[] vehicleArray: roadArray) {
            for (Vehicle vehicle: vehicleArray) {
                if (vehicle != null) {
                    System.out.print(vehicle.getType() + " | ");
                } else {
                    System.out.print(" _  | ");
                }

            }
        }

//        // Display next road
//        System.out.print("_junction_");
//        for (Vehicle[] vehicleArray: nextRoad.roadArray) {
//            for (Vehicle vehicle: vehicleArray) {
//                if (vehicle != null) {
//                    System.out.print(vehicle.getType() + " | ");
//                } else {
//                    System.out.print("_ | ");
//                }
//
//            }
//        }
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

        try {
            // When vehicle reaches end of road segment
            if (roadArray[0][roadArray[0].length - 1].getPosition() == (roadArray[0].length - 1)) {
                if (nextRoad != null) {
                    // TODO: Check traffic light

                    // Reset vehicle position to 0
                    roadArray[0][roadArray[0].length - 1].setPosition(0);
                    // Set next road vehicle
                    nextRoad.setVehicle(roadArray[0][roadArray[0].length - 1]);
                    // Destroy car in current road
                    roadArray[0][roadArray[0].length - 1] = null;

//                String type = nextRoad.getVehicle().getType();
//                System.out.println("NEXT" + type);
//                vehicle = null;
                } else {
                    // No next road, destroy car
                    roadArray[0][roadArray[0].length - 1] = null;
                }

            }

        } catch (Exception e) {
        }
        for (int i=(this.roadArray[0].length-1);i>0;i--) {
            this.roadArray[0][i] = this.roadArray[0][i-1];
            if (this.roadArray[0][i] != null) {
                this.roadArray[0][i].incPosition();
            }

            this.roadArray[0][i-1] = null;
        }
    }
}
