package com.campany.cards;

public class NumberedCard extends Card{

    private int number;
    public NumberedCard(String color,int number) {
        super(color);
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void CardJob() {
        MyGameINSTANCE.setGameColor(this.color);
        MyGameINSTANCE.nextTurn();
    }

    public String CardName(){
        return ""+this.number+color.charAt(0);
    }
}
