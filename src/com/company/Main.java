package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
	    Road road1 = new Road(5,4,3, "horizontal-right", new TrafficLight(5));
        Road road2 = new Road(5,5,8, "vertical-down", new TrafficLight(5));
        Road road3 = new Road(5,4,9,"horizontal-right", null);

        Intersection intersection1 = new Intersection(1,4,8,"straight", null);
        intersection1.setPreviousRoad(road1);

        Road road4 = new Road(5,10,9,"horizontal-right", null);
        Road road5 = new Road(5,11,8,"vertical-down", null);
        Road road6 = new Road(5,10,7,"horizontal-left",null);

        Intersection intersection2 = new Intersection(1,10,8,"straight", null);
        intersection2.setPreviousRoad(road2);
//
//        road1.setNextRoad(road2);

//	    road1.setVehicle(new Vehicle("Car"));

	    Grid grid = new Grid();
        grid.setGrid(15,15);
        grid.createGrid();

        grid.addRoad(road1);
        grid.addRoad(road2);
        grid.addRoad(road3);
        grid.addRoad(intersection1);
        grid.addRoad(road4);
        grid.addRoad(road5);
        grid.addRoad(road6);
        grid.addRoad(intersection2);

        grid.setNextRoad(road1,intersection1);
        grid.setIntersectionNextRoad(intersection1, null, road2, road3, road1);
//        grid.setNextRoad(intersection1,road2);

//        grid.setNextRoad(road1,intersection1);
//        grid.setIntersectionNextRoad(intersection1, null, road2, road3, road1);
        grid.setNextRoad(road2,intersection2);
        grid.setIntersectionNextRoad(intersection2,road2,road5,road4,road6);
//        grid.createVehicle();

//        grid.updateMap();
//        grid.displayAll();


//        for (int i=0; i<5; i++) {
//            grid.updateMap();
//            grid.displayAll();
//            grid.moveVehicles();
//            System.out.println();
//        }
        final int[] i = {0};
        int delay = 1000;
        int interval = 2000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(i[0] %10 ==0) {
                    grid.createVehicle();
                }
                grid.updateMap();
                grid.displayAll();
                grid.moveVehicles();
                System.out.println();
                i[0]++;

//                grid.displayAll();
            }
        },delay,interval);

    }

    public static void clearConsole() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_1);
        } catch (AWTException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
