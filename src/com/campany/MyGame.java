package com.campany;

import com.campany.rules.Rule;

public class MyGame extends Game {
    private static MyGame MyGameINSTANCE = new MyGame();

    private MyGame() {
    }

    public static MyGame getINSTANCE() {
        return MyGameINSTANCE;
    }

    @Override
    public void play() {
        //init the game
        CreatePlayers();
        initGame();

        //while max score < 500
        while (true) {
            LastCardPlayed();
            ConfirmUser();
            playCards();
        }
    }
}


