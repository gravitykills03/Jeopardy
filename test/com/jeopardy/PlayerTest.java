package com.jeopardy;

import static org.junit.Assert.*;

public class PlayerTest {
    public static void main(String[] args) {
        Player player = new Player("Tish",600);

        player.addMoney(100);
        System.out.println(player);

        System.out.println();

        player.subtractMoney(200);
        System.out.println(player);
    }

}