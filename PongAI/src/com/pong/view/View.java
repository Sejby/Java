package com.pong.view;

import com.pong.ai.Controller;
import com.pong.logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View extends JFrame {
    private static View instance;

    public static View GetInstance() {
        if (instance == null) instance = new View();
        return instance;
    }

    private View() {
        super();
        setLayout(null);
        setSize(850, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        Dimension size = new Dimension(110, 25);
        //<editor-fold defaultstate="collapsed" desc="Canvas">
        canvas = new Canvas();
        canvas.setLocation(20, 5);
        add(canvas);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Left JPanel">
        JPanel guiGroupLeft = new JPanel();
        guiGroupLeft.setSize(getWidth()/2, 300);
        guiGroupLeft.setLocation(0, 500);
        guiGroupLeft.setLayout(null);
        add(guiGroupLeft);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Right JPanel">
        JPanel guiGroupRight = new JPanel();
        guiGroupRight.setSize(getWidth()/2, 300);
        guiGroupRight.setLocation(getWidth()/2, 500);
        guiGroupRight.setLayout(null);
        add(guiGroupRight);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Network type textfield">
        networkType = new JTextField("2-4-1");
        networkType.setPreferredSize(size);
        networkType.setSize(size);
        networkType.setLocation(12,12);
        guiGroupLeft.add(networkType);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Set network button">
        setNetwork = new JButton("Set network");
        setNetwork.setPreferredSize(size);
        setNetwork.setSize(size);
        setNetwork.setLocation(size.width + 24, 12);
        setNetwork.addActionListener((e) -> {
            setNetwork();
        });
        guiGroupLeft.add(setNetwork);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Count textfield">
        count = new JTextField("500");
        count.setSize(size);
        count.setPreferredSize(size);
        count.setLocation(12, size.height + 24);
        guiGroupLeft.add(count);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Learn button">
        learn = new JButton("Learn");
        learn.setSize(size);
        learn.setPreferredSize(size);
        learn.setLocation(size.width + 24, size.height + 24);
        learn.addActionListener((e) -> doLearn());
        guiGroupLeft.add(learn);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Start button">
        start = new JButton("Start");
        start.setSize(size);
        start.setPreferredSize(size);
        start.setLocation(guiGroupRight.getWidth()-size.width-24, 12);
        start.addActionListener(e -> Logic.GetInstance().canRun = true);
        guiGroupRight.add(start);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Pause button">
        reset = new JButton("Reset");
        reset.setSize(size);
        reset.setPreferredSize(size);
        reset.setLocation(guiGroupRight.getWidth()-size.width*2-36, 12);
        reset.addActionListener(e -> {
            Logic.GetInstance().canRun = false;
            setNetwork();
        });
        guiGroupRight.add(reset);
        //</editor-fold>

    }

    private void setNetwork() {
        Controller.GetInstance().SetNetwork(networkType.getText());
    }

    private void doLearn() {
        Controller.GetInstance().amountOfRuns = Integer.parseInt(count.getText());
        Runnable t = Controller.GetInstance()::Learn;
        new Thread(t).start();
    }

    public Canvas canvas;
    private final JTextField networkType;
    private final JButton setNetwork;
    private final JTextField count;
    private final JButton learn;
    private final JButton start;
    private final JButton reset;
}
