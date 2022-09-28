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
        setupData();
    }

    private void setupData() {
        Random rand = new Random();
        int upperBound = 6;
        int dailyDoubleQuestion = rand.nextInt(upperBound);

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

                if (i == dailyDoubleQuestion) {
                    q.setDailyDouble(true);
                }

                String currEnum = singleEnumValues[i].toString();

                singleJeopardyBoard.put(currEnum, q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dailyDoubleQuestion = rand.nextInt(upperBound);

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

                if (i == dailyDoubleQuestion) {
                    q.setDailyDouble(true);
                }

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
        int questionValue;
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

        questionValue = (questionRequested.isDailyDouble()) ? dailyDoubleWager() : questionRequested.getValue();

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("Please input answer: ");

        validateAnswer(answer, questionRequested, questionValue);
        Console.pause(2000L);
    }

    public void promptForDJQuestion() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean validQuestion = false;
        boolean correctAnswer = false;
        int questionValue;
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

        questionValue = (questionRequested.isDailyDouble()) ? dailyDoubleWager() : questionRequested.getValue();

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("Please input answer: ");

        validateAnswer(answer, questionRequested, questionValue);
        Console.pause(2000L);
    }

    public void playerSetup(String name) {
        player.setName(name);
        player.setMoney(0);
    }

    public boolean isJeopardyComplete() {
        boolean result = true;

        for(Map.Entry<String, Question> value : singleJeopardyBoard.entrySet()) {
            if (!value.getValue().isAnswered()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean isDoubleJeopardyComplete() {
        boolean result = true;

        for(Map.Entry<String, Question> value : doubleJeopardyBoard.entrySet()) {
            if (!value.getValue().isAnswered()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public void displayResult() {
        System.out.println(player.getName() + " = " + player.getMoney());
    }

    public void promptForFinalJeopardyQuestion() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean validWager = false;
        boolean validAnswer = true;
        boolean isReady = false;
        String question = "What is the last name of this famous British actor who played Scotty in the new Star Trek reboot?";
        String answer = "Pegg";
        String readyInput;
        String finalQuestionPlayerAnswer;
        int wager = 0;

        // Player has score less than 100, exit Final Jeopardy to display Results
        if (player.getMoney() < 100) {
            System.out.println("You do not qualify for Final Jeopardy with a score less than 100.");
            return;
        }

        while (!isReady) {
            readyInput = prompter.prompt("Are you ready? (yes or no): ", "yes|no", "Invalid input. Please type yes or no");
            if (readyInput.equals("yes")) {
                isReady = true;
            } else {
                System.out.println("Please hurry. We do not have all day!");
                Console.pause(4000L);
            }
        }

        while (!validWager) {
            String wagerString = prompter.prompt("Enter your wager: ", "\\d{3,}", "Wager must be greater than 100.");

            wager = Integer.parseInt(wagerString);

            if (wager < 100 || wager > player.getMoney()) {
                System.out.println("The wager range is 100 to player max score: [100, " + player.getMoney() + "]!");
            } else {
                validWager = true;
            }
        }

        System.out.println();
        finalQuestionPlayerAnswer = prompter.prompt(question);

        validAnswer = finalQuestionPlayerAnswer.equalsIgnoreCase(answer);

        if (validAnswer) {
            System.out.printf("\nCORRECT ANSWER!!\t Answer = %s\nMoney Added: %d", answer, wager);
            player.addMoney(wager);
        }
        else {
            System.out.printf("\nINCORRECT ANSWER!!\t Answer = %s\nMoney Taken: %d", answer, wager);
            player.subtractMoney(wager);
        }
    }

    private void validateAnswer(String answer, Question question, int questionValue) {
        boolean result = question.getAnswer().equalsIgnoreCase(answer);

        if (result) {
            System.out.printf("CORRECT!!\tAnswer = %s\nMoney Added: %d\n", question.getAnswer(), questionValue);
            player.addMoney(questionValue);
        }
        else {
            System.out.printf("INCORRECT!!\tAnswer = %s\nMoney Taken: %d\n", question.getAnswer(), questionValue);
            player.subtractMoney(questionValue);
        }

        question.setAnswered(true);
    }

    private int dailyDoubleWager() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean validWager = false;
        int wager = 0;

        System.out.print("\n\n\nDAILY DOUBLE!!!\n");
        while (!validWager) {
            wager = Integer.parseInt(prompter.prompt("Enter your wager: "));

            if (player.getMoney() <= 0) {
                if (wager < 100 || wager > 600) {
                    System.out.println("The wager range is [100, 600] while in a non-positive balance!");
                }
                else {
                    validWager = true;
                }
            }
            else {
                if (wager < 100 || wager > player.getMoney()) {
                    System.out.println("The wager range is [100, " + player.getMoney() + "]!");
                }
                else {
                    validWager = true;
                }
            }
        }

        return wager;
    }
}
