package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Vehicle {

    private String type;
    private int position;

    public Vehicle(String type, int position) {
        this.type = type;
        this.position = position;
    }

    public Vehicle(String type) {
        this.type = type;
        this.position = 0;
    }

    public String getType() {
        return type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void incPosition() {
        this.position += 1;
    }

    public Road selectNextRoad (ArrayList<Road> roadOption) {
        Random r = new Random();
        int chosenNumber = r.nextInt(roadOption.size());
        return roadOption.get(chosenNumber);
    }

    public Boolean leaveRoad () {
        if (position == 3) {
            return true;
        }
        Random r = new Random();
        return r.nextBoolean();
    }
}
