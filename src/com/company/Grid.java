package com.company;

import java.util.ArrayList;

public class Grid {
    private int gridHeight;
    private int gridWidth;
    private int[][] gridMap;
    private Vehicle[][] vehicleGridMap;
    private ArrayList<Road> roadList;

    public Grid() {
        this.roadList = new ArrayList<>();
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
        roadList.get(0).setVehicle(new Vehicle("Car",1));
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
        System.out.println("road1: " + roadList.get(0).getTrafficLightList().get(0).getTrafficLightColour() + ", road2: " + roadList.get(1).getTrafficLightList().get(0).getTrafficLightColour());
        for (int i=0;i<gridMap.length;i++) {
            for (int j=0;j<gridMap[i].length;j++) {
                if (vehicleGridMap[i][j] != null) {
                    System.out.print("| C |");
                } else if (gridMap[i][j] == 0) {
                    System.out.print("     ");
                } else if (gridMap[i][j] == 11) {
                    System.out.print("|---|");
                } else if (gridMap[i][j] == 12) {
                    System.out.print("| | |");
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
        roadList.add(road);
        int startX = road.getxCoord();
        int startY = road.getyCoord();
        String direction = road.getDirection();
        int roadSegments = road.getRoadLength();
        if (direction.equals("horizontal-left")) {
            //Horizontal road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1][startY-1-i] = 11;
            }
        } else if (direction.equals("horizontal-right")) {
            //Horizontal road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1][startY-1+i] = 11;
            }
        } else if (direction.equals("vertical-down")) {
            //Vertical road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1+i][startY-1] = 12;
            }
        } else if (direction.equals("vertical-up")) {
            //Vertical road
            for (int i=0;i<roadSegments;i++) {
                gridMap[startX-1-i][startY-1] = 12;
            }
        }else if (direction.equals("straight")) {
            //Straight intersection
            gridMap[startX-1][startY-1] = 2;
        } else if (direction.equals("3-way")) {
            //3-way intersection
            gridMap[startX-1][startY-1] = 3;
        } else if (direction.equals("4-way")) {
            //4-way intersection
            gridMap[startX-1][startY-1] = 4;
        }
    }

    public void setNextRoad(Road road1, Road road2) {
        road1.setNextRoad(road2);
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
            if (direction.equals("horizontal-right")) {
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1][startY-1+i] = roadArray[0][i];
                }
            } else if (direction.equals("horizontal-left")){
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1][startY-1-i] = roadArray[0][i];
                }
            } else if (direction.equals("vertical-up")){
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1-i][startY-1] = roadArray[0][i];
                }
            } else if (direction.equals("vertical-down")){
                for (int i=0;i<roadSegments;i++) {
                    vehicleGridMap[startX-1+i][startY-1] = roadArray[0][i];
                }
            }else {
                vehicleGridMap[startX-1][startY-1] = road.getVehicle();
            }



        }

    }

    public void moveVehicles() {
        for (Road road: roadList) {
            road.moveVehicle();
        }
    }
}
