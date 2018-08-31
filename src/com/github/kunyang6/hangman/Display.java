/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import javax.swing.JPanel;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author vince
 */
public class Display extends JFrame {
    private DrawListener drawListener;
    
    public Display(DrawListener drawListener, int width, int height)
    {
        this.drawListener = drawListener;
        setTitle("Hangman Game");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void requestRepaint()
    {
        SwingUtilities.invokeLater(this::onRepaintRequested);
    }
    
    public void onRepaintRequested()
    {
        repaint();
    }
    
    public void paint(Graphics graphics) 
    {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        drawListener.paint(bufferedImage.getGraphics());
        graphics.drawImage(bufferedImage, 0, 0, null);
    }
    
    public int getOffsetY()
    {
        return getInsets().top;
    }
    
    public int getOffsetX()
    {
        return getInsets().left;
    }
    
    public int getDrawingWidth()
    {
        return getWidth() - getInsets().right - getInsets().left;
    }
    
    public int getDrawingHeight()
    {
        return getHeight() - getInsets().bottom - getInsets().top;
    }
}
