package com.campany.cards;

public class ReverseCard extends Card{

    public ReverseCard(String color) {
        super(color);
    }

    @Override
    public void CardJob() {
        MyGameINSTANCE.ReverseTurn();
        MyGameINSTANCE.setGameColor(this.color);
        MyGameINSTANCE.nextTurn();
    }

    public String CardName(){
        return "R"+color.charAt(0);
    }
}



