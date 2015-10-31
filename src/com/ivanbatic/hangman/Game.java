package com.ivanbatic.hangman;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private HashMap wordlist;


    public Game(HashMap wordlist) {
        this.wordlist = wordlist;
    }

    public void start() {
        System.out.println("Welcome to the Hangman Game.");
        System.out.println("When trying to guess a word, try to reveal a few characters first.");
        System.out.println("If you think that you know the solution, just type it in.\nLet's go!\n");


        do {
            String word = nextWord(4);

            Round round = new Round(word.toLowerCase(), (String) wordlist.get(word), 20);
            round.start();
            System.out.println("Solution: " + round.word + " - " + round.explanation);

        } while (shouldGameContinue());
    }

    public boolean shouldGameContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wanna continue playing (y/n)? ");
        String line = scanner.nextLine();
        return line.startsWith("y");
    }

    public String nextWord(int minLength) {

        int size = wordlist.size();
        Random random = new Random();
        String key = "";
        do {
            key = (String) wordlist.keySet().toArray()[random.nextInt(size)];
        } while (key.length() < minLength);
        return key;
    }


}
