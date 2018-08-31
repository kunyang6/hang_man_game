/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author vince
 */
public class HangMan {
    private Vocabulary wordBank;
    private int tries = 5;
    private Difficulty level;
    private Word word;
    private Difficulty difficulty;
    
    public HangMan() throws IOException {
        this.wordBank = new Vocabulary();
    }
    
    /**
     * $ = php love.
     * This method is a throw back to php, #IKE.
     * This method sets the word of the game based on the elected difficulty.
     * @param $level
     * @throws Exception 
     */
    public void setLevel(String $level) throws Exception {
        $level = $level.toLowerCase();
        
        final String EASY = "easy";
        final String MEDIUM = "medium";
        final String HARD = "hard";
        
        if($level.equals(EASY)) {
            this.difficulty = Difficulty.EASY;
            this.word = this.wordBank.selectWord(difficulty);
        } else if($level.equals(MEDIUM)) {
            this.difficulty = Difficulty.MEDIUM;
            this.word = this.wordBank.selectWord(difficulty);
        } else if($level.equals(HARD)) {
            this.difficulty = Difficulty.HARD;
            this.word = this.wordBank.selectWord(difficulty);
        } else {
            throw new Exception("Difficulty not found");
        }
    }
    
    
}
