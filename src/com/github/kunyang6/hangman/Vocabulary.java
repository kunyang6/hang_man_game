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
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author vince
 */
public class Vocabulary {
    private ArrayList<Word> vocabulary = new ArrayList<>();
    private String filePath;
    
    public Vocabulary(String filePath)
    {
        this.filePath = filePath;
    }
    
    public void buildVocabulary() throws FileNotFoundException
    {
        File file = new File(this.filePath);
        if(!file.exists())
        {
            throw new FileNotFoundException(String.format("The File %s can not be found",this.filePath));
        }
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\n");
        while(scanner.hasNext())
        {
            Word word = new Word(scanner.next());
            vocabulary.add(word);
        }
        Collections.sort(vocabulary);
    }
    
    
    
    public static void main(String[] args)
    {
        Vocabulary something = new Vocabulary("C:\\Users\\vince\\Desktop\\SideProjects\\HangManGame\\lib\\usa.txt");
        try{
            something.buildVocabulary();
        } catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
