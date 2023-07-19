package com.campany.cards;

import com.campany.MyGame;
import java.util.Scanner;

public abstract class Card {
    protected String color;
    protected static MyGame MyGameINSTANCE = MyGame.getINSTANCE();
    protected static Scanner input = new Scanner(System.in);

    Card(String color){
        this.color=color;
    }

    public abstract void CardJob();
    public abstract String CardName();

    public String getColor() {
        return color;
    }



}
