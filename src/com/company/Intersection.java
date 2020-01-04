package com.company;

import java.util.ArrayList;

public class Intersection extends Road {
    private Vehicle vehicle;
    private Road previousRoad = null;
    private Road nextNorthRoad = null;
    private Road nextSouthRoad = null;
    private Road nextEastRoad = null;
    private Road nextWestRoad = null;
    private ArrayList<Road> roadOption = new ArrayList<>();

    public Intersection(int roadLength, int startX, int startY, String direction, Boolean spawnPoint, TrafficLight trafficLight) {
        super(roadLength, startX, startY, direction, spawnPoint, trafficLight);
    }


    public Road getPreviousRoad() {
        return previousRoad;
    }

    public void setPreviousRoad(Road previousRoad) {
        this.previousRoad = previousRoad;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle, String position) {
        super.setVehicle(vehicle, position);
        this.vehicle = vehicle;
    }

    public void setNorthRoad(Road nextRoad) {
        this.nextNorthRoad = nextRoad;
        if (previousRoad != nextRoad && nextRoad != null) {
            roadOption.add(nextRoad);
        }
    }

    public void setSouthRoad(Road nextRoad) {
        this.nextSouthRoad = nextRoad;
        if (previousRoad != nextRoad && nextRoad != null) {
            roadOption.add(nextRoad);
        }
    }

    public void setEastRoad(Road nextRoad) {
        this.nextEastRoad = nextRoad;
        if (previousRoad != nextRoad && nextRoad != null) {
            roadOption.add(nextRoad);
        }
    }

    public void setWestRoad(Road nextRoad) {
        this.nextWestRoad = nextRoad;
        if (previousRoad != nextRoad && nextRoad != null) {
            roadOption.add(nextRoad);
        }
    }

//    @Override
//    public void setNextRoad(Road nextRoad) {
//        super.setNextRoad(nextRoad);
//    }

    @Override
    public void moveVehicleUp() {
        super.moveVehicleUp();
        if (vehicle != null && vehicle.getPosition() == 0) {
            vehicle.incPosition();
        } else if (vehicle != null && vehicle.getPosition() != 0) {
            // Reset vehicle position to 0
            vehicle.setPosition(1);
            // Let vehicle decide which direction he wish to go
            Road roadChoice = vehicle.selectNextRoad(roadOption);
            // Set next road vehicle
            if (roadChoice == nextNorthRoad) {
                roadChoice.setVehicle(vehicle,"start");
            } else if (roadChoice == nextEastRoad) {
                roadChoice.setVehicle(vehicle,"start");
            } else if (roadChoice == nextSouthRoad) {
                roadChoice.setVehicle(vehicle,"end");
            } else if (roadChoice == nextWestRoad) {
                roadChoice.setVehicle(vehicle,"end");
            }
            // Destroy car in current road
            vehicle = null;
        }

    }

}
