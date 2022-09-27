package com.jeopardy;

public class Player {
    private String name;
    private int money;

    public Player() {
    }

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void addMoney(int value) {
        this.money += value;
    }

    public void subtractMoney(int value) {
       this.money -= value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Player name: " + name +
                ", money: " + money;
    }
}
