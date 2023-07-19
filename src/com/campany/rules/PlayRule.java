package com.campany.rules;

import com.campany.cards.Card;

public class PlayRule implements Rule {

    @Override
    public boolean Validate(Card card) {
        Card LastCardPlayed = MyGameINSTANCE.getCardsDeck().GetTopOfDiscardPile();
        String firstChar = card.CardName().charAt(0)+"";
        String firstCharOfLastCard = LastCardPlayed.CardName().charAt(0)+"";
        if(firstChar.equals("W") || firstChar.equals(firstCharOfLastCard) || card.getColor().equals(MyGameINSTANCE.getGameColor())){
            return true;
        }
        return false;
    }
}
