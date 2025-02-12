package com.company;

public class TrafficLight {
    private String trafficLightColour;
    private double rateOfChange;
    private int pos;
    private int counter = 0;
    private String printPos;
    private int ID;
    private int updateRate;

    public TrafficLight(int pos, String printPos, int ID, int updateRate) {
        this.trafficLightColour = "Green";
//        this.rateOfChange = 0.4;
        if (ID == 1) {
            this.trafficLightColour = "Green";
        } else if (ID == 2) {
            this.trafficLightColour = "Red";
        }
        this.pos = pos;
        this.printPos = printPos;
        this.ID = ID;
        this.updateRate = updateRate*updateRate;
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
        if (counter <= (10*updateRate)) {
            counter++;
        } else {
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
