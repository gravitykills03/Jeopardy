package com.jeopardy.controller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.jeopardy.DoubleJeopardyDollar;
import com.jeopardy.Player;
import com.jeopardy.Question;
import com.jeopardy.SingleJeopardyDollar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class GameBoard {
    private static final String SINGLE_JEOPARDY_FILE_PATH = "lists/questions1.csv";
    private static final String DOUBLE_JEOPARDY_FILE_PATH = "lists/questions2.csv";
    private Player player;
    private Prompter prompter;
    private Map<String, Question> singleJeopardyBoard;
    private Map<String, Question> doubleJeopardyBoard;

    /**
     * GameBoard constructor
     * Initializes player, singleJeopardyBoard, and doubleJeopardyBoard
     * Calls helper method setupData() for populating data
     */
    public GameBoard() {
        player = new Player();
        prompter = new Prompter(new Scanner(System.in));
        singleJeopardyBoard = new LinkedHashMap<>();
        doubleJeopardyBoard = new LinkedHashMap<>();
        setupData();
    }

    /**
     * Helper method to assist the constructor in filling the Map<> variables with data
     */
    private void setupData() {
        Random rand = new Random();
        int upperBound = 6;
        int dailyDoubleQuestion = rand.nextInt(upperBound);

        try {
            List<String> lines = Files.readAllLines(Path.of(SINGLE_JEOPARDY_FILE_PATH));
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
            List<String> lines = Files.readAllLines(Path.of(DOUBLE_JEOPARDY_FILE_PATH));
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

    /**
     * Helper method to validate an answer inputted by player
     * Inputted answer and actual answer are compared with .equalsIgnoreCase()
     *
     * @param answer - String for user inputted answer
     * @param question - Question object that holds relevant question
     * @param questionValue - int for the score value of the question
     */
    private void validateAnswer(String answer, Question question, int questionValue) {
        boolean result = question.getAnswer().equalsIgnoreCase(answer);

        if (result) {
            System.out.printf("CORRECT!!\tAnswer = %s\nMoney Added: %d\n", question.getAnswer(), questionValue);
            System.out.println();
            player.addMoney(questionValue);
        }
        else {
            System.out.printf("INCORRECT!!\tAnswer = %s\nMoney Taken: %d\n", question.getAnswer(), questionValue);
            System.out.println();
            player.subtractMoney(questionValue);
        }

        question.setAnswered(true);
    }

    /**
     * Helper method called when the question object is flagged as a DailyDouble.
     * This method prompts the user for a specified wager adhering to Daily Double rules.
     *
     * @return - int - wager specified by player
     */
    private int dailyDoubleWager() {
        boolean validWager = false;
        int wager = 0;

        System.out.print("\n\n\nDAILY DOUBLE!!!\n");
        while (!validWager) {
            String wagerString = prompter.prompt("Enter your wager: ", "\\d{3,}", "Wager must be at least 3 digits (i.e: 100)\n");

            wager = Integer.parseInt(wagerString);

            if (player.getMoney() <= 0) {
                if (wager < 100 || wager > 600) {
                    System.out.println("The wager range is [100, 600] while in a non-positive balance!\n");
                }
                else {
                    validWager = true;
                }
            }
            else {
                if (wager < 100 || wager > player.getMoney()) {
                    System.out.println("The wager range is [100, " + player.getMoney() + "]!\n");
                }
                else {
                    validWager = true;
                }
            }
        }

        return wager;
    }

    /**
     * Method to fill the player variable with necessary data.
     *
     * @param name - String - name given by user
     */
    public void playerSetup(String name) {
        player.setName(name);
        player.setMoney(0);
    }

    /**
     * Displays the relevant jeopardy board to console.
     * Displays the categories followed by question values in a grid format
     */
    public void displaySingleJeopardyBoard() {
        System.out.printf("%26s\t  | %17s\n", "BATMAN", "STAR WARS");
        System.out.println("\t\t______________________________________");


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

    /**
     * Displays the relevant jeopardy board to console.
     * Displays the categories followed by question values in a grid format
     */
    public void displayDoubleJeopardyBoard() {
        System.out.printf("%33s\t  | %16s\n", "THOR", "STAR TREK");
        System.out.println("\t\t\t_________________________________________");


        int questionCount = 1;
        for (Map.Entry<String, Question> value : doubleJeopardyBoard.entrySet()) {
            Question tempQ = value.getValue();

            if (!tempQ.isAnswered()) {
                // if second question, reduce spacing for display
                if (questionCount == 2) {
                    System.out.printf("%25d", tempQ.getValue());
                } else {
                    System.out.printf("%33d", tempQ.getValue());
                }
            }
            else {
                // if second question, reduce spacing for display
                if (questionCount == 2) {
                    System.out.printf("%25s", "   ");
                } else {
                    System.out.printf("%33s", "   ");
                }
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

    /**
     * Method that prompts the player to enter the necessary information to retrieve the relevant
     * question object.
     * When question object is retrieved, the question is displayed and the player is prompted to answer.
     * After an answer is given, the method calls necessary helper methods to validate answer and update the player
     * score appropriately
     */
    public void promptForQuestion() {
        boolean validQuestion = false;
        boolean correctAnswer = false;
        int questionValue;
        String valueInput;
        String key;
        String categoryInput;
        String answer;
        Question questionRequested = null;

        while (!validQuestion) {
            categoryInput = prompter.prompt("Please enter the question category: ", "(?i)BATMAN|STAR WARS", "Invalid category. Please choose from the following: BATMAN, STAR WARS\n");
            valueInput = prompter.prompt("Please enter the question value: ", "100|200|300", "Invalid value.  Please enter one of the following: 100, 200, 300\n");

            key = categoryInput.toUpperCase() + "_" + valueInput.toUpperCase();
            key = key.replace(" ", "_"); // BATMAN 100 -> BATMAN_100  || STAR WARS 100 -> STAR_WARS_100

            questionRequested = singleJeopardyBoard.get(key);

            if (!questionRequested.isAnswered()) {
                validQuestion = true;
            }
        }

        questionValue = (questionRequested.isDailyDouble()) ? dailyDoubleWager() : questionRequested.getValue();

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("\nPlease input answer: ");

        validateAnswer(answer, questionRequested, questionValue);
        Console.pause(2000L);
    }

    /**
     * Method that prompts the player to enter the necessary information to retrieve the relevant
     * question object.
     * When question object is retrieved, the question is displayed and the player is prompted to answer.
     * After an answer is given, the method calls necessary helper methods to validate answer and update the player
     * score appropriately
     */
    public void promptForDoubleJeopardyQuestion() {
        boolean validQuestion = false;
        boolean correctAnswer = false;
        int questionValue;
        String valueInput;
        String key;
        String categoryInput;
        String answer;
        Question questionRequested = null;

        while (!validQuestion) {
            categoryInput = prompter.prompt("Please enter the question category: ", "(?i)THOR|STAR TREK", "Invalid category. Please choose from the following: THOR, STAR TREK\n");
            valueInput = prompter.prompt("Please enter the question value: ", "200|400|600", "Invalid value.  Please enter one of the following: 200, 400, 600\n");

            key = categoryInput.toUpperCase() + "_" + valueInput.toUpperCase();
            key = key.replace(" ", "_"); // BATMAN 100 -> BATMAN_100  || STAR WARS 100 -> STAR_WARS_100

            questionRequested = doubleJeopardyBoard.get(key);

            if (!questionRequested.isAnswered()) {
                validQuestion = true;
            }
        }

        questionValue = (questionRequested.isDailyDouble()) ? dailyDoubleWager() : questionRequested.getValue();

        System.out.printf("\n\n");
        System.out.println(questionRequested.getQuestion());
        answer = prompter.prompt("\nPlease input answer: ");

        validateAnswer(answer, questionRequested, questionValue);
        Console.pause(2000L);
    }

    /**
     * Method to run final jeopardy round.
     * Method determines if player is elgible for round (score > 100) and if so prompts for a valid wager.
     * Method then displays the final question of the game and prompts for answer.
     * After an answer is given, the method calls necessary helper methods to validate answer and update the player
     * score appropriately
     */
    public void promptForFinalJeopardyQuestion() {
        boolean validWager = false;
        boolean validAnswer = true;
        boolean isReady = false;
        String question = "What is the last name of this famous British actor who played Scotty in the new Star Trek reboot?: ";
        String answer = "Pegg";
        String readyInput;
        String finalQuestionPlayerAnswer;
        int wager = 0;

        // Player has score less than 100, exit Final Jeopardy to display Results
        if (player.getMoney() < 100) {
            System.out.println("You do not qualify for Final Jeopardy with a score less than 100.\n");
            return;
        }

        while (!isReady) {
            readyInput = prompter.prompt("Are you ready? (yes or no): ", "(?i)yes|no", "Invalid input. Please enter one of the following: Yes, No\n");
            if (readyInput.equalsIgnoreCase("yes")) {
                isReady = true;
            } else {
                System.out.println("Please hurry. We do not have all day!");
                Console.pause(4000L);
            }
        }

        while (!validWager) {
            String wagerString = prompter.prompt("Enter your wager: ", "\\d{3,}", "Wager must be at least 3 digits (i.e: 100)\n");

            wager = Integer.parseInt(wagerString);

            if (wager < 100 || wager > player.getMoney()) {
                System.out.println("The wager range is 100 to player max score: [100, " + player.getMoney() + "]!\n");
            } else {
                validWager = true;
            }
        }

        System.out.println();
        finalQuestionPlayerAnswer = prompter.prompt(question);

        validAnswer = finalQuestionPlayerAnswer.equalsIgnoreCase(answer);

        if (validAnswer) {
            System.out.printf("\nCORRECT ANSWER!!\t Answer = %s\nMoney Added: %d", answer, wager);
            System.out.println();
            player.addMoney(wager);
        }
        else {
            System.out.printf("\nINCORRECT ANSWER!!\t Answer = %s\nMoney Taken: %d", answer, wager);
            System.out.println();
            player.subtractMoney(wager);
        }
    }

    /**
     * Method to determine when the relevant jeopardy round is complete.
     * If a question object is not answered, returns false.
     *
     * @return - boolean on if the round is complete
     */
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

    /**
     * Method to determine when the relevant jeopardy round is complete.
     * If a question object is not answered, returns false.
     *
     * @return - boolean on if the round is complete
     */
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

    /**
     * Method to display the player information
     */
    public void displayPlayerWithScore() {
        System.out.println(player);
    }
}
