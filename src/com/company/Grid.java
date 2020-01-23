package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel {
    private int gridHeight;
    private int gridWidth;
    private int[][] gridMap;
    private Vehicle[][] vehicleGridMap;
    private ArrayList<Road> roadList;
    private ArrayList<Road> spawnPoints;
    private SimulatorBoard simulatorBoard;

    public Grid(int gridHeight, int gridWidth, SimulatorBoard simulatorBoard) {
        this.roadList = new ArrayList<>();
        this.spawnPoints = new ArrayList<>();
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        this.gridMap = new int[gridHeight][gridWidth];
        this.vehicleGridMap = new Vehicle[gridHeight][gridWidth];
        this.simulatorBoard = simulatorBoard;
    }

    public void createVehicle() {
        // Create vehicle only at spawn points
        Random rand = new Random();
        int chosenLocation = rand.nextInt(spawnPoints.size());
        Road chosenRoad = spawnPoints.get(chosenLocation);
        String[] vehicleType = {"Car", "Bus", "Motorbike"};
        Random randVeh = new Random();
        int randomVehChoice = randVeh.nextInt(vehicleType.length);
        chosenRoad.setVehicle(new Vehicle(vehicleType[randomVehChoice],1),chosenRoad.getSpawnLocation());

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;
        // get width of JPanel
        int panelWidth = getWidth();
        // get height of JPanel
        int panelHeight = getHeight();
        // get length of each array box width
        int xWidth = panelWidth / gridWidth;
        // get length of each array box height
        int yHeight = panelHeight / gridHeight;
        // row
        for (int row=0;row<gridMap.length;row++) {
            // column
            for (int col=0;col<gridMap[row].length;col++) {

                int x = (col*panelWidth) / gridMap.length;
                int y = (row*panelHeight) / gridMap[row].length;

                if (gridMap[row][col] == 0) {
                    // non-road
                    graphicsSettings.setColor(new Color(0, 230, 77));
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                } else if (gridMap[row][col] == 99) {
                    // Green traffic light
                    Color trafficGreen = new Color(41, 140, 38);
                    graphicsSettings.setColor(trafficGreen);
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                } else if (gridMap[row][col] == 95) {
                    // Red traffic light
//                    Color trafficGreen = new Color(41, 140, 38);
                    graphicsSettings.setColor(Color.RED);
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                } else if (gridMap[row][col] == 11) {
                    // horizontal right
                    graphicsSettings.setColor(Color.GRAY);
//                    System.out.print("|->-|");
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                    graphicsSettings.setColor(Color.BLACK);
                    graphicsSettings.fillRect(x,y,xWidth+1,1);
                    graphicsSettings.setColor(Color.WHITE);
                    graphicsSettings.fillRect(x,y+yHeight,xWidth+1,1);
                } else if (gridMap[row][col] == 12) {
                    // horizontal left
//                    System.out.print("|-<-|");
                    graphicsSettings.setColor(Color.GRAY);
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                    graphicsSettings.setColor(Color.BLACK);
                    graphicsSettings.fillRect(x,y+yHeight,xWidth+1,1);
                } else if (gridMap[row][col] == 21) {
                    // vertical up
//                    System.out.print("| ^ |");
                    graphicsSettings.setColor(Color.GRAY);
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                    graphicsSettings.setColor(Color.BLACK);
                    graphicsSettings.fillRect(x,y,1,yHeight+1);
                    graphicsSettings.setColor(Color.WHITE);
                    graphicsSettings.fillRect(x+xWidth-1,y,1,yHeight+1);
                } else if (gridMap[row][col] == 22) {
                    // vertical down
//                    System.out.print("| v |");
                    graphicsSettings.setColor(Color.GRAY);
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                    graphicsSettings.setColor(Color.BLACK);
                    graphicsSettings.fillRect(x+xWidth-1,y,1,yHeight+1);
                } else {
                    // intersection
                    graphicsSettings.setColor(Color.GRAY);
//                    System.out.print("|-X-|");
                    graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                }


                

                if (vehicleGridMap[row][col] != null) {

                    switch (vehicleGridMap[row][col].getType()) {
                        // If Car
                        case "Car":
                            graphicsSettings.setColor(new Color(230, 230, 0));
                            graphicsSettings.fillRect(x, y, xWidth + 1, yHeight + 1);
                            break;
                        case "Bus":
                            if (gridMap[row][col] == 11 || gridMap[row][col] == 12) {
                                // horizontal right
                                graphicsSettings.setColor(new Color(0, 64, 255));
                                graphicsSettings.fillRect(x-10, y, xWidth + 1+10, yHeight + 1);
//                          System.out.print("|->-|");
                            } else if (gridMap[row][col] == 21 || gridMap[row][col] == 22) {
                                // horizontal right
                                graphicsSettings.setColor(new Color(0, 64, 255));
                                graphicsSettings.fillRect(x, y-10, xWidth + 1, yHeight + 1+10);
//                          System.out.print("|->-|");
                            } else {
                                graphicsSettings.setColor(new Color(0, 64, 255));
                                graphicsSettings.fillRect(x, y, xWidth + 1, yHeight + 1);
                            }
                            break;
                        case "Motorbike":
                            if (gridMap[row][col] == 11 || gridMap[row][col] == 12) {
                                // horizontal right
                                graphicsSettings.setColor(new Color(230, 92, 0));
                                graphicsSettings.fillRect(x+5, y, xWidth + 1-5, yHeight + 1);
//                          System.out.print("|->-|");
                            } else if (gridMap[row][col] == 21 || gridMap[row][col] == 22) {
                                // horizontal right
                                graphicsSettings.setColor(new Color(230, 92, 0));
                                graphicsSettings.fillRect(x, y+5, xWidth + 1, yHeight + 1-5);
//                          System.out.print("|->-|");
                            }  else {
                                graphicsSettings.setColor(new Color(230, 92, 0));
                                graphicsSettings.fillRect(x, y, xWidth + 1, yHeight + 1);
                            }
                            break;
                        default:
                            graphicsSettings.setColor(Color.BLACK);
                            graphicsSettings.fillRect(x, y, xWidth + 1, yHeight + 1);
                            break;
                    }

                }
            }

        }

        moveVehicles();
    }


    public void addRoad(Road road) {
        // Adding roads to roadList
        roadList.add(road);
        // If road is a spawn point, add it to spawnPoints arraylist
        if (road.getSpawnPoint()) {
            spawnPoints.add(road);
        }

        int startX = road.getxCoord();
        int startY = road.getyCoord();
        String direction = road.getDirection();
        int roadSegments = road.getRoadLength();
        if (direction.equals("horizontal")) {
            //Horizontal road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1][startY-1+i] = 11;
                gridMap[startX][startY-1+i] = 12;
            }
        } else if (direction.equals("vertical")) {
            //Vertical road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1-i][startY-1] = 21;
                gridMap[startX-1-i][startY] = 22;
            }
        } else {
            for (int i=0;i<2;i++) {
                gridMap[startX-1][startY-1+i] = 3;
                gridMap[startX][startY-1+i] = 3;
            }
        }
    }

    public void setNextRoad(Road curRoad, Road roadStart, Road roadEnd) {
        curRoad.setNextRoad(roadStart,roadEnd);
    }

    public void setIntersectionNextRoad(Intersection intersection1, Road roadNorth, Road roadSouth, Road roadEast, Road roadWest) {
        intersection1.setNorthRoad(roadNorth);
        intersection1.setSouthRoad(roadSouth);
        intersection1.setEastRoad(roadEast);
        intersection1.setWestRoad(roadWest);
    }


    /*
    Update positions on where vehicles are on the road
    Update traffic light colours
     */
    public void updateMap() {
        simulatorBoard.totalTrafficLights = 0;
        for (Road road: roadList) {
            // Get total number of traffic lights
            try {
                if (road.getTrafficLightList().get(0) != null) {
                    simulatorBoard.totalTrafficLights += 1;
                }
            } catch (Exception e) {
                // null pointers/ index out of bounds for those roads without traffic lights
            }


            int startX = road.getxCoord();
            int startY = road.getyCoord();
            String direction = road.getDirection();
            int roadSegments = road.getRoadLength();
            Vehicle[][] roadArray = road.getRoadArray();
            if (direction.equals("horizontal")) {
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1][startY-1+i] = roadArray[0][i];
                    vehicleGridMap[startX][startY-1+i] = roadArray[1][i];
                }
                // Update Traffic Light
                if (road.getTrafficLightList() != null) {
                    for (TrafficLight trafficLight: road.getTrafficLightList()) {
                        if (trafficLight.getPrintPos().equals("start")) {
                            if (trafficLight.getTrafficLightColour().equals("Green")) {
                                gridMap[startX-2][startY-2+roadSegments] = 99;
                            } else {
                                // red light
                                gridMap[startX-2][startY-2+roadSegments] = 95;
                            }
                        } else {
                            // end pos
                            if (trafficLight.getTrafficLightColour().equals("Green")) {
                                gridMap[startX+1][startY-1] = 99;
                            } else {
                                // red light
                                gridMap[startX+1][startY-1] = 95;
                            }
                        }
                    }
                }
            } else if (direction.equals("vertical")){
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1-i][startY-1] = roadArray[0][i];
                    vehicleGridMap[startX-1-i][startY] = roadArray[1][i];
                }
                // Update Traffic Light
                if (road.getTrafficLightList() != null) {
                    for (TrafficLight trafficLight: road.getTrafficLightList()) {
                        if (trafficLight.getPrintPos().equals("start")) {
                            if (trafficLight.getTrafficLightColour().equals("Green")) {
                                gridMap[startX-roadSegments][startY-2] = 99;
                            } else {
                                // red light
                                gridMap[startX-roadSegments][startY-2] = 95;
                            }
                        } else {
                            // end pos
                            if (trafficLight.getTrafficLightColour().equals("Green")) {
                                gridMap[startX-1][startY+1] = 99;
                            } else {
                                // red light
                                gridMap[startX-1][startY+1] = 95;
                            }
                        }
                    }
                }
            }else {
                Intersection roadIntersection = (Intersection) road;
                Vehicle[] vehicleIntersection = roadIntersection.getIntersectionGrid();
                //  -------
                // | 0 | 1 |
                //  -------
                // | 3 | 2 |
                //  -------
                vehicleGridMap[startX-1][startY-1] = vehicleIntersection[0];
                vehicleGridMap[startX-1][startY] = vehicleIntersection[1];
                vehicleGridMap[startX][startY] = vehicleIntersection[2];
                vehicleGridMap[startX][startY-1] = vehicleIntersection[3];
            }
        }
        simulatorBoard.updateTrafficLightStatus();
    }

    private void moveVehicles() {
//        System.out.print("road1: " + roadList.get(0).getTrafficLightList().get(0).getTrafficLightColour() + ", road2: " + roadList.get(1).getTrafficLightList().get(0).getTrafficLightColour() + ", ");
//        System.out.print("road3: " + roadList.get(2).getTrafficLightList().get(0).getTrafficLightColour() + ", road4: " + roadList.get(3).getTrafficLightList().get(0).getTrafficLightColour());
        for (Road road: roadList) {
            road.moveVehicleUp();
            road.moveVehicleDown();
        }
    }
}
