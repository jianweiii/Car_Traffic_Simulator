package com.company;

import java.util.Random;

public class Road {
    private Vehicle vehicle;
    private Road nextRoad = null;
    private Vehicle[][] roadArray;
    private int roadLength;
    private TrafficLight trafficLight;

    public Road() {
        // Initiates number of road segments
        this.roadLength = randomRoadLength();
        this.trafficLight = new TrafficLight();
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
                    trafficLight.trafficOperator();
                    if (trafficLight.getTrafficLightColour().equals("Red")) {

                    } else if (trafficLight.getTrafficLightColour().equals("Green")){
                        // Reset vehicle position to 0
                        roadArray[0][roadArray[0].length - 1].setPosition(0);
                        // Set next road vehicle
                        nextRoad.setVehicle(roadArray[0][roadArray[0].length - 1]);
                        // Destroy car in current road
                        roadArray[0][roadArray[0].length - 1] = null;
                    }



                } else {
                    // No next road, destroy car
                    roadArray[0][roadArray[0].length - 1] = null;
                }

            }

        } catch (Exception e) {
        }
        // If car is present in last segment of road, dont replace it
        if (roadArray[0][roadArray[0].length-1] != null) {
            for (int i=(roadArray[0].length-2);i>0;i--) {
                roadArray[0][i] = roadArray[0][i-1];
                // if vehicle is in current position, increment it's position by 1
                if (roadArray[0][i] != null) {
                    roadArray[0][i].incPosition();
                }
                roadArray[0][i-1] = null;
            }
        } else {
            for (int i=(roadArray[0].length-1);i>0;i--) {
                // If car is present in last segment of road, dont replace it

                roadArray[0][i] = roadArray[0][i-1];
                // if vehicle is in current position, increment it's position by 1
                if (roadArray[0][i] != null) {
                    roadArray[0][i].incPosition();
                }

                roadArray[0][i-1] = null;
            }
        }

    }
}
