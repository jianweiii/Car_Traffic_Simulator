package com.company;

import com.company.gui.FourWay;
import com.company.gui.Straight;
import com.company.gui.ThreeWay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddNewLayout extends JPanel {
    private int gridHeight, gridWidth;
    private int[][] gridMap;
    private ArrayList<Component> componentsArray;

    public AddNewLayout(int gridHeight, int gridWidth) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.gridMap = new int[gridHeight][gridWidth];

        componentsArray = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;

        // Reset Grid
        for( int row=0; row < gridMap.length; row++ ) {
            for (int col=0; col < gridMap[row].length; col++) {
                gridMap[row][col] = 0;
            }
        }


        for (Component component: componentsArray) {
            if (component.getClass() == Straight.class) {
                updateStraightComponent(component);
            }

            // TODO: CHECK WHY VEHICLES ARE GOING MISSING IN JUNCTION
            if (component.getClass() == ThreeWay.class) {
                ThreeWay threeWay = (ThreeWay) component;
                int xStartThreeWay = threeWay.getxPos();
                int yStartThreeWay = threeWay.getyPos();
                String direction = threeWay.getDirection();

                if (direction.equals("north")) {
                    for (int row=0;row<2;row++) {
                        for (int col=0;col<6;col++) {
                            gridMap[xStartThreeWay+row][yStartThreeWay+col] = 22;
                        }
                    }

                    for (int row=0;row<2;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay-1-row][yStartThreeWay+2+col] = 22;
                        }
                    }
                } else if (direction.equals("south")) {
                    for (int row=0;row<2;row++) {
                        for (int col=0;col<6;col++) {
                            gridMap[xStartThreeWay+row][yStartThreeWay+col] = 22;
                        }
                    }

                    for (int row=0;row<2;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay+3-row][yStartThreeWay+2+col] = 22;
                        }
                    }
                } else if (direction.equals("east")) {
                    for (int row=0;row<2;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay+row][yStartThreeWay+4+col] = 22;
                        }
                    }

                    for (int row=0;row<6;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay+3-row][yStartThreeWay+2+col] = 22;
                        }
                    }
                } else if (direction.equals("west")) {
                    for (int row=0;row<2;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay+row][yStartThreeWay+col] = 22;
                        }
                    }

                    for (int row=0;row<6;row++) {
                        for (int col=0;col<2;col++) {
                            gridMap[xStartThreeWay+3-row][yStartThreeWay+2+col] = 22;
                        }
                    }
                }
            }

            if (component.getClass() == FourWay.class) {
                FourWay fourWay = (FourWay) component;
                int xStartFourWay = fourWay.getxPos();
                int yStartFourWay = fourWay.getyPos();

                for (int row=0;row<2;row++) {
                    for (int col=0;col<6;col++) {
                        gridMap[xStartFourWay+row][yStartFourWay+col] = 22;
                    }
                }

                for (int row=0;row<6;row++) {
                    for (int col=0;col<2;col++) {
                        gridMap[xStartFourWay+3-row][yStartFourWay+2+col] = 22;
                    }
                }
            }




        }

        // get width of JPanel
        int panelWidth = getWidth();
        // get height of JPanel
        int panelHeight = getHeight();
        // get length of each array box width
        int xWidth = panelWidth / gridWidth;
        // get length of each array box height
        int yHeight = panelHeight / gridHeight;

        // row
        for (int row=0;row<gridMap.length;row++) {
            // column
            for (int col=0;col<gridMap[row].length;col++) {
                if (gridMap[row][col] == 22) {
                    graphicsSettings.setColor(Color.BLACK);
                } else {
                    graphicsSettings.setColor(new Color(197,197,197));
                }

                int x = (col*panelWidth) / gridMap.length;
                int y = (row*panelHeight) / gridMap[row].length;

                graphicsSettings.fillRect(x+1,y+1,xWidth-1,yHeight-1);
            }

        }

    }

    @Override
    public Component add(Component comp) {
        componentsArray.add(comp);
        repaint();
        return super.add(comp);
    }

    public ArrayList<Component> getComponentsArray() {
        return componentsArray;
    }

    public void clear() {
        componentsArray.clear();
    }

    private void updateStraightComponent(Component component) {
        Straight straight = (Straight) component;
        int xStraight = straight.getxPos();
        int yStraight = straight.getyPos();
        int lengthStraight = straight.getLength();
        if (straight.getDirection().equals("horizontal")){
            for (int row=0;row<2;row++) {
                for (int col=0;col<lengthStraight;col++) {
                    gridMap[xStraight + row][yStraight + col] = 22;
                }
            }
        } else if (straight.getDirection().equals("vertical")){
            for (int row=0;row<2;row++) {
                for (int col=0;col<lengthStraight;col++) {
                    gridMap[xStraight - col][yStraight + row] = 22;
                }
            }
        }
    }
}
