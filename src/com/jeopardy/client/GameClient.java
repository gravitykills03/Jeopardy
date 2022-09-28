package com.jeopardy.client;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.jeopardy.GameBoard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class GameClient {
    public static void main(String[] args) {

        GameBoard gb = new GameBoard();
        welcomeScreen();
        playerSetup(gb);
        playSJ(gb);
        playDJ(gb);
        playFJ(gb);
        displayResults(gb);
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

        Console.pause(2500L);
        Console.clear();
    }

    public static void playerSetup(GameBoard gameBoard) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        displayBanner("rules.txt");

        String name = prompter.prompt("Enter player name: ");
        gameBoard.playerSetup(name);
    }

    public static void playSJ(GameBoard gameBoard) {
        while(!gameBoard.isJeopardyComplete()) {
            displayBanner("sj.txt");

            gameBoard.displaySingleJeopardyBoard();
            System.out.println();
            System.out.println("SCORE:");
            System.out.print("\t");
            gameBoard.displayResult();
            System.out.print("\n\n");

            gameBoard.promptForQuestion();
            Console.clear();
        }

        displayBanner("sjComplete.txt");
        Console.pause(2500L);
    }

    public static void playDJ(GameBoard gameBoard) {
        while(!gameBoard.isDoubleJeopardyComplete()) {
            displayBanner("dj.txt");

            gameBoard.displayDoubleJeopardyBoard();
            System.out.println();
            System.out.println("\t\tSCORE:");
            System.out.print("\t\t\t");
            gameBoard.displayResult();
            System.out.print("\n\n");

            gameBoard.promptForDJQuestion();
            Console.clear();
        }

        displayBanner("djComplete.txt");
        Console.pause(2500L);

    }

    public static void playFJ(GameBoard gb) {
        displayBanner("fj.txt");
        System.out.println();
        System.out.println("\t\tSCORE:");
        System.out.print("\t\t\t");
        gb.displayResult();
        System.out.print("\n\n");

        gb.promptForFinalJeopardyQuestion();

        Console.pause(2500L);
        Console.clear();
    }

    public static void displayResults(GameBoard gb) {
        displayBanner("gameOver.txt");
        System.out.println();
        System.out.println("FINAL SCORE!!");
        gb.displayResult();
        System.out.printf("\nTHANK YOU FOR PLAYING!!");
        System.out.printf("\n\nCopyright 2022 - Trebek's Legacy LLC");
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
