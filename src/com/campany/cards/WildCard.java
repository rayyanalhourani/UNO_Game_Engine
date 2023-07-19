package com.campany.cards;

public class WildCard extends Wild {

    public WildCard(String color) {
        super(color);
    }

    @Override
    public void CardJob() {
        ChooseAColor();
        MyGameINSTANCE.nextTurn();
    }

    public String CardName(){
        return "WC";
    }
}
