package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

//        JFrame jFrame = new JFrame();
//        jFrame.setSize(1000,1000);
//        jFrame.setTitle("Car Simulator Traffic");
//        JButton button,button1, button2, button3,button4;
//        button = new JButton("left");
//        button1 = new JButton("right");
//        button2 = new JButton("top");
//        button3 = new JButton("bottom");
//        button4 = new JButton("center");
////        jFrame.add(button,BorderLayout.WEST);
////        jFrame.add(button1, BorderLayout.EAST);
//        jFrame.add(button2, BorderLayout.NORTH);
//        jFrame.add(button3, BorderLayout.SOUTH);
////        jFrame.add(button4, BorderLayout.CENTER);
//
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	    Road road1 = new Road(5,8,3, "horizontal", true, new TrafficLight(5));
//	    road1.setSpawnLocation("start");
//        Road road2 = new Road(5,7,8, "vertical", true, new TrafficLight(5));
//        road2.setSpawnLocation("end");
//        Road road3 = new Road(5,8,10,"horizontal",true, new TrafficLight(5));
//        road3.setSpawnLocation("end");
//        Road road4 = new Road(5,14,8,"vertical", true, new TrafficLight(5));
//        road4.setSpawnLocation("start");
//        Intersection intersection1 = new Intersection(1,8,8,"straight", false,null);


//        Road road5 = new Road(5,11,8,"vertical-down", false, null);
//        Road road6 = new Road(5,10,7,"horizontal-left", false,null);
//
//        Intersection intersection2 = new Intersection(1,10,8,"straight", false, null);
//        intersection2.setPreviousRoad(road2);
////
////        road1.setNextRoad(road2);
//
////	    road1.setVehicle(new Vehicle("Car"));
//
//	    Grid grid = new Grid();
//        grid.setGrid(20,20);
//        grid.createGrid();
//
//        grid.addRoad(road1);
//        grid.addRoad(road2);
//        grid.addRoad(road3);
//        grid.addRoad(road4);
//        grid.addRoad(intersection1);
//        grid.setIntersectionNextRoad(intersection1,road2,road4,road3,road1);
//        grid.setNextRoad(road1,intersection1,null);
//        grid.setNextRoad(road2,null,intersection1);
//        grid.setNextRoad(road3,null,intersection1);
//        grid.setNextRoad(road4,intersection1,null);



//        grid.addRoad(road5);
//        grid.addRoad(road6);
//        grid.addRoad(intersection2);
//
//        grid.setNextRoad(road1,intersection1);
//        grid.setIntersectionNextRoad(intersection1, null, road2, road3, road1);
//        grid.setNextRoad(intersection1,road2);

//        grid.setNextRoad(road1,intersection1);
//        grid.setIntersectionNextRoad(intersection1, null, road2, road3, road1);
//        grid.setNextRoad(road2,intersection2);
//        grid.setIntersectionNextRoad(intersection2,road2,road5,road4,road6);
//        grid.createVehicle();

//        grid.updateMap();
//        grid.displayAll();


//        for (int i=0; i<5; i++) {
//            grid.updateMap();
//            grid.displayAll();
//            grid.moveVehicles();
//            System.out.println();
//        }
//        final int[] i = {0};
//        int delay = 0;
//        int interval = 2000;
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if(i[0] % 5 ==0) {
//                    grid.createVehicle();
//                }
//                grid.updateMap();
//                grid.displayAll();
//                grid.moveVehicles();
//                System.out.println();
//                i[0]++;
//
////                grid.displayAll();
//            }
//        },delay,interval);

    }
}
