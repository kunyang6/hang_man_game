/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kunyang6.hangman;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author vince
 */
public class Word implements Comparable<Word>{
    private final String word;
    private final int length;
    private Difficulty difficulty;
    private StringBuilder alteredWord;

    public Word(String word)
    {
        this.word = word.trim();
        this.length = word.length();
    }

    public String getAlteredWord() {return this.alteredWord.toString();}

    public String getWord(){return this.word;}

    public int getLength(){return this.length;}

    public Difficulty getDifficulty(){return this.difficulty;}

    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty;}

    /**
     * This method will randomly replace the letters with under scores.
     * @return void
     */
    public void replaceLettersWithUnderScores() {
        LinkedHashSet<Integer> usedNumbers = new LinkedHashSet<>();
        double sqrtOfWordLength = Math.sqrt(this.length);
        int amountOfHelperLetters =  (int)(Math.ceil(sqrtOfWordLength));
        while(amountOfHelperLetters > 0) {
            int helperLetterIndex =  (int)(Math.random()*this.length);
            if(!usedNumbers.contains(helperLetterIndex)) {
                usedNumbers.add(helperLetterIndex);
                amountOfHelperLetters--;
            }
        }

        this.alteredWord = new StringBuilder("");
        for(int i = 0; i < this.length; i++) {
            if(usedNumbers.contains(i)) {
                this.alteredWord.append(this.word.charAt(i));
            } else {
                this.alteredWord.append("_");
            }
        }
    }
    /**
     * This method will return a boolean value based on the input from the user.
     * @param guessLetter
     * @return
     */
    public boolean guess(char guessLetter) {
        boolean hasGuessedRight = false;
        StringBuilder newString = new StringBuilder("");
        for(int i = 0; i < this.alteredWord.length(); i++) {
            if(this.word.charAt(i) == guessLetter) {
                newString.append(guessLetter);
                hasGuessedRight = true;
            } else {
                newString.append(this.getAlteredWord().charAt(i));
            }
        }
        this.alteredWord = newString;
        displayAlteredWord();
        return hasGuessedRight;
    }

    public void displayAlteredWord() {
        for(int i = 0; i < this.getAlteredWord().length(); i++) {
            System.out.print(this.getAlteredWord().charAt(i) + " ");
        }
        System.out.println();
    }

    @Override
    public int compareTo(Word word)
    {
        return this.length - word.length;
    }

//program debugger
    public static void main(String[] args) {
        Word word = new Word("department");
        word.replaceLettersWithUnderScores();
        word.displayAlteredWord();
        if(word.guess('d')) {
            System.out.println("guessed right");
        }
    }
}
