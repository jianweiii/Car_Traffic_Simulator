package com.company;

import java.util.Random;

public class TrafficLight {
    private String trafficLightColour;
    private double rateOfChange;

    public TrafficLight() {
        this.trafficLightColour = "Green";
        this.rateOfChange = 0.3;
    }

    public String getTrafficLightColour() {
        return trafficLightColour;
    }

    public void trafficOperator() {
        Random random = new Random();
        if (random.nextDouble() < 0.3) {
            if (trafficLightColour.equals("Green")) {
                trafficLightColour = "Red";
            } else {
                trafficLightColour = "Green";
            }
        }
    }
}
