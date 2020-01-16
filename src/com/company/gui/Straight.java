package com.company.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Straight extends JPanel {

    private int length;
    private int xPos;
    private int yPos;
    private String direction;
    private int[][] straightRoad;


    //  (xLBStart,yLBStart)--------------(xRTStart,yRTStart)
    //     (xLBEnd, yLBEnd)--------------(xRTEnd,yRTEnd)
    private int xLBStartPos, yLBStartPos;
    private int xLBEndPos, yLBEndPos;
    private int xRTEndPos, yRTEndPos;
    private int xRTStartPos, yRTStartPos;


    public Straight(int length, int x, int y,String direction) {
        this.length = length;
        this.xPos = x;
        this.yPos = y;
        this.direction = direction;
        this.straightRoad = new int[2][length];
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Action.down");
        am.put("Action.down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPos += 1;
                getParent().repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Action.right");
        am.put("Action.right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yPos += 1;
                getParent().repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Action.up");
        am.put("Action.up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPos -= 1;
                getParent().repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Action.left");
        am.put("Action.left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yPos -= 1;
                getParent().repaint();
            }
        });
    }


    public int getLength() {
        return length;
    }

    public int getxPos() {
        return xPos-1;
    }

    public int getyPos() {
        return yPos-1;
    }

    public String getDirection() {
        return direction;
    }

    public int getxLBStartPos() {
        xLBStartPos = xPos;
        return xLBStartPos;
    }

    public int getyLBStartPos() {
        yLBStartPos = yPos;
        return yLBStartPos;
    }

    public int getxRTStartPos() {
        if (direction.equals("horizontal")) {
            xRTStartPos = xPos;
        } else if (direction.equals("vertical")) {
            xRTStartPos = xPos-length;
        }
        return xRTStartPos;
    }

    public int getyRTStartPos() {
        if (direction.equals("horizontal")) {
            yRTStartPos = yPos+length;
        } else if (direction.equals("vertical")) {
            yRTStartPos = yPos;
        }
        return yRTStartPos;
    }
//
//    public int getxLBEndPos() {
//        if (direction.equals("horizontal")) {
//            xLBEndPos = xPos +1;
//        } else if (direction.equals("vertical")) {
//            xLBEndPos = xPos;
//        }
//        return xLBEndPos;
//    }
//
//    public int getyLBEndPos() {
//        if (direction.equals("horizontal")) {
//            yLBEndPos = yPos;
//        } else if (direction.equals("vertical")) {
//            yLBEndPos = yPos + 1;
//        }
//        return yLBEndPos;
//    }
//
//    public int getxRTEndPos() {
//        if (direction.equals("horizontal")) {
//            xRTEndPos = xPos + 1;
//        } else if (direction.equals("vertical")) {
//            xRTEndPos = xPos - length;
//        }
//        return xRTEndPos;
//    }
//
//    public int getyRTEndPos() {
//        if (direction.equals("horizontal")) {
//
//        } else if (direction.equals("vertical")) {
//
//        }
//        return yRTEndPos;
//    }


}
