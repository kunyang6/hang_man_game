/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author vince
 */
public class Vocabulary {
    private ArrayList<Word> vocabulary = new ArrayList<>();
    private ArrayList<Word> easyLevel = new ArrayList<Word>();
    private ArrayList<Word> mediumLevel = new ArrayList<Word>();
    private ArrayList<Word> hardLevel = new ArrayList<Word>();
    
    public Vocabulary() throws IOException {
        this.buildVocabulary();
    }
    
    public void buildVocabulary() throws IOException {
        InputStream stream = Vocabulary.class.getResourceAsStream("WordBank.txt");
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\n");
        while(scanner.hasNext()) {
            Word word = new Word(scanner.next());
            this.vocabulary.add(word);
        }
        Collections.sort(this.vocabulary);
        assignDifficulty();
    }
    
    /**
     * This method can only be used, when the library is being built.
     */
    private void assignDifficulty() {
        final int LEVEL_DIVISOR = 3;
        int levelSection = this.vocabulary.size() / LEVEL_DIVISOR;
        int start = 0;
        int endOfEasyLevel = levelSection;
        int endOfMediumLevel = endOfEasyLevel + levelSection;
        int endOfVocabulary  = this.vocabulary.size();
        
        //assign easy difficulty
        assignDifficultyHelper(start, endOfEasyLevel, Difficulty.EASY);
        
        
        //assign medium difficulty
        assignDifficultyHelper(endOfEasyLevel, endOfMediumLevel, Difficulty.MEDIUM);
        
        //assign hard difficulty
        assignDifficultyHelper(endOfMediumLevel, endOfVocabulary, Difficulty.HARD);
    }
    
    /**
     * 
     * @param start: This is the start of either Easy, Medium, Hard Arraylist.
     * @param end: This is the end of either Easy, Medium, Hard Arraylist.
     * @param difficulty: This is the control of choosing the words' difficulty.
     */
    private void assignDifficultyHelper(int start, int end, Difficulty difficulty) {
        for (int i = start; i < end; i++) {
            Word word = this.vocabulary.get(i);
            word.setDifficulty(difficulty);
            if(difficulty.equals(Difficulty.EASY)) {
                easyLevel.add(word);
            } else if (difficulty.equals(Difficulty.MEDIUM)){
                mediumLevel.add(word);
            } else {
                hardLevel.add(word);
            }
        }
    }
    
    public Word selectWord(Difficulty difficulty) {
        int randomNumber;
        Word word;
        switch(difficulty) {
            case MEDIUM:
                randomNumber = (int)Math.random() * this.mediumLevel.size();
                word = this.mediumLevel.get(randomNumber);
                return word;
            case EASY:
                randomNumber = (int)Math.random() * this.easyLevel.size();
                word = this.easyLevel.get(randomNumber);
                return word;
            case HARD:
                randomNumber = (int)Math.random() * this.hardLevel.size();
                word = this.hardLevel.get(randomNumber);
                return word;
        }
        throw new IllegalArgumentException("Invalid difficulty");
    }
    
    public ArrayList<Word> getEasyLevel() {return this.easyLevel;}
    
    public ArrayList<Word> getHardLevel() {return this.hardLevel;}
    
    public ArrayList<Word> getMediumLevel() {return this.mediumLevel;}
    
    public ArrayList<Word> getVocabulary(){return this.vocabulary;}
    
    
    public static void main(String[] args) {
        ArrayList<Word> easyLevel = new ArrayList<>();
        try{
            Vocabulary demo = new Vocabulary();
            easyLevel = demo.getHardLevel();
            for(Word word : easyLevel) {
                System.out.println(word.getWord());
                System.out.println(word.getDifficulty().toString());
            }
        } catch(IOException ex)
        {
            ex.printStackTrace();
        } finally {
            easyLevel.clear();
        }
    }
}
