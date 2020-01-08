package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private int gridHeight;
    private int gridWidth;
    private int[][] gridMap;
    private Vehicle[][] vehicleGridMap;
    private ArrayList<Road> roadList;
    private ArrayList<Road> spawnPoints;

    public Grid() {
        this.roadList = new ArrayList<>();
        this.spawnPoints = new ArrayList<>();
    }

    public void setGrid(int gridHeight, int gridWidth) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    public void createGrid() {
        this.gridMap = new int[gridHeight][gridWidth];
        this.vehicleGridMap = new Vehicle[gridHeight][gridWidth];
    }

    public void createVehicle() {
        // Create vehicle only at spawn points
        Random rand = new Random();
        int chosenLocation = rand.nextInt(spawnPoints.size());
        System.out.println(chosenLocation);
        Road chosenRoad = spawnPoints.get(chosenLocation);
        chosenRoad.setVehicle(new Vehicle("Car",1),chosenRoad.getSpawnLocation());

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
            } else if (direction.equals("vertical")){
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1-i][startY-1] = roadArray[0][i];
                    vehicleGridMap[startX-1-i][startY] = roadArray[1][i];
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
        System.out.print("road1: " + roadList.get(0).getTrafficLightList().get(0).getTrafficLightColour() + ", road2: " + roadList.get(1).getTrafficLightList().get(0).getTrafficLightColour() + ", ");
        System.out.print("road3: " + roadList.get(2).getTrafficLightList().get(0).getTrafficLightColour() + ", road4: " + roadList.get(3).getTrafficLightList().get(0).getTrafficLightColour());
        for (Road road: roadList) {
            road.moveVehicleUp();
            road.moveVehicleDown();
        }
    }
}
