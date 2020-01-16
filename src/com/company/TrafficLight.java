package com.company;

import java.util.Random;

public class TrafficLight {
    private String trafficLightColour;
    private double rateOfChange;
    private int pos;
    private int counter = 0;
    private String printPos;

    public TrafficLight(int pos, String printPos) {
        this.trafficLightColour = "Green";
        this.rateOfChange = 0.4;
        this.pos = pos;
        this.printPos = printPos;
    }

    public int getPos() {
        return pos;
    }

    public String getPrintPos() {
        return printPos;
    }

    public String getTrafficLightColour() {
        return trafficLightColour;
    }

    public void trafficOperator() {
        if (counter <= 4) {
            counter++;
        } else {
            Random random = new Random();
            if (random.nextDouble() < rateOfChange) {
                if (trafficLightColour.equals("Green")) {
                    trafficLightColour = "Red";
                    counter = 0;
                } else {
                    trafficLightColour = "Green";
                    counter = 0;
                }
            }
        }
    }
}
