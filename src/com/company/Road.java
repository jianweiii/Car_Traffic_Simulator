package com.company;

import java.util.ArrayList;

public class Road {
    private int xCoord, xRTpos;
    private int yCoord, yRTpos;
    private String direction;
    private Vehicle vehicle;
    private Road curRoad = null;
    private Road nextStartRoad = null;
    private Road nextEndRoad = null;
    private Vehicle[][] roadArray;
    private int roadLength;
    private Boolean spawnPoint;
    private String spawnLocation;
    private ArrayList<TrafficLight> trafficLightList = new ArrayList<>();
    private SimulatorBoard simulatorBoard;

    public Road(int roadLength, int startX, int startY, String direction, Boolean spawnPoint,SimulatorBoard simulatorBoard) {
        // Initiates number of road segments
        this.xCoord = startX;
        this.yCoord = startY;
        this.direction = direction;
        this.roadLength = roadLength;
        this.roadArray = new Vehicle[2][roadLength];
        this.spawnPoint = spawnPoint;
        this.curRoad = this;
        this.simulatorBoard = simulatorBoard;
    }

    public Vehicle getVehicle() {
        Vehicle rVehicle = vehicle;
        vehicle = null;
        return rVehicle;
    }

    public void setVehicle(Vehicle vehicle, String position) {
        try {
            if (position.equals("start")) {
                this.roadArray[0][0] = vehicle;
            } else if (position.equals("end")) {
                this.roadArray[1][roadArray[1].length-1] = vehicle;
            }
        } catch(NullPointerException e) {
        }
    }

    /*
        Set adjacent road
         */

    public void setNextRoad(Road nextStartRoad, Road nextEndRoad) {
        this.nextStartRoad = nextStartRoad;
        this.nextEndRoad = nextEndRoad;
    }

    public void setNextStartRoad(Road nextStartRoad) {
        this.nextStartRoad = nextStartRoad;
    }

    public void setNextEndRoad(Road nextEndRoad) {
        this.nextEndRoad = nextEndRoad;
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

    public Boolean getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnLocation(String spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public String getSpawnLocation() {
        return spawnLocation;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLightList.add(trafficLight);
    }

    public ArrayList<TrafficLight> getTrafficLightList() {
        return trafficLightList;
    }

    public Road getNextStartRoad() {
        return nextStartRoad;
    }

    public Road getNextEndRoad() {
        return nextEndRoad;
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
            System.out.println();
        }
    }
    /*
    Move Vehicle 1 slot up
     */
    public void moveVehicleUp() {
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
                    if (nextStartRoad != null) {
                        // Check if there is traffic light
                        if (!trafficLightList.isEmpty()) {
                            // Check for correct traffic light
                            for (TrafficLight trafficLight: trafficLightList) {
                                if (trafficLight.getPos() == i) {
                                    // Check traffic light condition
                                    if (trafficLight.getTrafficLightColour().equals("Red")) {
                                        //Do nothing and wait
                                    } else if (trafficLight.getTrafficLightColour().equals("Green")){
                                        // If nextStartRoad is an Intersection class, make it an Intersection class
                                        if (nextStartRoad.getClass() == Intersection.class) {
                                            Intersection nextIntersection = (Intersection) nextStartRoad;
                                            Vehicle[] intersectionVehicles = nextIntersection.getIntersectionGrid();
                                            // Check with respect to which position of the
                                            // intersection, the current road is connected to
                                            if (nextIntersection.getNextWestRoad() == this) {
                                                // check if next intersection point is occupied
                                                // and also check if any in-coming vehicle onto next intersection point
                                                if (intersectionVehicles[0] == null && intersectionVehicles[3] == null) {
                                                    // Reset vehicle position to 0
                                                    roadArray[0][roadArray[0].length-1].setPosition(0);
                                                    // Set next road vehicle
                                                    nextStartRoad.setVehicle(roadArray[0][roadArray[0].length-1], "0");
                                                    // Destroy car in current road
                                                    roadArray[0][roadArray[0].length - 1] = null;
                                                } else {
                                                    // Do nothing
                                                }
                                            } else if (nextIntersection.getNextSouthRoad() == this) {
                                                // check if next intersection point is occupied
                                                // and also check if any in-coming vehicle onto next intersection point
                                                if (intersectionVehicles[3] == null && intersectionVehicles[2] == null) {
                                                    // Reset vehicle position to 0
                                                    roadArray[0][roadArray[0].length-1].setPosition(0);
                                                    // Set next road vehicle
                                                    nextStartRoad.setVehicle(roadArray[0][roadArray[0].length-1], "3");
                                                    // Destroy car in current road
                                                    roadArray[0][roadArray[0].length - 1] = null;
                                                } else {
                                                    // Do nothing
                                                }
                                            } else {
                                                // Reset vehicle position to 0
                                                roadArray[0][roadArray[0].length-1].setPosition(0);
                                                // Set next road vehicle
                                                nextStartRoad.setVehicle(roadArray[0][roadArray[0].length-1], "start");
                                                // Destroy car in current road
                                                roadArray[0][roadArray[0].length - 1] = null;
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    // check if theres a vehicle in next road
                                    if (nextStartRoad.getRoadArray()[0][0] != null) {
                                        // Do nothing
                                    } else {
                                        // For straight road, no traffic lights
                                        // Reset vehicle position to 0
                                        roadArray[0][roadArray[0].length-1].setPosition(0);
                                        // Set next road vehicle
                                        nextStartRoad.setVehicle(roadArray[0][roadArray[0].length-1], "start");
                                        // Destroy car in current road
                                        roadArray[0][roadArray[0].length - 1] = null;
                                    }
                                }
                            }
                        } else
                            // No traffic light
                            {
                            // check if theres a vehicle in next road
                            if (nextStartRoad.getRoadArray()[0][0] != null) {
                                // Do nothing
                            } else {
                                // For straight road, no traffic lights
                                // Reset vehicle position to 0
                                roadArray[0][roadArray[0].length-1].setPosition(0);
                                // Set next road vehicle
                                nextStartRoad.setVehicle(roadArray[0][roadArray[0].length-1], "start");
                                // Destroy car in current road
                                roadArray[0][roadArray[0].length - 1] = null;

                            }

                        }
                    } else {
                        roadArray[0][roadArray[0].length - 1] = null;
                        simulatorBoard.totalVehicles -= 1;
                        simulatorBoard.updateVehicleStatus();
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

    /*
    Move Vehicle 1 slot down
     */
    public void moveVehicleDown() {
        try {
            getTrafficLightList().get(0).trafficOperator();
        } catch (Throwable e) {

        }

        // move every slot up by 1 location
        for (int i=0;i<(roadArray[1].length-1);i++) {
            // Do not allow taking over of vehicles
            if (roadArray[1][i] != null) {
                // When vehicle reaches end of road segment
                if (roadArray[1][0] != null) {
                    // Check for nextRoad
                    if (nextEndRoad != null) {
                        // Check if there is traffic light
                        if (!trafficLightList.isEmpty()) {
                            // Check for correct traffic light
                            for (TrafficLight trafficLight: trafficLightList) {
                                if (trafficLight.getPos() == i) {
                                    // Check traffic light condition
                                    if (trafficLight.getTrafficLightColour().equals("Red")) {
                                        //Do nothing and wait
                                    } else if (trafficLight.getTrafficLightColour().equals("Green")){
                                        // If nextEndRoad is an Intersection class, make it an Intersection class
                                        if (nextEndRoad.getClass() == Intersection.class) {
                                            Intersection nextIntersection = (Intersection) nextEndRoad;
                                            Vehicle[] intersectionVehicles = nextIntersection.getIntersectionGrid();
                                            // Check with respect to which position of the
                                            // intersection, the current road is connected to
                                            if (nextIntersection.getNextNorthRoad() == this) {
                                                // check if next intersection point is occupied
                                                // and also check if any in-coming vehicle onto next intersection point
                                                if (intersectionVehicles[1] == null && intersectionVehicles[0] == null) {
                                                    // Reset vehicle position to 0
                                                    roadArray[1][0].setPosition(0);
                                                    // Set next road vehicle
                                                    nextEndRoad.setVehicle(roadArray[1][0],"1");
                                                    // Destroy car in current road
                                                    roadArray[1][0] = null;
                                                } else {
                                                    // Do nothing
                                                }
                                            } else if (nextIntersection.getNextEastRoad() == this) {
                                                // check if next intersection point is occupied
                                                // and also check if any in-coming vehicle onto next intersection point
                                                if (intersectionVehicles[2] == null && intersectionVehicles[1] == null) {
                                                    // Reset vehicle position to 0
                                                    roadArray[1][0].setPosition(0);
                                                    // Set next road vehicle
                                                    nextEndRoad.setVehicle(roadArray[1][0],"2");
                                                    // Destroy car in current road
                                                    roadArray[1][0] = null;
                                                } else {
                                                    // Do nothing
                                                }
                                            } else {
                                                // Reset vehicle position to 0
                                                roadArray[1][0].setPosition(0);
                                                // Set next road vehicle
                                                nextEndRoad.setVehicle(roadArray[1][0],"end");
                                                // Destroy car in current road
                                                roadArray[1][0] = null;
                                            }
                                        }
                                    }
                                }
                                else
                                    // No traffic light
                                    {
                                    // check if theres a vehicle in next road
                                    if (nextEndRoad.getRoadArray()[1][nextEndRoad.getRoadLength()-1] != null) {
                                        // Do nothing
                                    } else {
                                        // Reset vehicle position to 0
                                        roadArray[1][0].setPosition(0);
                                        // Set next road vehicle
                                        nextEndRoad.setVehicle(roadArray[1][0],"end");
                                        // Destroy car in current road
                                        roadArray[1][0] = null;
                                    }
                                }
                            }
                        } else // for straight roads
                        {
                            // check if theres a vehicle in next road
                            if (nextEndRoad.getRoadArray()[1][nextEndRoad.getRoadLength()-1] != null) {
                                // Do nothing
                            } else {
                                // Reset vehicle position to 0
                                roadArray[1][0].setPosition(0);
                                // Set next road vehicle
                                nextEndRoad.setVehicle(roadArray[1][0],"end");
                                // Destroy car in current road
                                roadArray[1][0] = null;
                            }

                        }

                    } else {
                        // Destroy car if no further roads
                        roadArray[1][0] = null;
                        simulatorBoard.totalVehicles -= 1;
                        simulatorBoard.updateVehicleStatus();
                    }
                }
            } else {
                try {
//                    roadArray[1][i] = roadArray[1][i+1];
//                    // if vehicle is in current position, increment it's position by 1
//                    if (roadArray[1][i+1] != null) {
//                        roadArray[1][i].incPosition();
//                    }
//                    roadArray[1][i+1] = null;
                    // Do not move vehicle that has just into road (prevents double moving)
                    if (roadArray[1][i+1].getPosition() != 0) {
                        roadArray[1][i] = roadArray[1][i+1];
                        // if vehicle is in current position, increment it's position by 1
                        if (roadArray[1][i+1] != null) {
                            roadArray[1][i].incPosition();
                        }
                        roadArray[1][i+1] = null;
                    } else {
                        // vehicle pos reflects real position on road segment
                        // eg. 1 = start of road
                        // eg. 0 = just moved over from another road, to prevent double moving
                        if (roadArray[1][roadArray[1].length-1] != null && roadArray[1][roadArray[1].length-1].getPosition() == 0) {
                            roadArray[1][roadArray[1].length-1].incPosition();
                        }
                    }
                } catch (Exception e) {
//                    System.out.println(e);
                }
            }
        }
    }

    public int getxRTpos() {
        if (direction.equals("horizontal")) {
            xRTpos = xCoord;
        } else if (direction.equals("vertical")) {
            xRTpos = xCoord-roadLength+1;
        }
        return xRTpos;
    }

    public int getyRTpos() {
        if (direction.equals("horizontal")) {
            yRTpos = yCoord+roadLength;
        } else if (direction.equals("vertical")) {
            yRTpos = yCoord;
        }
        return yRTpos;
    }
}
