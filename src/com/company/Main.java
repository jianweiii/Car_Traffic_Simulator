package com.company;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
	    Road road1 = new Road();
        Road road2 = new Road();

        road1.setNextRoad(road2);

	    road1.setVehicle(new Vehicle("Car"));

        System.out.println(road1.getRoadLength());
        System.out.println(road2.getRoadLength());

        road1.displayRoad();
        System.out.print(" _junction_ ");
        road2.displayRoad();

        System.out.println();

	    for (int i=0;i<(30);i++) {
            road1.moveVehicle();
            road1.displayRoad();
            System.out.print(" _junction_ ");

            road2.displayRoad();
            road2.moveVehicle();
            System.out.println();
        }
        road1.displayRoad();
        System.out.print(" _junction_ ");
        road2.displayRoad();
    }
}
