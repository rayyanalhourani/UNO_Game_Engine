package com.campany.rules;

import com.campany.cards.Card;

public class DrawRule implements Rule {
    @Override
    public boolean Validate(Card card) {
        //just draw 1 card
        MyGameINSTANCE.getPlayers().get(MyGameINSTANCE.getPlayerTurn()).AddToPlayerCards(card);
        return true;
    }
}