/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

/**
 *
 * @author vince
 */
public class Word implements Comparable<Word>{
    private String word;
    private int length;
    private Difficulty difficulty;
    
    public Word(String word)
    {
        this.word = word;
        this.length = word.length();
    }
    
    public String getWord(){return this.word;}
    
    public int getLength(){return this.length;}
    
    public Difficulty getDifficulty(){return this.difficulty;}
    
    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty;}
    
    @Override
    public int compareTo(Word word)
    {
        return this.length - word.length;
    }
}
