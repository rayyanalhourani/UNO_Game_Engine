package com.campany.cards;

public class SkipCard extends Card{

    public SkipCard(String color) {
        super(color);
    }

    @Override
    public void CardJob() {
        MyGameINSTANCE.setGameColor(this.color);
        MyGameINSTANCE.nextTurn();
        MyGameINSTANCE.nextTurn();
    }
    public String CardName(){
        return "S"+color.charAt(0);
    }

}
