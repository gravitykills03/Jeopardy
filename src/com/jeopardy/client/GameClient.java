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
        playSJ();
        playDJ();
        playFJ();
        displayResults();

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
        displayBanner("rules.txt");

        // Prompt for user name
    }

    public static void playSJ() {
        displayBanner("sj.txt");

        System.out.println("** BOARD TO BE DISPLAYED **");
        // display board
        // loop
        //     prompt for category
        //     prompt for value
        //     gameBoard methods

        displayBanner("sjComplete.txt");
        Console.pause(1500L);
    }

    public static void playDJ() {
        displayBanner("dj.txt");

        System.out.println("** BOARD TO BE DISPLAYED **");
        // display board
        // loop
        //     prompt for category
        //     prompt for value
        //     gameBoard methods

        displayBanner("djComplete.txt");
        Console.pause(1500L);

    }

    public static void playFJ() {
        displayBanner("fj.txt");

        // display question
        // prompt for answer

        Console.clear();
        // display answer and player question result

    }

    public static void displayResults() {
        displayBanner("gameOver.txt");

        // Player name with score
    }

    private static void displayBanner(String target) {
        String banner = null;

        try {
            banner = Files.readString(Path.of("images/" + target));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Console.clear();
        System.out.println(banner);
        Console.blankLines(3);
    }
}
