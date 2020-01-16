package com.company;

import com.company.gui.FourWay;
import com.company.gui.Straight;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulatorBoard extends JFrame {
    private static SimulatorBoard simulatorBoard;

    private static int boardWidth = 1000;
    private static int boardHeight = 1000;

    private GridLayouts gridLayouts;
    private Grid grid;
    private JButton cityEdit, simulationEdit;
    private JButton runSimulator, stopSimulator;
    private JButton openLayout;
    private JLabel curMode;
    private JLabel curStatus;
    private Boolean simulationRunning = false;
    ScheduledThreadPoolExecutor executor;
    private JFrame mContent;
    private JPanel mainActivityLayout;
    private CardLayout mainCard;
    private int roadLength;
    private AddNewLayout addNewLayout;

    private int counter= 0;

    private Boolean isOpenLayout = true;

    public static void main(String[] args) {
        simulatorBoard = new SimulatorBoard();
    }

    private SimulatorBoard() {
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Car Traffic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mContent = this;

        // Load all grid layouts
        gridLayouts = new GridLayouts();
        gridLayouts.createGrid();

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

        runSimulator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!simulationRunning) {
                    System.out.println(executor);
                    // Do not create new threads if there is a current one
                    if (executor == null || executor.isTerminated()) {
                        // Allocate 5 threads
                        executor = new ScheduledThreadPoolExecutor(5);
                        executor.scheduleAtFixedRate(new RepaintBoard(simulatorBoard),0,1L, TimeUnit.SECONDS);
                    }

                }
            }
        });

        stopSimulator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                executor.shutdown();
                try {
                    if (executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                        System.out.println("task completed");
                    } else {
                        System.out.println("Forcing shutdown...");
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

        // action listener for open layout button
        openLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainCard.show(mainActivityLayout, "openLayout");
            }
        });

        // Where all the main center layout
        this.add(mainActivity(),BorderLayout.CENTER);

        this.setVisible(true);

    }

    class RepaintBoard implements Runnable {
        SimulatorBoard simulatorBoard;

        public RepaintBoard(SimulatorBoard simulatorBoard) {
            this.simulatorBoard = simulatorBoard;
        }

        @Override
        public void run() {
            if (counter % 5 == 0) {
                grid.createVehicle();
            }
            grid.updateMap();
            simulatorBoard.repaint();
            counter++;
        }
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

        JPanel switchPanel = new JPanel();
        CardLayout addRoadCard = new CardLayout();
        switchPanel.setLayout(addRoadCard);

        openLayout = new JButton("Open");
        openLayout.setFont(new Font("Helvetica", Font.PLAIN,20));

        JPanel addRoadPanel = new JPanel(new GridLayout(3,1));
        JButton straightRoad = new JButton("Straight");
        JButton threeWayIntersection = new JButton("3-way");
        JButton fourWayIntersection = new JButton("4-way");
        addRoadPanel.add(straightRoad);
        addRoadPanel.add(threeWayIntersection);
        addRoadPanel.add(fourWayIntersection);

        switchPanel.add(openLayout,"open");
        switchPanel.add(addRoadPanel,"addRoad");

        addRoadCard.show(switchPanel,"open");

        JButton saveLayout = new JButton("Save");
        saveLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        JButton exitLayout = new JButton("Exit");
        exitLayout.setFont(new Font("Helvetica", Font.PLAIN,20));
        cityMenuBar.add(newLayout);
        cityMenuBar.add(switchPanel);
        cityMenuBar.add(saveLayout);
        cityMenuBar.add(exitLayout);

        newLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isOpenLayout) {
                    addRoadCard.show(switchPanel,"open");
                    isOpenLayout = true;
                } else {
                    addRoadCard.show(switchPanel,"addRoad");
                    mainCard.show(mainActivityLayout,"addLayout");
                    addNewLayout.clear();
                    isOpenLayout = false;
                }
            }
        });

        saveLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                int layoutOption = Integer.parseInt(JOptionPane.showInputDialog("Save to layout(1-5)"));
                int layoutOption = 2;
                gridLayouts.saveGridLayout(addNewLayout.getComponentsArray(), layoutOption);
            }
        });

        exitLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        straightRoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                JTextField roadLength = new JTextField(10);
//                JTextField roadDirection = new JTextField(10);
//
//                JPanel myPanel = new JPanel();
//                myPanel.add(new JLabel("Road Length:"));
//                myPanel.add(roadLength);
//                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//                myPanel.add(new JLabel("Road Direction:"));
//                myPanel.add(roadDirection);
//
//                while (true) {
//                    int result = JOptionPane.showConfirmDialog(null, myPanel,
//                            "Please Enter Road Length and Direction", JOptionPane.OK_CANCEL_OPTION);
//                    if (result == JOptionPane.OK_OPTION) {
//                        System.out.println("road length value: " + roadLength.getText());
//                        System.out.println("road direction value: " + roadDirection.getText());
//
//                    }
//                    if (roadDirection.getText().equals("horizontal") || roadDirection.getText().equals("vertical")) {
//                        break;
//                    }
//                }
//                addNewLayout.add(new Straight(Integer.parseInt(roadLength.getText()),5,5,roadDirection.getText()));
                addNewLayout.add(new Straight(5,5,5,"horizontal"));
            }
        });

        fourWayIntersection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addNewLayout.add(new FourWay(10,10));
            }
        });
        return cityMenuBar;
    }

    private JPanel simulatorEditMenu() {
        JPanel simulatorMenuBar = new JPanel();
//        simulatorMenuBar.setLayout(new BoxLayout(simulatorMenuBar,BoxLayout.Y_AXIS));
        simulatorMenuBar.setLayout(new GridLayout(4,1));
        runSimulator = new JButton("Start");
        runSimulator.setFont(new Font("Helvetica", Font.PLAIN,20));
        stopSimulator = new JButton("Stop");
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

    private JPanel openLayoutMenu() {
        JPanel openLayout = new JPanel(new GridLayout(5,1));
        JButton layout1 = new JButton("Layout 1");
        JButton layout2 = new JButton("Layout 2");
        JButton layout3 = new JButton("Layout 3");
        JButton layout4 = new JButton("Layout 4");
        JButton layout5 = new JButton("Layout 5");
        openLayout.add(layout1);
        openLayout.add(layout2);
        openLayout.add(layout3);
        openLayout.add(layout4);
        openLayout.add(layout5);

        layout1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grid = gridLayouts.getGridlayouts()[0];
                    if (grid != null) {
                        mainActivityLayout.add(grid,"indvLayout");
                        mainCard.show(mainActivityLayout,"indvLayout");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        layout2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grid = gridLayouts.getGridlayouts()[1];
                    if (grid != null) {
                        mainActivityLayout.add(grid,"indvLayout");
                        mainCard.show(mainActivityLayout,"indvLayout");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        layout3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grid = gridLayouts.getGridlayouts()[2];
                    if (grid != null) {
                        mainActivityLayout.add(grid,"indvLayout");
                        mainCard.show(mainActivityLayout,"indvLayout");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        layout4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grid = gridLayouts.getGridlayouts()[3];
                    if (grid != null) {
                        mainActivityLayout.add(grid,"indvLayout");
                        mainCard.show(mainActivityLayout,"indvLayout");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        layout5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    grid = gridLayouts.getGridlayouts()[4];
                    if (grid != null) {
                        mainActivityLayout.add(grid,"indvLayout");
                        mainCard.show(mainActivityLayout,"indvLayout");
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        return openLayout;
    }

    private JPanel mainActivity() {
        mainActivityLayout = new JPanel();
        mainCard = new CardLayout();
        mainActivityLayout.setLayout(mainCard);

        JPanel emptyPanel = new JPanel();

        mainActivityLayout.add(emptyPanel,"empty");
        mainActivityLayout.add(openLayoutMenu(), "openLayout");
        mainActivityLayout.add(addNewLayout(), "addLayout");
        return mainActivityLayout;
    }

    private JPanel addNewLayout() {
        addNewLayout = new AddNewLayout(50,50);
//        Straight straight = new Straight(5,5,5);

//        addNewLayout.setLayout();
        return addNewLayout;
    }
}
