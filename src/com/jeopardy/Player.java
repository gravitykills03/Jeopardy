package com.jeopardy;

public class Player {
    private String name;
    private int money;

    /**
     * No-Arg Constructor
     */
    public Player() {
    }

    /**
     * Two-Arg Constructor
     *
     * @param name - String: player name
     * @param money - int: player money value
     */
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    /**
     * Method to add money to its stored variable
     *
     * @param value - int: money to be added
     */
    public void addMoney(int value) {
        this.money += value;
    }

    /**
     * Method to take money from the stored variable
     *
     * @param value - int: money to be taken
     */
    public void subtractMoney(int value) {
       this.money -= value;
    }

    /**
     * Accessor Methods: retrieves the name of the player
     *
     * @return - String: player name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor Method: Sets the name of the player
     *
     * @param name - String: inputted player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor Method: Sets the money of the player
     *
     * @param money - int: player money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Accessor Method: Retrieves the money currenlty held by the player
     *
     * @return - int: player money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Overrided toString to return a string with relevant data about player.
     *
     * @return - String: Player name with score
     */
    @Override
    public String toString() {
        return "Player: " + name + "\tMoney: " + money;
    }
}
