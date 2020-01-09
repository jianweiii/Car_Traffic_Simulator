package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulatorBoard extends JFrame {

    private static int boardWidth = 1000;
    private static int boardHeight = 1000;

    private Grid grid;
    private JButton cityEdit;
    private JButton simulationEdit;
    private JLabel curMode;
    private JLabel curStatus;

    public static void main(String[] args) {
        SimulatorBoard simulatorBoard = new SimulatorBoard();
    }

    public SimulatorBoard() {
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Car Traffic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // For modes
        this.add(modeSelect(),BorderLayout.NORTH);

        // Menu bar for individual modes
        CardLayout cardLayout = new CardLayout();
        JPanel menuBar = new JPanel(cardLayout);
        menuBar.add(cityEditMenu(),"city");
        menuBar.add(simulatorEditMenu(),"simulator");
        cardLayout.show(menuBar,"city");
        this.add(menuBar, BorderLayout.WEST);

        // action listener for simulation mode
        simulationEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(menuBar,"simulator");
                curMode.setText("Simulation");
            }
        });
        // action listener for city edit mode
        cityEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(menuBar,"city");
                curMode.setText("City Edit");
            }
        });

        // Status bar for current mode, vehicles, traffic light, speed
        this.add(statusBar(),BorderLayout.SOUTH);

        // Creating Grid layout
        this.add(createGridLayout(),BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintBoard(this),0,1L, TimeUnit.SECONDS);
        this.setVisible(true);

    }

    class RepaintBoard implements Runnable {
        SimulatorBoard simulatorBoard;
        int counter= 0;

        public RepaintBoard(SimulatorBoard simulatorBoard) {
            this.simulatorBoard = simulatorBoard;
        }

        @Override
        public void run() {
            if (counter % 10 == 0) {
                grid.createVehicle();
            }
            grid.updateMap();
            simulatorBoard.repaint();
            counter++;
        }
    }

    private Grid createGridLayout() {
        grid = new Grid();
        grid.setGrid(20,20);
        grid.createGrid();
        Road road1 = new Road(5,8,3, "horizontal", true, new TrafficLight(5));
        road1.setSpawnLocation("start");
        Road road2 = new Road(5,7,8, "vertical", true, new TrafficLight(5));
        road2.setSpawnLocation("end");
        Road road3 = new Road(5,8,10,"horizontal",true, new TrafficLight(5));
        road3.setSpawnLocation("end");
        Road road4 = new Road(5,14,8,"vertical", true, new TrafficLight(5));
        road4.setSpawnLocation("start");
        Intersection intersection1 = new Intersection(1,8,8,"straight", false,null);

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
        return grid;
    }

    private JPanel modeSelect() {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        cityEdit = new JButton("City Edit");
        cityEdit.setPreferredSize(new Dimension(200,60));
        cityEdit.setFont(new Font("Helvetica", Font.PLAIN,30));
        simulationEdit = new JButton(("Simulation"));
        simulationEdit.setPreferredSize(new Dimension(200,60));
        simulationEdit.setFont(new Font("Helvetica", Font.PLAIN,30));
        modePanel.add(cityEdit);
        modePanel.add(simulationEdit);
        return modePanel;
    }

    private JPanel cityEditMenu() {
        JPanel cityMenuBar = new JPanel();
        cityMenuBar.setLayout(new GridLayout(4,1));
        JButton newLayout = new JButton("New");
        newLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton openLayout = new JButton("Open");
        openLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton saveLayout = new JButton("Save");
        saveLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton exitLayout = new JButton("Exit");
        exitLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        cityMenuBar.add(newLayout);
        cityMenuBar.add(openLayout);
        cityMenuBar.add(saveLayout);
        cityMenuBar.add(exitLayout);
        return cityMenuBar;
    }

    private JPanel simulatorEditMenu() {
        JPanel simulatorMenuBar = new JPanel();
//        simulatorMenuBar.setLayout(new BoxLayout(simulatorMenuBar,BoxLayout.Y_AXIS));
        simulatorMenuBar.setLayout(new GridLayout(4,1));
        JButton runSimulator = new JButton("Start");
        runSimulator.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton stopSimulator = new JButton("Stop");
        stopSimulator.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton updateRate = new JButton("Update Rate");
        updateRate.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton vehicleSpawnRate = new JButton("Vehicle Spawn Rate");
        vehicleSpawnRate.setFont(new Font("Helvetica", Font.PLAIN,15));
        simulatorMenuBar.add(runSimulator);
        simulatorMenuBar.add(stopSimulator);
        simulatorMenuBar.add(updateRate);
        simulatorMenuBar.add(vehicleSpawnRate);
        return simulatorMenuBar;
    }

    private JPanel statusBar() {
        JPanel statusUpdate = new JPanel();
        statusUpdate.setLayout(new GridLayout(1,4));
        statusUpdate.setBorder(new EmptyBorder(10,0,10,0));
        JLabel modeLabel = new JLabel("Mode:");
        modeLabel.setFont(new Font("Helvetica", Font.PLAIN,30));
        modeLabel.setHorizontalAlignment(JLabel.CENTER);
        statusUpdate.add(modeLabel);
        curMode = new JLabel();
        curMode.setFont(new Font("Helvetica", Font.PLAIN,30));
        statusUpdate.add(curMode);
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Helvetica", Font.PLAIN,30));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusUpdate.add(statusLabel);
        curStatus = new JLabel();
        curStatus.setFont(new Font("Helvetica", Font.PLAIN,30));
        statusUpdate.add(curStatus);
        return statusUpdate;
    }
}
