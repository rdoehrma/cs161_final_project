/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doehr01_cs161_project3;

/**
 *
 * Ryan Doehrmann
 * CS161
 * Fall 2017
 * Project 3
 * 
 */
public class Test
{
    public static void main(String[] args) 
    {
        // TODO code application logic here
        ClockWork work = new ClockWork();
        ControlDevices device =  new ControlDevices(work);
        FaceGUI face = new FaceGUI(work);
    }
    
}
