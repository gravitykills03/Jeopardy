package com.jeopardy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class GameBoard {
    private static final String singJeopFilePath = "lists/questions1.csv";
    private static final String doubJeopFilePath = "lists/questions2.csv";
    private static final String resultsFilePath = "data/board.dat";
    private Player player;
    List<List<Question>> singleJeopardyBoardGrid;
    List<List<Question>> doubleJeopardyBoardGrid;

    public GameBoard() {
        player = new Player();
        setupData();
    }

    private void setupData() {
        try {
            List<String> lines = Files.readAllLines(Path.of(singJeopFilePath));
            int currLine = 0;

            for (int i = 0; i < 3; ++i) {
                List<Question> sameValue = null;

                for (int j = 0; j < 2; ++j) {
                    String line = lines.get(currLine);
                    String[] tokens = line.split(",");
                    Question q = new Question(tokens[1], tokens[2], tokens[0], Integer.parseInt(tokens[3]));
                    sameValue.add(q);
                }

                singleJeopardyBoardGrid.add(sameValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(doubJeopFilePath));
            int currLine = 0;

            for (int i = 0; i < 3; ++i) {
                List<Question> sameValue = null;

                for (int j = 0; j < 2; ++j) {
                    String line = lines.get(currLine);
                    String[] tokens = line.split(",");
                    Question q = new Question(tokens[1], tokens[2], tokens[0], Integer.parseInt(tokens[3]));
                    sameValue.add(q);
                }

                doubleJeopardyBoardGrid.add(sameValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBoard(String name, int total) {
        Integer player = null;
        if (playerMap.containsKey(name)) {
            player = playerMap.get(name);

            // Add or subtract money based on answer result
            player.addMoney(money);
        } else {
            player = new Player(name, playerMap.get(total));
            playerMap.put(name, total);
        }
        player.add(reward);
        save();
    }

    public void displaySingleJeopardyBoard() {
        // display categories and values with proper spacing
        // check isAnswered boolean if its answered display bank
        // iterate through their respective grids to display
    }

    public void displayDoubleJeopardyBoard() {

    }

    public void getQuestion(){

    }

    // displayResult()
    // promptForQuestion()
    // getScore()
    // timer()
    // getQuestion()

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(resultsFilePath))) {
            out.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
