package com.jeopardy.client;

import com.apps.util.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GameClient {
    public static void main(String[] args) {
        // setup()
        welcomeScreen();
        playerSetup();
        // playSJ()
        // playDJ()
        // playFJ()
        // displayResults()

    }

    private void setup() {
        // new gameboard
        // populate gameboard with questions
        // maybe in main?
    }

    public static void welcomeScreen() {
        String welcome = null;
        String credits = null;

        try {
            welcome = Files.readString(Path.of("images/welcome.txt"));
            credits = Files.readString(Path.of("images/credits.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Console.clear();
        System.out.println(welcome);

        Console.pause(1500L);
        Console.clear();

        System.out.println(credits);

        Console.pause(1500L);
        Console.clear();
    }

    public static void playerSetup() {
        String rules = null;

        try {
            rules = Files.readString(Path.of("images/rules.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Console.clear();
        System.out.println(rules);
        Console.pause(1500L);

        // Prompt for user name
    }

    private void playSJ() {
        // SJ banner
        // display board
        // loop
        //     prompt for category
        //     prompt for value
        //     gameBoard methods
        // End of SJ banner

    }

    private void playDJ() {
        // DJ banner
        // display board
        // loop
        //     prompt for category
        //     prompt for value
        //     gameBoard methods
        // End of DJ banner

    }

    private void playFJ() {
        // FJ banner
        // display question
        // prompt for answer
        // display answer and player question result

    }

    private void displayResults() {
        // End of game banner
        // Player name with score

    }
}
