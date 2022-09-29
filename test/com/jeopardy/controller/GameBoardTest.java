package com.jeopardy.controller;

import com.apps.util.Prompter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameBoardTest {
    private GameBoard gb;
    private Prompter prompter;

    @Before
    public void setUp() {
        gb = new GameBoard();
        try {
            prompter = new Prompter(new Scanner(new File("lists/testResponses.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isJeopardyComplete_shouldReturnFalse_ifJeopardyNotComplete() {
        assertFalse(gb.isDoubleJeopardyComplete());
    }

    @Test
    public void isDoubleJeopardyCompleteTest_shouldReturnFalse_ifJeopardyNotComplete() {
        assertFalse(gb.isDoubleJeopardyComplete());
    }

    @Test
    public void setupPlayer_shouldReturnGivenName_validNamePassed() {
        String playerName = prompter.prompt("Enter your name: ");
        gb.playerSetup(playerName);

        assertTrue("Player: Player Name\tMoney: 0".equalsIgnoreCase(gb.getPlayerInfo()));
    }
}