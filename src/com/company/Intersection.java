package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Intersection extends Road {
    private Vehicle vehicle;
    private Road previousRoad = null;
    private Road nextNorthRoad = null;
    private Road nextSouthRoad = null;
    private Road nextEastRoad = null;
    private Road nextWestRoad = null;
    private Vehicle[] intersectionGrid;
    private ArrayList<Road> roadOption = new ArrayList<>();

    public Intersection(int roadLength, int startX, int startY, String direction, Boolean spawnPoint) {
        super(roadLength, startX, startY, direction, spawnPoint);
        this.intersectionGrid = new Vehicle[4];
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

    public Vehicle[] getIntersectionGrid() {
        return intersectionGrid;
    }

    @Override
    public void setVehicle(Vehicle vehicle, String position) {
        super.setVehicle(vehicle, position);
        // check position of car to append to which position of the array
        if (Integer.parseInt(position) == 0) {
            intersectionGrid[0] = vehicle;
        } else if (Integer.parseInt(position) == 1) {
            intersectionGrid[1] = vehicle;
        } else if (Integer.parseInt(position) == 2) {
            intersectionGrid[2] = vehicle;
        } else if (Integer.parseInt(position) == 3) {
            intersectionGrid[3] = vehicle;
        }
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

    public Road getNextNorthRoad() {
        return nextNorthRoad;
    }

    public Road getNextSouthRoad() {
        return nextSouthRoad;
    }

    public Road getNextEastRoad() {
        return nextEastRoad;
    }

    public Road getNextWestRoad() {
        return nextWestRoad;
    }
    //    @Override
//    public void setNextRoad(Road nextRoad) {
//        super.setNextRoad(nextRoad);
//    }

    @Override
    public void moveVehicleUp() {
        super.moveVehicleUp();

        // Before moving array, check if vehicle wants to leave intersection
        if (intersectionGrid[0] != null
                && intersectionGrid[0].getPosition() !=0
                && nextNorthRoad != null) {
            if (intersectionGrid[0].leaveRoad()) {
                nextNorthRoad.setVehicle(intersectionGrid[0],"start");
                intersectionGrid[0] = null;
            }
        }
        if (intersectionGrid[1] != null
                && intersectionGrid[1].getPosition() !=0
                && nextEastRoad != null) {
            if (intersectionGrid[1].leaveRoad()) {
                nextEastRoad.setVehicle(intersectionGrid[1],"start");
                intersectionGrid[1] = null;
            }
        }
        if (intersectionGrid[2] != null
                && intersectionGrid[2].getPosition() !=0
                && nextSouthRoad != null) {
            if (intersectionGrid[2].leaveRoad()) {
                nextSouthRoad.setVehicle(intersectionGrid[2],"end");
                intersectionGrid[2] = null;
            }
        }
        if (intersectionGrid[3] != null
                && intersectionGrid[3].getPosition() !=0
                && nextWestRoad != null) {
            if (intersectionGrid[3].leaveRoad()) {
                nextWestRoad.setVehicle(intersectionGrid[3],"end");
                intersectionGrid[3] = null;
            }
        }



        //Rotate the array toward right
        //Stores the last element of array
        Vehicle temp = intersectionGrid[3];

        for(int i = intersectionGrid.length-1; i > 0; i--){
            // Do not move car that has been just moved in from another road
            if (intersectionGrid[i-1] != null && intersectionGrid[i-1].getPosition() == 0) {
                intersectionGrid[i-1].incPosition();
            } else {
                try {
                    if (intersectionGrid[i] != null && intersectionGrid[i].getPosition() == 0) {
                        // Do nothing, do not replace vehicle that has just moved in
                    } else if (intersectionGrid[i-1].getPosition() != 0) {
                        // As long as car position is not 0, move it
                        //Shift element of array by one
                        intersectionGrid[i] = intersectionGrid[i-1];
                        // Destroy car
                        intersectionGrid[i-1] = null;
                    }
                } catch (NullPointerException e) {
                    // Catch null pointers wrt getposition
                }
            }
        }
        if (temp != null && temp.getPosition() == 0) {
            temp.incPosition();
        } else {
            try {
                if (intersectionGrid[0] != null && intersectionGrid[0].getPosition() == 0) {
                    // Do nothing, do not replace vehicle that has just moved in
                } else if (intersectionGrid[3].getPosition() != 0) {
                    // As long as car position is not 0, move it
                    //Last element of array will be added to the start of array.
                    intersectionGrid[0] = temp;
                    // If vehicle at pos 3 is same as pos 1, destroy the car at pos 3
                    if (intersectionGrid[0] == intersectionGrid[3]) {
                        intersectionGrid[3] = null;
                    }
                }
            } catch (NullPointerException e) {
                // Catch null pointers wrt getposition
            }
        }
    }

}
