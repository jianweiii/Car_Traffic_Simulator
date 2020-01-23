package com.company;

import com.company.gui.FourWay;
import com.company.gui.Straight;
import com.company.gui.ThreeWay;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GridLayouts {
    private Grid[] gridlayouts;
    private int updateRate;
    private SimulatorBoard simulatorBoard;

    private JSONObject roadLayoutList;
    private ArrayList<Component> roadComponentArrayList1 = new ArrayList<>();
    private ArrayList<Component> roadComponentArrayList2 = new ArrayList<>();
    private ArrayList<Component> roadComponentArrayList3 = new ArrayList<>();
    private ArrayList<Component> roadComponentArrayList4 = new ArrayList<>();
    private ArrayList<Component> roadComponentArrayList5 = new ArrayList<>();

    public GridLayouts(SimulatorBoard simulatorBoard, int updateRate) {
        this.simulatorBoard = simulatorBoard;
        gridlayouts = new Grid[5];
        this.updateRate = updateRate;
    }

    public void saveGridLayout(ArrayList<Component> components, int option) {
        Grid grid = new Grid(50,50, simulatorBoard);
        ArrayList<Road> roadArray = new ArrayList<>();

        for (Component component: components) {
            // if road is straight
            if (component.getClass() == Straight.class) {
                Straight straight = (Straight) component;
                int roadLength = straight.getLength();
                int startX = straight.getxPos();
                int startY = straight.getyPos();
                String direction = straight.getDirection();
                if ((startY == 0 && direction.equals("horizontal")) || (startX == 49 && direction.equals("vertical"))) {
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true,simulatorBoard);
                    road.setSpawnLocation("start");
                    roadArray.add(road);
                    grid.addRoad(road);
                } else if ((startX+1-roadLength == 0 && direction.equals("vertical")) || (startY+roadLength-1 == 49 && direction.equals("horizontal"))) {
                    Road road = new Road(roadLength,startX+1,startY+1,direction, true,simulatorBoard);
                    road.setSpawnLocation("end");
                    roadArray.add(road);
                    grid.addRoad(road);
                } else {
                    Road road = new Road(roadLength,startX+1,startY+1,direction, false,simulatorBoard);
                    roadArray.add(road);
                    grid.addRoad(road);
                }
            }
            if (component.getClass() == ThreeWay.class) {
                ThreeWay threeWay = (ThreeWay) component;
                int startX = threeWay.getxPos();
                int startY = threeWay.getyPos();
                String direction = threeWay.getDirection();

                if (direction.equals("north")) {
                    Road road1 = new Road(2,startX+1,startY+1, "horizontal", false,simulatorBoard);
                    road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start",1,updateRate));
                    Road road2 = new Road(2,startX+1-1,startY+1+2, "vertical", false,simulatorBoard);
                    road2.setTrafficLight( new TrafficLight(0,"end",2,updateRate));
                    Road road3 = new Road(2,startX+1,startY+1+4,"horizontal",false,simulatorBoard);
                    road3.setTrafficLight( new TrafficLight(0,"end",1,updateRate));
                    Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"threeway", false,simulatorBoard);

                    grid.addRoad(road1);
                    grid.addRoad(road2);
                    grid.addRoad(road3);
                    grid.addRoad(intersection1);
                    grid.setIntersectionNextRoad(intersection1,road2,null,road3,road1);
                    grid.setNextRoad(road1,intersection1,null);
                    grid.setNextRoad(road2,null,intersection1);
                    grid.setNextRoad(road3,null,intersection1);

                    roadArray.add(road1);
                    roadArray.add(road2);
                    roadArray.add(road3);
                } else if (direction.equals("south")) {
                    Road road1 = new Road(2,startX+1,startY+1, "horizontal", false,simulatorBoard);
                    road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start",1,updateRate));
                    Road road3 = new Road(2,startX+1,startY+1+4,"horizontal",false,simulatorBoard);
                    road3.setTrafficLight( new TrafficLight(0,"end",1,updateRate));
                    Road road4 = new Road(2,startX+1+3,startY+1+2,"vertical", false,simulatorBoard);
                    road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start",2,updateRate));
                    Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"threeway", false,simulatorBoard);

                    grid.addRoad(road1);
                    grid.addRoad(road3);
                    grid.addRoad(road4);
                    grid.addRoad(intersection1);
                    grid.setIntersectionNextRoad(intersection1,null,road4,road3,road1);
                    grid.setNextRoad(road1,intersection1,null);
                    grid.setNextRoad(road3,null,intersection1);
                    grid.setNextRoad(road4,intersection1,null);

                    roadArray.add(road1);
                    roadArray.add(road3);
                    roadArray.add(road4);
                } else if (direction.equals("east")) {
                    Road road2 = new Road(2,startX+1-1,startY+1+2, "vertical", false,simulatorBoard);
                    road2.setTrafficLight( new TrafficLight(0,"end",2,updateRate));
                    Road road3 = new Road(2,startX+1,startY+1+4,"horizontal",false,simulatorBoard);
                    road3.setTrafficLight( new TrafficLight(0,"end",1,updateRate));
                    Road road4 = new Road(2,startX+1+3,startY+1+2,"vertical", false,simulatorBoard);
                    road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start",2,updateRate));
                    Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"threeway", false,simulatorBoard);

                    grid.addRoad(road2);
                    grid.addRoad(road3);
                    grid.addRoad(road4);
                    grid.addRoad(intersection1);
                    grid.setIntersectionNextRoad(intersection1,road2,road4,road3,null);
                    grid.setNextRoad(road2,null,intersection1);
                    grid.setNextRoad(road3,null,intersection1);
                    grid.setNextRoad(road4,intersection1,null);

                    roadArray.add(road2);
                    roadArray.add(road3);
                    roadArray.add(road4);
                } else if (direction.equals("west")) {
                    Road road1 = new Road(2,startX+1,startY+1, "horizontal", false,simulatorBoard);
                    road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start",1,updateRate));
                    Road road2 = new Road(2,startX+1-1,startY+1+2, "vertical", false,simulatorBoard);
                    road2.setTrafficLight( new TrafficLight(0,"end",2,updateRate));
                    Road road4 = new Road(2,startX+1+3,startY+1+2,"vertical", false,simulatorBoard);
                    road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start",2,updateRate));
                    Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"threeway", false,simulatorBoard);

                    grid.addRoad(road1);
                    grid.addRoad(road2);
                    grid.addRoad(road4);
                    grid.addRoad(intersection1);
                    grid.setIntersectionNextRoad(intersection1,road2,road4,null,road1);
                    grid.setNextRoad(road1,intersection1,null);
                    grid.setNextRoad(road2,null,intersection1);
                    grid.setNextRoad(road4,intersection1,null);

                    roadArray.add(road1);
                    roadArray.add(road2);
                    roadArray.add(road4);
                }
            }

            if (component.getClass() == FourWay.class) {
                FourWay fourWay = (FourWay) component;
                int startX = fourWay.getxPos();
                int startY = fourWay.getyPos();
                Road road1 = new Road(2,startX+1,startY+1, "horizontal", false,simulatorBoard);
                road1.setTrafficLight( new TrafficLight(road1.getRoadArray()[0].length -1,"start",1,updateRate));
                Road road2 = new Road(2,startX+1-1,startY+1+2, "vertical", false,simulatorBoard);
                road2.setTrafficLight( new TrafficLight(0,"end",2,updateRate));
                Road road3 = new Road(2,startX+1,startY+1+4,"horizontal",false,simulatorBoard);
                road3.setTrafficLight( new TrafficLight(0,"end",1,updateRate));
                Road road4 = new Road(2,startX+1+3,startY+1+2,"vertical", false,simulatorBoard);
                road4.setTrafficLight( new TrafficLight(road4.getRoadArray()[0].length -1,"start",2,updateRate));
                Intersection intersection1 = new Intersection(1,startX+1,startY+1+2,"fourway", false,simulatorBoard);

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

                roadArray.add(road1);
                roadArray.add(road2);
                roadArray.add(road3);
                roadArray.add(road4);
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
                    } else {
                        if (road1.getDirection().equals("horizontal") && road2.getDirection().equals("horizontal")) {
                            // horizontal
                            if (road1.getxRTpos() == road2.getxCoord() && road1.getyRTpos() == road2.getyCoord()) {
                                road1.setNextStartRoad(road2);
                            }
                            if (road2.getxCoord() == road1.getxRTpos() && road2.getyCoord() == road1.getyRTpos())
                            {
                                road2.setNextEndRoad(road1);
                            }
                        }
                        if (road1.getDirection().equals("vertical") && road2.getDirection().equals("vertical")) {
                            // vertical
                            if (road1.getxRTpos() == road2.getxCoord()+1 && road1.getyRTpos() == road2.getyCoord()) {
                                road1.setNextStartRoad(road2);
                            }
                            if (road1.getxCoord()+1 == road2.getxRTpos() && road1.getyCoord() == road2.getyRTpos())
                            {
                                road1.setNextEndRoad(road2);
                            }
                        }
                    }
                }
            }

        }

        // Overwrite grid layout
        gridlayouts[option-1] = grid;
        // Write to JSON
        writeToJSON(components, option);
    }

    public Grid[] getGridlayouts() {
        return gridlayouts;
    }

    private void writeToJSON(ArrayList<Component> components, int option) {

        // JSON Object
        //Remove old layout
        roadLayoutList.remove(String.valueOf(option));

        // Straight
        JSONObject straight = new JSONObject();
        JSONArray straightList = new JSONArray();

        // 3way
        JSONObject threeWay = new JSONObject();
        JSONArray threeWayList = new JSONArray();

        // 4way
        JSONObject fourWay = new JSONObject();
        JSONArray fourWayList = new JSONArray();

        for (Component component: components) {
            if (component.getClass() == Straight.class) {
                Straight straightComponent = (Straight) component;
                int length = straightComponent.getLength();
                int xStart = straightComponent.getxPos();
                int yStart = straightComponent.getyPos();
                String direction = straightComponent.getDirection();

                JSONObject straightObj = new JSONObject();
                straightObj.put("length", length);
                straightObj.put("x", xStart);
                straightObj.put("y", yStart);
                straightObj.put("direction", direction);
                straightList.add(straightObj);
            }

            if (component.getClass() == ThreeWay.class) {
                ThreeWay threeWayComponent = (ThreeWay) component;
                int xStart = threeWayComponent.getxPos()+1;
                int yStart = threeWayComponent.getyPos()+1;
                String direction = threeWayComponent.getDirection();

                JSONObject threeWayObj = new JSONObject();
                threeWayObj.put("x", xStart);
                threeWayObj.put("y", yStart);
                threeWayObj.put("direction", direction);
                threeWayList.add(threeWayObj);
            }

            if (component.getClass() == FourWay.class) {
                FourWay fourWayComponent = (FourWay) component;
                int xStart = fourWayComponent.getxPos();
                int yStart = fourWayComponent.getyPos();

                JSONObject fourWayObj = new JSONObject();
                fourWayObj.put("x", xStart);
                fourWayObj.put("y", yStart);
                fourWayList.add(fourWayObj);
            }
        }
        straight.put("straight",straightList);
        threeWay.put("threeway",threeWayList);
        fourWay.put("fourway",fourWayList);

        JSONArray componentList = new JSONArray();
        componentList.add(straight);
        componentList.add(threeWay);
        componentList.add(fourWay);

        roadLayoutList.put(String.valueOf(option), componentList);

        //Write JSON file
        try (FileWriter file = new FileWriter("road_components.json")) {

            file.write(roadLayoutList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllFromJSON(){


        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("road_components.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            roadLayoutList = (JSONObject) obj;
            System.out.println(roadLayoutList);

            JSONArray layout1 = (JSONArray) roadLayoutList.get("1");
            parseRoadLayout(layout1,1);
            JSONArray layout2 = (JSONArray) roadLayoutList.get("2");
            parseRoadLayout(layout2,2);
            JSONArray layout3 = (JSONArray) roadLayoutList.get("3");
            parseRoadLayout(layout3,3);
            JSONArray layout4 = (JSONArray) roadLayoutList.get("4");
            parseRoadLayout(layout4,4);
            JSONArray layout5 = (JSONArray) roadLayoutList.get("5");
            parseRoadLayout(layout5,5);


            saveGridLayout(roadComponentArrayList1,1);
            saveGridLayout(roadComponentArrayList2,2);
            saveGridLayout(roadComponentArrayList3,3);
            saveGridLayout(roadComponentArrayList4,4);
            saveGridLayout(roadComponentArrayList5,5);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }

    private void parseRoadLayout(JSONArray layout, int option) {
        for (Object roadObj: layout) {
            JSONObject road = (JSONObject) roadObj;
            JSONArray straightArray = (JSONArray) road.get("straight");
            if (straightArray != null) {
                for (Object straightObj: straightArray) {
                    JSONObject straightRoadObj = (JSONObject) straightObj;
                    long lengthLong = (long) straightRoadObj.get("length");
                    int length = (int) lengthLong;
                    long xStartLong = (long) straightRoadObj.get("x");
                    int xStart = (int) xStartLong;
                    long yStartLong = (long) straightRoadObj.get("y");
                    int yStart = (int) yStartLong;
                    String direction = (String) straightRoadObj.get("direction");
                    Straight straightRoad = new Straight(length,xStart+1,yStart+1,direction);
                    if (option == 1) {
                        roadComponentArrayList1.add(straightRoad);
                    } else if (option == 2) {
                        roadComponentArrayList2.add(straightRoad);
                    } else if (option == 3) {
                        roadComponentArrayList3.add(straightRoad);
                    } else if (option == 4) {
                        roadComponentArrayList4.add(straightRoad);
                    } else if (option == 5) {
                        roadComponentArrayList5.add(straightRoad);
                    }

                }
            }

            JSONArray threeWayArray = (JSONArray) road.get("threeway");
            if (threeWayArray != null) {
                for (Object threeWayObj: threeWayArray) {
                    JSONObject threeWayRoadObj = (JSONObject) threeWayObj;
                    long xStartLong = (long) threeWayRoadObj.get("x");
                    int xStart = (int) xStartLong;
                    long yStartLong = (long) threeWayRoadObj.get("y");
                    int yStart = (int) yStartLong;
                    String direction = (String) threeWayRoadObj.get("direction");
                    ThreeWay threeWay = new ThreeWay(xStart,yStart,direction);
                    if (option == 1) {
                        roadComponentArrayList1.add(threeWay);
                    } else if (option == 2) {
                        roadComponentArrayList2.add(threeWay);
                    } else if (option == 3) {
                        roadComponentArrayList3.add(threeWay);
                    } else if (option == 4) {
                        roadComponentArrayList4.add(threeWay);
                    } else if (option == 5) {
                        roadComponentArrayList5.add(threeWay);
                    }
                }
            }

            JSONArray fourWayArray = (JSONArray) road.get("fourway");
            if (fourWayArray != null) {
                for (Object fourWayObj: fourWayArray) {
                    JSONObject fourWayRoadObj = (JSONObject) fourWayObj;
                    long xStartLong = (long) fourWayRoadObj.get("x");
                    int xStart = (int) xStartLong;
                    long yStartLong = (long) fourWayRoadObj.get("y");
                    int yStart = (int) yStartLong;
                    FourWay fourWay = new FourWay(xStart+1,yStart+1);
                    if (option == 1) {
                        roadComponentArrayList1.add(fourWay);
                    } else if (option == 2) {
                        roadComponentArrayList2.add(fourWay);
                    } else if (option == 3) {
                        roadComponentArrayList3.add(fourWay);
                    } else if (option == 4) {
                        roadComponentArrayList4.add(fourWay);
                    } else if (option == 5) {
                        roadComponentArrayList5.add(fourWay);
                    }
                }
            }
        }
    }
}
