/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author vince
 */
public class Game {
    public static void main(String[] args)
    {
        Game game = new Game();
        game.initWindow();
    }
    
    private JFrame frame;
    private Display display;
    
    public Game()
    {
        
    }
    
    public void initWindow()
    {
        frame = new JFrame("Vinnie's Hangman");
        display = new Display();
        //display.setPreferredSize(new Dimension(640, 480));
        display.setSize(640, 480);
        frame.getContentPane().add(display);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();
    }
}
