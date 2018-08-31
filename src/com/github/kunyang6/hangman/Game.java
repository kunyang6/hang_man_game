/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;

/**
 *
 * @author vince
 */
public class Game implements DrawListener {
    public static void main(String[]args) throws InterruptedException
    {
        Game game = new Game();
        game.display = new Display(game, 1280, 720);
        while (true)
        {
            game.display.requestRepaint();
            Thread.sleep(50);
        }
    }
    
    private String word = "coffee";
    private boolean[] wordFound;
    
    protected Display display;
    
    private Font titleFont;
    
    public Game()
    {
        titleFont  = new Font("Arial", Font.PLAIN, 30);
    }
    
    public void paint(Graphics graphics) {
         graphics.setColor(Color.BLACK);
        graphics.fillRect(display.getOffsetX(), display.getOffsetY(), display.getDrawingWidth(), display.getDrawingHeight());
        graphics.setColor(Color.WHITE);
        graphics.setFont(titleFont);
        
        
        
        for(int i = 0; i < word.length(); i++) {
            graphics.drawString(word.substring(i, i+1), display.getOffsetX() + i*30, display.getOffsetY() + 30);
        }
    }
    
}
