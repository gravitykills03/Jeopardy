package com.jeopardy;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameBoard {
    private static final String singJeopFilePath = "lists/questions1.csv";
    private static final String doubJeopFilePath = "lists/questions2.csv";
    private Player player;
    Map<String, Question> singleJeopardyBoard;
    Map<String, Question> doubleJeopardyBoard;

    public GameBoard() {
        player = new Player();
        singleJeopardyBoard = new LinkedHashMap<>();
        doubleJeopardyBoard = new LinkedHashMap<>();
        displayTokens();
        setupData();
    }

    private void displayTokens() {
        try {
            List<String> lines = Files.readAllLines(Path.of(singJeopFilePath));
            SingleJeopardyDollar[] singleEnumValues = SingleJeopardyDollar.values();

            for(int i = 0; i < 6; ++i) {
                Question q = new Question();
                String line = lines.get(i);
                String[] tokens = line.split(",");

                q.setQuestion(tokens[0]);
                q.setAnswer(tokens[1]);
                q.setValue(Integer.parseInt(tokens[2]));

                String currEnum = singleEnumValues[i].toString();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupData() {
        try {
            List<String> lines = Files.readAllLines(Path.of(singJeopFilePath));
            SingleJeopardyDollar[] singleEnumValues = SingleJeopardyDollar.values();

            for(int i = 0; i < 6; ++i) {
                Question q = new Question();
                String line = lines.get(i);
                String[] tokens = line.split(",");

                q.setQuestion(tokens[0]);
                q.setAnswer(tokens[1]);
                q.setValue(Integer.parseInt(tokens[2]));

                String currEnum = singleEnumValues[i].toString();

                singleJeopardyBoard.put(currEnum, q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(doubJeopFilePath));
            DoubleJeopardyDollar[] doubleEnumValues = DoubleJeopardyDollar.values();

            for(int i = 0; i < 6; ++i) {
                Question q = new Question();
                String line = lines.get(i);
                String[] tokens = line.split(",");

                q.setQuestion(tokens[0]);
                q.setAnswer(tokens[1]);
                q.setValue(Integer.parseInt(tokens[2]));

                String currEnum = doubleEnumValues[i].toString();

                doubleJeopardyBoard.put(currEnum, q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displaySingleJeopardyBoard() {
        System.out.printf("%26s\t\t\t| %16s\n", "BATMAN", "STAR WARS");
        System.out.println("             ___________________________________________________");


        int questionCount = 1;
        for (Map.Entry<String, Question> value : singleJeopardyBoard.entrySet()) {
            Question tempQ = value.getValue();

            if (!tempQ.isAnswered()) {
                System.out.printf("%25d", tempQ.getValue());
            }
            else {
                System.out.printf("%25s", "   ");
            }

            if (questionCount == 2) {
                System.out.println();
                questionCount = 1;
            }
            else {
                ++questionCount;
            }
        }
    }


    public void displayDoubleJeopardyBoard() {
        System.out.printf("%33s\t\t\t\t| %20s\n", "THOR", "STAR TREK");
        System.out.println("\t\t\t\t\t\t___________________________________________________");


        int questionCount = 1;
        for (Map.Entry<String, Question> value : doubleJeopardyBoard.entrySet()) {
            Question tempQ = value.getValue();

            if (!tempQ.isAnswered()) {
                System.out.printf("%33d", tempQ.getValue());
            }
            else {
                System.out.printf("%33s", "   ");
            }

            if (questionCount == 2) {
                System.out.println();
                questionCount = 1;
            }
            else {
                ++questionCount;
            }
        }
    }

    public void promptForQuestion() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean validQuestion = false;
        boolean correctAnswer = false;
        String valueInput;
        String key;
        String categoryInput;
        String answer;
        Question questionRequested = null;

        while (!validQuestion) {
            categoryInput = prompter.prompt("Please enter the question category: ", "BATMAN|STAR WARS", "Invalid category. Please choose from the following (case sensitive): BATMAN, STAR WARS");
            valueInput = prompter.prompt("Please enter the question value: ", "100|200|300", "Invalid value.  Please enter one of the following: 100, 200, 300");

            key = categoryInput + "_" + valueInput;
            key = key.replace(" ", "_"); // BATMAN 100 -> BATMAN_100  || STAR WARS 100 -> STAR_WARS_100

            questionRequested = singleJeopardyBoard.get(key);


            if (!questionRequested.isAnswered()) {
                validQuestion = true;
            }
        }

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("Please input answer: ");

        correctAnswer = validateAnswer(answer, questionRequested);

        if (correctAnswer) {
            System.out.println("CORRECT ANSWER!!\tMoney Added: " + questionRequested.getValue());
            player.addMoney(questionRequested.getValue());
        }
        else {
            System.out.println("INCORRECT ANSWER!!\tMoney Taken: " + questionRequested.getValue());
            player.subtractMoney(questionRequested.getValue());
        }

        questionRequested.setAnswered(true);

        Console.pause(2000L);
    }

    public void promptForDJQuestion() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean validQuestion = false;
        boolean correctAnswer = false;
        String valueInput;
        String key;
        String categoryInput;
        String answer;
        Question questionRequested = null;

        while (!validQuestion) {
            categoryInput = prompter.prompt("Please enter the question category: ", "THOR|STAR TREK", "Invalid category. Please choose from the following (case sensitive): THOR, STAR TREK");
            valueInput = prompter.prompt("Please enter the question value: ", "200|400|600", "Invalid value.  Please enter one of the following: 200, 400, 600");

            key = categoryInput + "_" + valueInput;
            key = key.replace(" ", "_"); // BATMAN 100 -> BATMAN_100  || STAR WARS 100 -> STAR_WARS_100

            questionRequested = doubleJeopardyBoard.get(key);


            if (!questionRequested.isAnswered()) {
                validQuestion = true;
            }
        }

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("Please input answer: ");

        correctAnswer = validateAnswer(answer, questionRequested);

        if (correctAnswer) {
            System.out.println("CORRECT ANSWER!!\tMoney Added: " + questionRequested.getValue());
            player.addMoney(questionRequested.getValue());
        }
        else {
            System.out.println("INCORRECT ANSWER!!\tMoney Taken: " + questionRequested.getValue());
            player.subtractMoney(questionRequested.getValue());
        }

        questionRequested.setAnswered(true);

        Console.pause(2000L);
    }

    public void playerSetup(String name) {
        player.setName(name);
        player.setMoney(0);
    }

    public boolean isJeopardyComplete() {
        boolean result = true;

        for(Map.Entry<String, Question> value : singleJeopardyBoard.entrySet()) {
            if (value.getValue().isAnswered() == false) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean isDoubleJeopardyComplete() {
        boolean result = true;

        for(Map.Entry<String, Question> value : doubleJeopardyBoard.entrySet()) {
            if (value.getValue().isAnswered() == false) {
                result = false;
                break;
            }
        }

        return result;
    }

    public void displayResult() {
        System.out.println(player.getName() + " = " + player.getMoney());
    }

    private boolean validateAnswer(String answer, Question question) {
        return question.getAnswer().equalsIgnoreCase(answer);
    }

    // timer()

}
