package com.jeopardy.controller;

import com.jeopardy.Player;
import com.jeopardy.Question;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GameBoardTest {
    private static final String singJeopFilePath = "lists/questions1.csv";
    private static final String doubJeopFilePath = "lists/questions2.csv";
    private Player player;

    Map<String, Question> singleJeopardyBoard;
    Map<String, Question> doubleJeopardyBoard;


    @Test
    public void isDoubleJeopardyCompleteTest_shouldReturnFalse_ifJeopardyNotComplete() {
        GameBoard gameBoard = new GameBoard();
        System.out.println(gameBoard.isDoubleJeopardyComplete());
        assertFalse("false", Boolean.parseBoolean(String.valueOf(gameBoard.isDoubleJeopardyComplete())));
    }

    @Test
    public void promptForQuestionTest() {
    }

    @Test
    public void promptForDJQuestion() {
    }

    @Test
    public void playerSetup() {
    }

    @Test
    public void isJeopardyComplete() {
    }

    @Test
    public void isDoubleJeopardyComplete() {
    }

    @Test
    public void displayResult() {
    }

    @Test
    public void promptForFinalJeopardyQuestion() {
    }
}