package com.company;

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
}
