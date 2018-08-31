/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.io.File;
import java.io.FileNotFoundException;
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
    private String filePath;
    private HashMap<String, Word> easyLevel = new HashMap<String, Word>();
    private HashMap<String, Word> mediumLevel = new HashMap<String, Word>();
    private HashMap<String, Word> hardLevel = new HashMap<String, Word>();
    
    public Vocabulary(String filePath) {
        this.filePath = filePath;
    }
    
    public void buildVocabulary() throws FileNotFoundException {
        File file = new File(this.filePath);
        if(!file.exists()) {
            throw new FileNotFoundException(String.format("The File %s can not be found",this.filePath));
        }
        Scanner scanner = new Scanner(file);
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
    
    private void assignDifficultyHelper(int start, int end, Difficulty difficulty) {
        for (int i = start; i < end; i++) {
            Word word = this.vocabulary.get(i);
            word.setDifficulty(difficulty);
            if(difficulty.equals(Difficulty.EASY)) {
                easyLevel.put(word.getWord(), word);
            } else if (difficulty.equals(Difficulty.MEDIUM)){
                mediumLevel.put(word.getWord(), word);
            } else {
                hardLevel.put(word.getWord(), word);
            }
        }
    }
    
    public HashMap<String,Word> getEasyLevel() {return this.easyLevel;}
    
    public String getFilePath() {return this.filePath;}
    
    public HashMap<String,Word> getHardLevel() {return this.hardLevel;}
    
    public HashMap<String,Word> getMediumLevel() {return this.mediumLevel;}
    
    public ArrayList<Word> getVocabulary(){return this.vocabulary;}
    
    
    public static void main(String[] args) {
        Vocabulary demo = new Vocabulary("C:\\Users\\vince\\Desktop\\SideProjects\\HangManGame\\lib\\usa.txt");
        try{
            demo.buildVocabulary();
//            HashMap<String,Word> easyLevel = demo.getEasyLevel();
//            Iterator iterator = easyLevel.entrySet().iterator();
//            System.out.println("-----------------start of easy level-------------------------");
//            while(iterator.hasNext()) {
//                Map.Entry<String,Word> pair = (Map.Entry) iterator.next();
//                Word word = pair.getValue();
//                System.out.println(word.getWord());
//                System.out.println(word.getDifficulty().toString());
//            }
//            System.out.println("---------------end of easy level----------------------------");
//            
//            HashMap<String,Word> mediumLevel = demo.getMediumLevel();
//            Iterator iteratorMedium = mediumLevel.entrySet().iterator();
//            System.out.println("----------------start of medium level------------------------");
//            while(iteratorMedium.hasNext()) {
//                Map.Entry<String,Word> pair = (Map.Entry) iteratorMedium.next();
//                Word word = pair.getValue();
//                System.out.println(word.getWord());
//                System.out.println(word.getDifficulty().toString());
//            }
//            System.out.println("------------end of medium level-----------------------");
//            
            HashMap<String,Word> hardLevel = demo.getHardLevel();
            System.out.println("-----------------start of hard level------------------");
            Iterator iteratorHard = hardLevel.entrySet().iterator();
            while(iteratorHard.hasNext()) {
                Map.Entry<String,Word> pair = (Map.Entry) iteratorHard.next();
                Word word = pair.getValue();
                System.out.println(word.getWord());
                System.out.println(word.getDifficulty().toString());
            }
            System.out.println("-------------end of hard level-----------------");
        } catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
