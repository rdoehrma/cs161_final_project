/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doehr01_cs161_project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * Ryan Doehrmann
 * CS161
 * Fall 2017
 * Project 3
 * 
 */
public class ControlDevices extends JFrame implements ActionListener, ChangeListener
{
    ClockWork work;
    JPanel layout;
    JPanel eastPanel;
    JPanel centerPanel;
    JPanel centerGrid;
    
    JButton run = new JButton("Run the Clock");
    JButton stop = new JButton("Stop the Clock");
    JButton reset = new JButton("Reset the Clock");
    JButton setClock = new JButton("Set the Clock");
    
    JLabel runLabel = new JLabel("Clcik to start the clock");
    JLabel stopLabel = new JLabel("Click to stop the clock");
    JLabel resetLabel = new JLabel("Click to reset the clock");
    JLabel slideLabel = new JLabel("Select tick time");
    JLabel formatLabel = new JLabel("Enter time, use format 15:42");
    JTextField timeText = new JTextField(5);
    
    JSlider slider = new JSlider(JSlider.VERTICAL, 0, 1000, 800);
    
    public ControlDevices(ClockWork cw)
    {
        super("Control of ClockWork");
        work = cw;
        makeSlider();
        buildControlGUI();
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void makeSlider()
    {
        slider.setSize(125,300);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.CYAN);
        slider.addChangeListener(this);
    }
    
    public void buildControlGUI()
    {
        Container pane = getContentPane();
        layout = new JPanel(new BorderLayout(10,10));    
        
        eastPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(0,2,2,50));
        
        run.addActionListener(this);
        stop.addActionListener(this);
        reset.addActionListener(this);
        setClock.addActionListener(this);
        
        centerPanel.add(run);
        centerPanel.add(runLabel);
        centerPanel.add(stop);
        centerPanel.add(stopLabel);
        centerPanel.add(reset);
        centerPanel.add(resetLabel);
        centerPanel.add(formatLabel);
        centerPanel.add(timeText);
        centerPanel.add(setClock);
        
        eastPanel.add(slider, BorderLayout.WEST);
        eastPanel.add(slideLabel, BorderLayout.CENTER);
        
        layout.add(centerPanel, BorderLayout.CENTER);
        layout.add(eastPanel, BorderLayout.EAST);
        
        pane.add(layout);
        
    }
    
    public void stateChanged(ChangeEvent event)
    {
        work.setTickTime(slider.getValue());

    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == stop)
        {
            run.setEnabled(true);
            stop.setEnabled(false);
            work.stop();
        }
        else if (event.getSource() == run)
        {
            stop.setEnabled(true);
            run.setEnabled(false);
            work.makeTimer();
        }
        else if (event.getSource() == reset)
        {
            run.setEnabled(true);
            work.reset();
        }
        else if (event.getSource() == setClock)
        {
            work.setClock(timeText.getText());
            work.setTickTime(slider.getValue());
            timeText.setText("");
        }
    }
}
