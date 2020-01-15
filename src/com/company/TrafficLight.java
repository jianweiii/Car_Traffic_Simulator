package com.company;

import java.util.Random;

public class TrafficLight {
    private String trafficLightColour;
    private double rateOfChange;
    private String pos;
    private int counter = 0;

    public TrafficLight(String pos) {
        this.trafficLightColour = "Green";
        this.rateOfChange = 0.5;
        this.pos = pos;
    }

    public String getPos() {
        return pos;
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
