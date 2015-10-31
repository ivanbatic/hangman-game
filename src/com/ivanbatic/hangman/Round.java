package com.ivanbatic.hangman;

import java.util.Scanner;

class Round {

    protected String word;
    protected String explanation;

    private StringBuilder wordStatus = new StringBuilder();
    private int guessableCharactersCount;
    private int charactersToGuess;
    private int tries = 10;
    private boolean succeeded = false;

    public Round(String word, String explanation, int tries) {
        this.word = word;
        this.tries = tries;
        this.explanation = explanation;

        this.wordStatus.append(word.replaceAll("[^\\s-]", "*").toCharArray());
        this.charactersToGuess = word.replaceAll("[\\s-]", "").length();
        this.guessableCharactersCount = charactersToGuess;
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Word: %s\n", wordStatus.toString());
            System.out.print("Guess: ");
            String in = scanner.nextLine().toLowerCase();
            if (in.length() == 0) {
                System.out.println("Please type in something.");
                continue;
            }
            tries--;
            if (in.length() == 1) {
                unmaskLetter(in.charAt(0));
            } else {
                attemptSolution(in);
            }

            if (tries == Math.round(Math.sqrt(guessableCharactersCount))) {
                showHint();
            }

        } while (succeeded == false && tries > 0 && charactersToGuess > 0);
        if (succeeded) {
            System.out.println("You are doing great!");
        } else {
            System.out.println("Too bad. Try again.");
        }
    }

    private void attemptSolution(String solution) {
        if (solution.toLowerCase().equals(word)) {
            succeeded = true;
        }
    }

    private void unmaskLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                wordStatus.setCharAt(i, letter);
                charactersToGuess--;
            }
        }

        if (word.equals(wordStatus)) {
            succeeded = true;
        }

    }

    private void showHint() {
        System.out.printf("You seem to be in trouble. Here's a hint:\n\t%s\n", explanation);
    }
}
