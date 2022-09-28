package com.jeopardy.client;

import com.jeopardy.controller.GameController;

class GameClient {
    public static void main(String[] args) {

        // Create new gameController
        GameController gameController = new GameController();

        // run game
        gameController.run();
    }
}
