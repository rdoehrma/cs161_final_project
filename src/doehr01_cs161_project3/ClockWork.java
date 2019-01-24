/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doehr01_cs161_project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Timer;

/**
 *
 * Ryan Doehrmann
 * CS161
 * Fall 2017
 * Project 3
 * 
 */
public class ClockWork
{
    int longArmIndex;
    int shortArmIndex;
    int secondArmIndex;
    
    int radius = 300;
    int centerX = 350;
    int centerY = 350;
    
    int[] x = new int[60];
    int[] y = new int[60];
    int[] xx = new int[60];
    int[] yy = new int[60];
    int[] minuteArrayX = new int[60];
    int[] hourArrayX = new int[60];
    int[] minuteArrayY = new int[60];
    int[] hourArrayY = new int[60];
    
    int tickTime;
    
    Timer timer;
    ActionListener listener;
    ActionEvent event;
    
    public ClockWork()
    {
        event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "tick");
    }
    
    public void loadTacks()
    {
        for (int i = 0; i < 60; i++)
        {
            x[i] = (int) (centerX + radius*(Math.sin(2 * i * Math.PI / 60)));
            y[i] = (int) (centerX - radius*(Math.cos(2 * i * Math.PI / 60)));
            
            minuteArrayX[i] = (int) (centerX + (radius - 25)*(Math.sin(2 * i* Math.PI / 60)));
            minuteArrayY[i] = (int) (centerX - (radius - 25)*(Math.cos(2 * i * Math.PI / 60)));
            
            hourArrayX[i] = (int) (centerX + (radius - 70)*(Math.sin(2 * i* Math.PI / 60)));
            hourArrayY[i] = (int) (centerX - (radius - 70)*(Math.cos(2 * i * Math.PI / 60)));           
            
            if ((i % 5) == 0)
            {
                xx[i] = (int) (centerX + (radius+15)*(Math.sin(2 * i * Math.PI / 60)));
                yy[i] = (int) (centerX - (radius+15)*(Math.cos(2 * i * Math.PI / 60)));
            }
            else 
            {
                xx[i] = (int) (centerX + (radius+10)*(Math.sin(2 * i * Math.PI / 60)));
                yy[i] = (int) (centerX - (radius+10)*(Math.cos(2 * i * Math.PI / 60)));
            }
        }
    }
    
    public void makeTimer()
    {
        timer = new Timer(tickTime, new TimerListener());
        timer.start();
        connect();
    }
    
    public void stop()
    {
        timer.stop();
    }
    
    public void reset()
    {
        timer.stop();
        shortArmIndex = 0;
        longArmIndex = 0;
        secondArmIndex = 0;
        connect();
    }
    
    public boolean timeFormat(String inp)
    {
        if (inp.length() != 5)
        {
            return false;
        }
        else if (!(inp.substring(2,3).equals(":")))
        {
            return false;
        }
        int hourDigits = Integer.valueOf(inp.substring(0,2));
        int minuteDigits = Integer.valueOf(inp.substring(3,5));
        
        if ((hourDigits < 0) || (hourDigits > 23))
        {
            return false;
        }
        
        if ((minuteDigits < 0) || (minuteDigits > 59))
        {
            return false;
        }
                
        return true;
    }
    
    public void setClock(String timeToSet)
    {
        if (timeFormat(timeToSet) == false)
        {
            return;
        }
        else
        {
            longArmIndex = Integer.valueOf(timeToSet.substring(3,5));
            shortArmIndex = (((Integer.valueOf(timeToSet.substring(0,2))) % 12) * 5) +(int) (longArmIndex / 12);
            secondArmIndex = 0;
            connect();
        }
        
    }
    public void connect()
    {
        listener.actionPerformed(event);
    }
    
    public class TimerListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            secondArmIndex = ((secondArmIndex + 1) % 60);
            if (secondArmIndex == 0)
            {
                longArmIndex = ((longArmIndex + 1) % 60);
                if ((longArmIndex % 12) == 0)
                {
                    shortArmIndex = ((shortArmIndex + 1) % 60);
                }
            }
            connect();
        }
        
    }
    
    
    public void setTickTime(int tick)
    {
        tickTime = tick;
    }
    
    public void addActionListener(ActionListener watch)
    {
        listener = watch;
    }
    
    public int[] getX() { return Arrays.copyOf(x, x.length); }
    public int[] getY() { return Arrays.copyOf(y, y.length); }
    public int[] getXX() { return Arrays.copyOf(xx, xx.length); }
    public int[] getYY() { return Arrays.copyOf(yy, yy.length); }
    public int[] getMinX() { return Arrays.copyOf(minuteArrayX, minuteArrayX.length); }
    public int[] getMinY() { return Arrays.copyOf(minuteArrayY, minuteArrayY.length); }
    public int[] getHourX() { return Arrays.copyOf(hourArrayX, hourArrayX.length); }
    public int[] getHourY() { return Arrays.copyOf(hourArrayY, hourArrayY.length); }
    
    public void setLong(int longArm){ longArmIndex = longArm; }
    public void setShort(int shortArm) { shortArmIndex = shortArm; }
    public void setSecond(int secondArm) { secondArmIndex = secondArm; }
    
    public int getLong(){ return longArmIndex; }
    public int getShort(){ return shortArmIndex; }
    public int getSecond(){ return secondArmIndex; }
    
}
