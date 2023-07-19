package com.campany.cards;

public class Wild4Card extends Wild{

    public Wild4Card(String color) {
        super(color);
    }

    @Override
    public void CardJob() {
        ChooseAColor();
        MyGameINSTANCE.nextTurn();
        MyGameINSTANCE.DrawCards(4);
    }

    public String CardName(){
        return "W4";
    }
}
