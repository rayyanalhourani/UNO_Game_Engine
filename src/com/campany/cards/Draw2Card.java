package com.campany.cards;

public class Draw2Card extends Card{

    public Draw2Card(String color) {
        super(color);
    }

    @Override
    public void CardJob() {
        MyGameINSTANCE.nextTurn();
        MyGameINSTANCE.DrawCards(2);
        MyGameINSTANCE.setGameColor(this.color);
    }
    public  String CardName(){
        return "D"+color.charAt(0);
    }


}
