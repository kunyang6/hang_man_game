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
import javax.swing.JPanel;
import java.util.List;
import java.util.LinkedList;
import javax.swing.SwingUtilities;

/**
 *
 * @author vince
 */
public class Display extends Container{
    private List<Widget> widgets;
    
    public Display()
    {
        widgets = new LinkedList<Widget>();
    }
    
    @Override
    public void paint(Graphics graphics){
        int width = 200;
        int height = 200;
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.drawRect(0, 0, width, height);
        drawWidgets(bufferGraphics);
        graphics.drawImage(buffer, 0, 0, null);
    }
    
    public void clearScene()
    {
        widgets.clear();
    }
    
    public void addWidget(Widget widget)
    {
        widgets.add(widget);
    }
    
    public void requestRepaint()
    {
        Container self = this;
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                self.repaint();
            }
        });
    }
    
    private void drawWidgets(Graphics graphics)
    {
        for (Widget widget : widgets)
        {
            widget.onDraw(graphics);
        }
    }
    
    
}
