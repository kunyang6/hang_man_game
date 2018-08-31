/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.awt.Graphics;

/**
 *
 * @author vince
 */
public abstract class Widget{
    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;
    
    public Widget(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
    
    public abstract void onDraw(Graphics graphics);
    
    public abstract void onClick();
    
    
}
