package com.company;

import java.awt.*;
import java.util.ArrayList;

public class GridLayouts {
    private Grid[] gridlayouts;

    public GridLayouts() {
        gridlayouts = new Grid[5];
    }

    public void createGrid() {
        Grid grid = new Grid(50,50);
        Road road1 = new Road(2,8,6, "horizontal", false);
        road1.setSpawnLocation("start");
        road1.setTrafficLight( new TrafficLight("start"));
        Road road2 = new Road(5,7,8, "vertical", false);
        road2.setTrafficLight( new TrafficLight("end"));
        road2.setSpawnLocation("end");
        Road road3 = new Road(5,8,10,"horizontal",false);
        road3.setSpawnLocation("end");
        road3.setTrafficLight( new TrafficLight("end"));
        Road road4 = new Road(5,14,8,"vertical", false);
        road4.setSpawnLocation("start");
        road4.setTrafficLight( new TrafficLight("start"));
        Intersection intersection1 = new Intersection(1,8,8,"straight", false);
        Road road5 = new Road(3,8,3,"horizontal",true);
        road5.setSpawnLocation("start");

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
        grid.addRoad(road5);
        grid.setNextRoad(road5,road1,null);
        gridlayouts[0] = grid;
    }

    public void saveGridLayout(ArrayList<Component> components, int option) {
        Grid grid = new Grid(50,50);
        ArrayList<Road> roadArray = new ArrayList<>();
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
                if (startY == 0 || startX == 49) {
                    System.out.println("starty:" + startY);
                    System.out.println("startx:" + startX);
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true);
                    road.setSpawnLocation("start");
                    roadArray.add(road);
                    grid.addRoad(road);

                } else if ((startX+1-roadLength) == 0 || startX+roadLength == 49) {
                    System.out.println("starty:" + startY);
                    System.out.println("startx:" + startX);
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true);
                    road.setSpawnLocation("end");
                    roadArray.add(road);
                    grid.addRoad(road);

                } else {
                    Road road = new Road(roadLength,startX+1,startY+1,direction, false);
                    roadArray.add(road);
                    grid.addRoad(road);
                }
            }
            // TODO: 3-way
            // TODO: 4-way
        }

        // for straight roads checking
        for (Road road1: roadArray) {
            for (Road road2: roadArray) {

                if (road1.getxRTpos() == road2.getxCoord() && road1.getyRTpos() == road2.getyCoord()) {
                    System.out.println(String.format("r1ex: %d, r2sx: %d, r1ey: %d, r2sy: %d", road1.getxRTpos(),road2.getxCoord(),road1.getyRTpos(), road2.getyCoord()));
//                    nextStartRoad = road2;
                    road1.setNextStartRoad(road2);

                }
                else if (road1.getxCoord() == road2.getxRTpos() && road1.getyCoord() == road2.getyRTpos())
                {
                    System.out.println(String.format("r1sx: %d, r2ex: %d, r1sy: %d, r2ey: %d", road1.getxCoord(),road2.getxRTpos(),road1.getyCoord(), road2.getyRTpos()));
//                    nextEndRoad = road2;
                    road2.setNextEndRoad(road1);
                }
            }
        }

        // Overwrite grid layout
        gridlayouts[option-1] = grid;
    }

    public Grid[] getGridlayouts() {
        return gridlayouts;
    }
}
