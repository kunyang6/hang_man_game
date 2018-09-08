/*
 * This program is the first prototype of hangman game. Program has yet to developed a full on graphic user interface.
 * V1.0 hangman, 2018/9/8
 */
package com.github.kunyang6.hangman;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vince
 */
public class HangMan {
    private VocabularyInterface wordBank;
    private int tries = 5;
    private Difficulty level;
    private Word word;
    private Difficulty difficulty;
    private int attempts;
    
    public HangMan() throws IOException {
        this.wordBank = new VocabularyInterface();
        this.attempts = 5;
    }

    /**
     * This method sets the word of the game based on the elected difficulty.
     * This method should not be used if the word bank is empty.
     * @param level
     * @throws IllegalArgumentException
     */
    public void setLevel(String level) throws IllegalArgumentException {
        level = level.toLowerCase();

        final String EASY = "easy";
        final String MEDIUM = "medium";
        final String HARD = "hard";

        if(level.equals(EASY)) {
            this.difficulty = Difficulty.EASY;
            this.word = this.wordBank.selectWord(difficulty);
        } else if(level.equals(MEDIUM)) {
            this.difficulty = Difficulty.MEDIUM;
            this.word = this.wordBank.selectWord(difficulty);
        } else if(level.equals(HARD)) {
            this.difficulty = Difficulty.HARD;
            this.word = this.wordBank.selectWord(difficulty);
        } else {
            throw new IllegalArgumentException("Difficulty not found");
        }
        
    }

    public Word getWord() {return this.word;}
    
    /**
     * The method is the start method of the hangman game. Method will utilize helper methods <logicOfHangMan(Scanner sc)> and <gameResult()>.
     */
    public void start() {
        System.out.println("-----------Game Has Started-----------");
        this.word.replaceLettersWithUnderScores();
        this.word.displayAlteredWord();
        Scanner sc = new Scanner(System.in);
        this.logicOfHangMan(sc);
        this.gameResult();
        System.out.println("----------Game Has Ended----------");
    }
    
    /**
     * This method will acquire user's input, which has 2 functionalities.
     * First option allows the user to guess the word. Based on the result of the user's input, global variable <this.attempts> will be either incrementing
     * or decrementing.
     * Second option allows the user to guess individual characters that existing in the word. Global variable <this.attempts> increments or decrements 
     * according to the accuracy of the guessing.
     * @param sc: Scanner for user inputs.
     */
    private void logicOfHangMan(Scanner sc) {
         while(this.attempts != 0) {
            System.out.println(String.format("Can you guess the word(type \"NO\" to continue)? Attempts: %d", this.attempts));
            String guessedWord = sc.nextLine().trim().toLowerCase();
            if(this.word.getWord().equals(guessedWord)){
                break;
            } else if(!guessedWord.equals("no") && !guessedWord.equals(this.word.getWord())) {
                System.out.println(String.format("The answer is not %s", guessedWord));
                this.attempts--;
            } else if(guessedWord.equals("no")) {
                String guessedLetter = guessingLetter(sc);
                if(!this.word.guess(guessedLetter.charAt(0))) {
                    this.attempts--;
                }
            } else {
                System.out.println("Invalid Input... Continue");
            }
        }
    }
    
    /**
     * < GameResult() > displays the status of the game result. (win or lose)
     */
    private void gameResult() {
        if(this.attempts == 0) {
            System.out.println("OUT OF ATTEMPTS, YOU FAILED");
        } else {
            System.out.println("Winner Winer Chicken Dinner");
        }
    }
    
    /**
     * < guessingLetter > utilizes user's input to return the guessing value. 
     * Method will be able to check user's ability to input correct guessing value.
     * @param sc: scanner obtians the input value.
     * @return guessedLetter: storage for user input.
     */
    private String guessingLetter(Scanner sc) {
        String guessedLetter = "";
        do {
            System.out.println("Guess a letter");
            guessedLetter = sc.nextLine().trim().toLowerCase();
            if(guessedLetter.length() > 1 && Character.isLetter(guessedLetter.charAt(0))) {
                System.out.println("Invalid input, guess again");
            }
        } while(guessedLetter.length() > 1);
        return guessedLetter;
    }
    
    public static void main(String[] args) {
        try {
            HangMan game = new HangMan();
            String level = "hard";
            game.setLevel(level);
            Word word = game.getWord();
            System.out.println("The word of level " + level + " is " + word.getWord());
            game.start(); 
        } catch (IOException ex) {
            Logger.getLogger(HangMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
