package com.campany;

import com.campany.cards.Card;
import java.util.ArrayList;

public class Player {
    private int id;
    private String name;
    private static int counter=0;
    private int score;
    private ArrayList<Card> PlayerCards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.id=counter;
        counter++;
        this.score=0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Card> getPlayerCards() {
        return PlayerCards;
    }

    public void AddToPlayerCards(Card card){
        PlayerCards.add(card);
    }

    public void RemoveFromPlayerCards(Card card) {
        PlayerCards.remove(card);
    }
    public void resetCards(){
        PlayerCards.clear();
    }
}

