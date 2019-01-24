/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doehr01_cs161_project3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/**
 *
 * Ryan Doehrmann
 * CS161
 * Fall 2017
 * Project 3
 * 
 */
public class FaceGUI extends JFrame implements ActionListener
{
    ClockWork work;
    Face face;
    
    JPanel layout = new JPanel(new BorderLayout());
    
    int radius = 300;
    int centerX = 350;
    int centerY = 350;
    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    
    public FaceGUI(ClockWork cw)
    {
        super("The Clock");
        work = cw;
        work.addActionListener(this);
        buildClock();
    }
    
    public void buildClock()
    {
        Container pane = getContentPane();
        
        work.loadTacks();
        face = new Face();
        face.setVisible(true);
        layout.setBackground(Color.RED);
        
        layout.add(face, BorderLayout.CENTER);
        pane.add(layout);
        setSize(700,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        face.repaint();
    }
    
    public class Face extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            int secondTemp = work.getSecond();
            int shortTemp = work.getShort();
            int longTemp = work.getLong();
            
            int innerX[] = work.getX();
            int innerY[] = work.getY();
            int outerX[] = work.getXX();
            int outerY[] = work.getYY();
            int minX[] = work.getMinX();
            int minY[] = work.getMinY();
            int hourX[] = work.getHourX();
            int hourY[] = work.getHourY();
            
            g.setColor(Color.WHITE);
            g.fillOval(innerX[45], innerY[0], 2*radius, 2*radius);
            
            g.setColor(Color.CYAN);
            g.fillOval(innerX[45] + 19, innerY[0] + 19, 2*(radius - 19), 2*(radius - 19));
            
            g.setColor(Color.BLACK);
            g.fillOval(345,345,10, 10);
            
            g.setColor(Color.BLACK);
            for (int i = 0; i < 60; i++)
            {
                g.drawLine(innerX[i], innerY[i], outerX[i], outerY[i]);
            }
            g.drawLine(350, 350, innerX[secondTemp], innerY[secondTemp]);
            g.setColor(Color.RED);
            g.drawLine(350, 350, minX[longTemp], minY[longTemp]);
            g.setColor(Color.BLUE);
            g.drawLine(350, 350, hourX[shortTemp], hourY[shortTemp]);
            g.setFont(font);
            g.drawString("12", outerX[0]-10, outerY[0] - 10);
            g.drawString("1", outerX[5], outerY[5] - 5);
            g.drawString("2", outerX[10]+5, outerY[10]);
            g.drawString("3", outerX[15]+5, outerY[15]+5);
            g.drawString("4", outerX[20] + 5, outerY[20]+10);
            g.drawString("5", outerX[25] + 5, outerY[25] + 15);
            g.drawString("6", outerX[30] - 5, outerY[30] + 20);
            g.drawString("7", outerX[35] - 8, outerY[35] + 20);
            g.drawString("8", outerX[40] - 15, outerY[40] + 15);
            g.drawString("9", outerX[45] - 20, outerY[45] + 5);
            g.drawString("10", outerX[50] - 25, outerY[50]);
            g.drawString("11", outerX[55] - 20, outerY[55]-3);
            
        }
    }
            
    
    
}
