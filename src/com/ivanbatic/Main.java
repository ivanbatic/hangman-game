package com.ivanbatic;

import com.ivanbatic.hangman.Game;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader reader = new FileReader("data/english-dictionary.json");
        Object parsed = JSONValue.parse(reader);
        JSONObject dictionary = (JSONObject) parsed;

        Game game = new Game(dictionary);
        game.start();
    }
}
