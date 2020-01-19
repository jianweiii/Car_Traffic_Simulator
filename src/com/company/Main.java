package com.company;

public class Main {

    public static void main(String[] args) {

//        Grid grid = new Grid(20,20);
//        Road road1 = new Road(2,8,6, "horizontal", false);
//        road1.setSpawnLocation("start");
//        road1.setTrafficLight( new TrafficLight("start"));
//        Road road2 = new Road(2,7,8, "vertical", false);
//        road2.setTrafficLight( new TrafficLight("end"));
//        road2.setSpawnLocation("end");
//        Road road3 = new Road(2,8,10,"horizontal",false);
//        road3.setSpawnLocation("end");
//        road3.setTrafficLight( new TrafficLight("end"));
//        Road road4 = new Road(5,14,8,"vertical", false);
//        road4.setSpawnLocation("start");
//        road4.setTrafficLight( new TrafficLight("start"));
//        Intersection intersection1 = new Intersection(1,8,8,"straight", false);
//        Road road5 = new Road(3,8,3,"horizontal",false);
//        road5.setSpawnLocation("start");
//        Road road6 = new Road(3,5,8,"vertical",true);
//        road6.setSpawnLocation("end");
//
//
//        grid.addRoad(road1);
//        grid.addRoad(road2);
//        grid.addRoad(road3);
//        grid.addRoad(road4);
//        grid.addRoad(intersection1);
//        grid.setIntersectionNextRoad(intersection1,road2,road4,road3,road1);
//        grid.setNextRoad(road1,intersection1,road5);
//        grid.setNextRoad(road2,road6,intersection1);
//        grid.setNextRoad(road3,null,intersection1);
//        grid.setNextRoad(road4,intersection1,null);
//        grid.addRoad(road5);
//        grid.setNextRoad(road5,road1,null);
//        grid.addRoad(road6);
//        grid.setNextRoad(road6,null,road2);
//        road1.setNextEndRoad(road5);
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
//        int interval = 500;
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
