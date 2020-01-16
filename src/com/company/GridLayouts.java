package com.company;

import com.company.gui.FourWay;
import com.company.gui.Straight;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GridLayouts {
    private Grid[] gridlayouts;

    public GridLayouts() {
        gridlayouts = new Grid[5];
    }

    public void createGrid() {
        Grid grid = new Grid(50,50);
        Road road1 = new Road(2,8,6, "horizontal", false);
        road1.setSpawnLocation("start");
        road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start"));
        Road road2 = new Road(2,7,8, "vertical", false);
        road2.setTrafficLight( new TrafficLight(0,"end"));
        road2.setSpawnLocation("end");
        Road road3 = new Road(5,8,10,"horizontal",false);
        road3.setSpawnLocation("end");
        road3.setTrafficLight( new TrafficLight(0,"end"));
        Road road4 = new Road(5,14,8,"vertical", false);
        road4.setSpawnLocation("start");
        road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start"));
        Intersection intersection1 = new Intersection(1,8,8,"straight", false);
        Road road5 = new Road(3,8,3,"horizontal",false);
        road5.setSpawnLocation("start");
        Road road6 = new Road(3,5,8,"vertical",true);
        road6.setSpawnLocation("end");





        grid.addRoad(road1);
        grid.addRoad(road2);
        grid.addRoad(road3);
        grid.addRoad(road4);
        grid.addRoad(intersection1);
        grid.setIntersectionNextRoad(intersection1,road2,road4,road3,road1);
        grid.setNextRoad(road1,intersection1,road5);
        grid.setNextRoad(road2,road6,intersection1);
        grid.setNextRoad(road3,null,intersection1);
        grid.setNextRoad(road4,intersection1,null);
        grid.addRoad(road5);
        grid.setNextRoad(road5,road1,null);
        grid.addRoad(road6);
        grid.setNextRoad(road6,null,road2);

        gridlayouts[0] = grid;
    }

    public void saveGridLayout(ArrayList<Component> components, int option) {
        Grid grid = new Grid(50,50);
        ArrayList<Road> roadArray = new ArrayList<>();
        ArrayList<ArrayList<Road>> multiRoadArray = new ArrayList<>();
        ArrayList<Road> intersectionArray = new ArrayList<>();
        for (Component component: components) {
            // if road is straight
            if (component.getClass() == Straight.class) {
                Straight straight = (Straight) component;
                int roadLength = straight.getLength();
                int startX = straight.getxPos();
                int startY = straight.getyPos();
                String direction = straight.getDirection();
                System.out.println(startX);
                System.out.println(startY);
                if ((startY == 0 && direction.equals("horizontal")) || (startX == 49 && direction.equals("vertical"))) {
                    System.out.println("starty:" + startY);
                    System.out.println("startx:" + startX);
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true);
                    System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                            road.getxCoord(),road.getyCoord(),road.getxRTpos(),road.getyRTpos()));
                    road.setSpawnLocation("start");
                    roadArray.add(road);
                    grid.addRoad(road);
                    System.out.println(road);

                } else if ((startX+1-roadLength == 0 && direction.equals("vertical")) || (startY+roadLength-1 == 49 && direction.equals("horizontal"))) {
                    System.out.println("starty:" + startY);
                    System.out.println("startx:" + startX);
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true);
                    System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                            road.getxCoord(),road.getyCoord(),road.getxRTpos(),road.getyRTpos()));
                    road.setSpawnLocation("end");
                    roadArray.add(road);
                    grid.addRoad(road);
                    System.out.println(road);

                } else {
                    Road road = new Road(roadLength,startX+1,startY+1,direction, false);
                    System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                            road.getxCoord(),road.getyCoord(),road.getxRTpos(),road.getyRTpos()));
                    roadArray.add(road);
                    grid.addRoad(road);
                    System.out.println(road);
                }
            }
            // TODO: 3-way

            // TODO: 4-way
            if (component.getClass() == FourWay.class) {
                FourWay fourWay = (FourWay) component;
                int startX = fourWay.getxPos();
                int startY = fourWay.getyPos();
                Road road1 = new Road(2,startX+1,startY+1, "horizontal", false);
                road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start"));
                Road road2 = new Road(2,startX+1-1,startY+1+2, "vertical", false);
                road2.setTrafficLight( new TrafficLight(0,"end"));
                Road road3 = new Road(2,startX+1,startY+1+4,"horizontal",false);
                road3.setTrafficLight( new TrafficLight(0,"end"));
                Road road4 = new Road(2,startX+1+3,startY+1+2,"vertical", false);
                road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start"));
                Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"straight", false);

                grid.addRoad(road1);
                grid.addRoad(road2);
                grid.addRoad(road3);
                grid.addRoad(road4);
                grid.addRoad(intersection1);
                grid.setIntersectionNextRoad(intersection1,road2,road4,road3,road1);
                grid.setNextRoad(road1,intersection1,null);
                grid.setNextRoad(road2,null,intersection1);
                grid.setNextRoad(road3,null,intersection1);
                grid.setNextRoad(road4,intersection1,null);

                System.out.println("intersection");
                System.out.println(intersection1);

                roadArray.add(road1);
                roadArray.add(road2);
                roadArray.add(road3);
                roadArray.add(road4);
                System.out.println("road2");
                System.out.println(road2);
//                intersectionArray.add(road1);
//                intersectionArray.add(road2);
//                intersectionArray.add(road3);
//                intersectionArray.add(road4);
//                multiRoadArray.add(intersectionArray);

                System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                        road1.getxCoord(),road1.getyCoord(),road1.getxRTpos(),road1.getyRTpos()));
                System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                        road2.getxCoord(),road2.getyCoord(),road2.getxRTpos(),road2.getyRTpos()));
                System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                        road3.getxCoord(),road3.getyCoord(),road3.getxRTpos(),road3.getyRTpos()));
                System.out.println(String.format("sx:%d, sy:%d, ex:%d. ey:%d",
                        road4.getxCoord(),road4.getyCoord(),road4.getxRTpos(),road4.getyRTpos()));



            }
        }


        for (Road road1: roadArray) {
            if (road1.getClass() == Intersection.class) {
                // Do nothing
            } else {
                // for straight roads checking
                for (Road road2: roadArray) {
                    if (road2.getClass() == Intersection.class) {
                        // Do nothing
                        System.out.println("intersection");
                        System.out.println(road2);
                    } else {
                        if (road1.getDirection().equals("horizontal")) {
                            // horizontal
//                            System.out.println(String.format("r1ex: %d, r2sx: %d, r1ey: %d, r2sy: %d", road1.getxRTpos(),road2.getxCoord(),road1.getyRTpos(), road2.getyCoord()));
                            if (road1.getxRTpos() == road2.getxCoord() && road1.getyRTpos() == road2.getyCoord()) {
                                System.out.println("horizontal-1");
                                System.out.println(String.format("r1ex: %d, r2sx: %d, r1ey: %d, r2sy: %d", road1.getxRTpos(),road2.getxCoord(),road1.getyRTpos(), road2.getyCoord()));
                                road1.setNextStartRoad(road2);
                                System.out.println("road1");
                                System.out.println(road1);
                                System.out.println("road2");
                                System.out.println(road2);
                            }
                            if (road2.getxCoord() == road1.getxRTpos() && road2.getyCoord() == road1.getyRTpos())
                            {
                                System.out.println("horizontal-2");
                                System.out.println(String.format("r1sx: %d, r2ex: %d, r1sy: %d, r2ey: %d", road1.getxCoord(),road2.getxRTpos(),road1.getyCoord(), road2.getyRTpos()));
                                road2.setNextEndRoad(road1);
                                System.out.println("road1");
                                System.out.println(road1);
                                System.out.println("road2");
                                System.out.println(road2);
                            }
                        }
                        if (road1.getDirection().equals("vertical")) {
                            // vertical
                            if (road1.getxRTpos() == road2.getxCoord()+1 && road1.getyRTpos() == road2.getyCoord()) {
                                System.out.println("vertical1");
                                System.out.println(String.format("r1ex: %d, r2sx: %d, r1ey: %d, r2sy: %d", road1.getxRTpos(),road2.getxCoord(),road1.getyRTpos(), road2.getyCoord()));
                                road1.setNextStartRoad(road2);
                                System.out.println(road1);
                                System.out.println(road2);
                            }
                            if (road1.getxCoord()+1 == road2.getxRTpos() && road1.getyCoord() == road2.getyRTpos())
                            {
                                System.out.println("vertical2");
                                System.out.println(String.format("r1sx: %d, r2ex: %d, r1sy: %d, r2ey: %d", road1.getxCoord(),road2.getxRTpos(),road1.getyCoord(), road2.getyRTpos()));
                                road1.setNextEndRoad(road2);
                                System.out.println(road1);
                                System.out.println(road2);


                            }
                        }
                    }



                }
            }


//            // for intersection checking
//            for (ArrayList<Road> intersection: multiRoadArray) {
//                int startX = intersection.get(0).getxCoord();
//                int startY = intersection.get(0).getyCoord();
//
//
//
//            }
        }

        // Overwrite grid layout
        gridlayouts[option-1] = grid;
    }

    public Grid[] getGridlayouts() {
        return gridlayouts;
    }
}
