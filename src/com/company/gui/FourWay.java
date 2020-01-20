package com.company.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FourWay extends JPanel {
    private int xPos;
    private int yPos;

    public FourWay(int x, int y) {
        this.xPos = x;
        this.yPos = y;
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

    public int getxPos() {
        return xPos-1;
    }

    public int getyPos() {
        return yPos-1;
    }


}
