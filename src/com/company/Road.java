package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Road {
    private int xCoord;
    private int yCoord;
    private String direction;
    private Vehicle vehicle;
    private Road nextRoad = null;
    private Vehicle[][] roadArray;
    private int roadLength;
    private ArrayList<TrafficLight> trafficLightList = new ArrayList<>();
//    private TrafficLight trafficLight;

    public Road(int roadLength, int startX, int startY, String direction, TrafficLight trafficLight) {
        // Initiates number of road segments
        this.xCoord = startX;
        this.yCoord = startY;
        this.direction = direction;
        this.roadLength = roadLength;
        this.roadArray = new Vehicle[1][roadLength];
//        this.trafficLight = mtrafficLight;
        this.trafficLightList.add(trafficLight);
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

    public Vehicle[][] getRoadArray() {
        return roadArray;
    }

    public int getRoadLength() {
        return roadLength;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getDirection() {
        return direction;
    }

    public Road getNextRoad() {
        return nextRoad;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLightList.add(trafficLight);
    }

    public ArrayList<TrafficLight> getTrafficLightList() {
        return trafficLightList;
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
    Move Vehicle 1 slot up
     */
    public void moveVehicle() {
        try {
            getTrafficLightList().get(0).trafficOperator();
        } catch (Throwable e) {

        }

        // move every slot up by 1 location
        for (int i=(roadArray[0].length-1);i>0;i--) {
            // Do not allow taking over of vehicles
            if (roadArray[0][i] != null) {
                // When vehicle reaches end of road segment
                if (roadArray[0][roadArray[0].length-1] != null) {
                    // Check for nextRoad
                    if (nextRoad != null) {
//                        // Reset vehicle position to 0
//                        roadArray[0][roadArray[0].length-1].setPosition(0);
//                        // Set next road vehicle
//                        nextRoad.setVehicle(roadArray[0][roadArray[0].length-1]);
//                        // Destroy car in current road
//                        roadArray[0][roadArray[0].length - 1] = null;
                        // Check traffic light condition
                        if (trafficLightList.get(0).getTrafficLightColour().equals("Red")) {
                            //Do nothing and wait
                        } else if (trafficLightList.get(0).getTrafficLightColour().equals("Green")){
                            // Reset vehicle position to 0
                            roadArray[0][roadArray[0].length-1].setPosition(0);
                            // Set next road vehicle
                            nextRoad.setVehicle(roadArray[0][roadArray[0].length-1]);
                            // Destroy car in current road
                            roadArray[0][roadArray[0].length - 1] = null;
                        }
                    } else {
                        roadArray[0][roadArray[0].length - 1] = null;
                    }
                }
            } else {
                try {
                    // Do not move vehicle that has just into road (prevents double moving)
                    if (roadArray[0][i-1].getPosition() != 0) {
                        roadArray[0][i] = roadArray[0][i-1];
                        // if vehicle is in current position, increment it's position by 1
                        if (roadArray[0][i-1] != null) {
                            roadArray[0][i].incPosition();
                        }
                        roadArray[0][i-1] = null;
                    } else {
                        // vehicle pos reflects real position on road segment
                        // eg. 1 = start of road
                        // eg. 0 = just moved over from another road, to prevent double moving
                        if (roadArray[0][0] != null && roadArray[0][0].getPosition() == 0) {
                            roadArray[0][0].incPosition();
                        }
                    }
                } catch (Exception e) {
//                    System.out.println(e);
                }
            }
        }
    }
}
