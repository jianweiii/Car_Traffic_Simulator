package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel {
    final private static int indvGridSize = 20;

    private int gridHeight;
    private int gridWidth;
    private int[][] gridMap;
    private Vehicle[][] vehicleGridMap;
    private ArrayList<Road> roadList;
    private ArrayList<Road> spawnPoints;

    public Grid(int gridHeight, int gridWidth) {
        this.roadList = new ArrayList<>();
        this.spawnPoints = new ArrayList<>();
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        this.gridMap = new int[gridHeight][gridWidth];
        this.vehicleGridMap = new Vehicle[gridHeight][gridWidth];
    }

    public void createVehicle() {
        // Create vehicle only at spawn points
        Random rand = new Random();
        int chosenLocation = rand.nextInt(spawnPoints.size());
        Road chosenRoad = spawnPoints.get(chosenLocation);
        chosenRoad.setVehicle(new Vehicle("Car",1),chosenRoad.getSpawnLocation());

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
                if (vehicleGridMap[row][col] != null) {
                    // car
                    graphicsSettings.setColor(Color.BLACK);
                } else if (gridMap[row][col] == 0) {
                    // non-road
                    graphicsSettings.setColor(Color.GREEN);
                } else if (gridMap[row][col] == 99) {
                    // Green traffic light
                    Color trafficGreen = new Color(41, 140, 38);
                    graphicsSettings.setColor(trafficGreen);
                } else if (gridMap[row][col] == 95) {
                    // Red traffic light
//                    Color trafficGreen = new Color(41, 140, 38);
                    graphicsSettings.setColor(Color.RED);
                } else if (gridMap[row][col] == 11) {
                    // horizontal right
                    graphicsSettings.setColor(Color.GRAY);
//                    System.out.print("|->-|");
                } else if (gridMap[row][col] == 12) {
                    // horizontal left
//                    System.out.print("|-<-|");
                    graphicsSettings.setColor(Color.GRAY);
                } else if (gridMap[row][col] == 21) {
                    // vertical up
//                    System.out.print("| ^ |");
                    graphicsSettings.setColor(Color.GRAY);
                } else if (gridMap[row][col] == 22) {
                    // vertical down
//                    System.out.print("| v |");
                    graphicsSettings.setColor(Color.GRAY);
                } else {
                    // intersection
                    graphicsSettings.setColor(Color.GRAY);
//                    System.out.print("|-X-|");
                }
                int x = (col*panelWidth) / gridMap.length;
                int y = (row*panelHeight) / gridMap[row].length;

                // TODO: CHAGNE BACK
//                graphicsSettings.fillRect(x,y,xWidth+1,yHeight+1);
                graphicsSettings.fillRect(x-1,y-1,xWidth-1,yHeight-1);
            }

        }

        moveVehicles();

    }

    public void displayGrid() {
        for (int[] roads : gridMap) {
            for (int road: roads) {
                if (road == 0) {
                    System.out.print("|   |");
                } else if (road == 11) {
                    System.out.print("|---|");
                } else if (road == 12){
                    System.out.print("|-|-|");
                } else {
                    System.out.print("|-X-|");
                }

            }
            System.out.println();

        }
    }

    public void displayVehicleGrid() {
        roadList.get(0).displayRoad();
        System.out.print("-X-");
        roadList.get(1).displayRoad();
        System.out.println();
        for (Vehicle[] vehicleRow : vehicleGridMap) {
            for (Vehicle vehicle: vehicleRow) {
                if (vehicle == null) {
                    System.out.print("|   |");
                } else {
                    System.out.print("|-1-|");
                }

            }
            System.out.println();

        }
    }

    public void displayAll() {
        for (int i=0;i<gridMap.length;i++) {
            for (int j=0;j<gridMap[i].length;j++) {
                if (vehicleGridMap[i][j] != null) {
                    System.out.print("| C |");
                } else if (gridMap[i][j] == 0) {
                    System.out.print("|   |");
                } else if (gridMap[i][j] == 11) {
                    System.out.print("|->-|");
                } else if (gridMap[i][j] == 12) {
                    System.out.print("|-<-|");
                } else if (gridMap[i][j] == 21) {
                    System.out.print("| ^ |");
                } else if (gridMap[i][j] == 22) {
                    System.out.print("| v |");
                } else {
                    System.out.print("|-X-|");
                }
            }
            System.out.println();
        }
    }

//    public void addTrafficLight() {
//        roadList.get(0).setTrafficLight(new TrafficLight(roadList.get(0).getRoadLength()-1));
//    }


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
//        else if (direction.equals("straight")) {
//            //Straight intersection
//            gridMap[startX-1][startY-1] = 2;
//        } else if (direction.equals("3-way")) {
//            //3-way intersection
//            gridMap[startX-1][startY-1] = 3;
//        } else if (direction.equals("4-way")) {
//            //4-way intersection
//            gridMap[startX-1][startY-1] = 4;
//        }
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
        for (Road road: roadList) {
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
    }

    public void moveVehicles() {
//        System.out.print("road1: " + roadList.get(0).getTrafficLightList().get(0).getTrafficLightColour() + ", road2: " + roadList.get(1).getTrafficLightList().get(0).getTrafficLightColour() + ", ");
//        System.out.print("road3: " + roadList.get(2).getTrafficLightList().get(0).getTrafficLightColour() + ", road4: " + roadList.get(3).getTrafficLightList().get(0).getTrafficLightColour());
        for (Road road: roadList) {
            road.moveVehicleUp();
            road.moveVehicleDown();
        }
    }
}
