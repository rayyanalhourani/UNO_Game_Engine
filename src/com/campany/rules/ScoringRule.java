package com.campany.rules;

import com.campany.Player;
import com.campany.cards.Card;

public class ScoringRule implements Rule{
    @Override
    public boolean Validate(Card card) {
        String firstChar =card.CardName().charAt(0)+"";
        Player player=MyGameINSTANCE.getPlayers().get(MyGameINSTANCE.getPlayerTurn());
        if(firstChar.equals("W")){
            player.setScore(player.getScore()+50);
        }
        else if(firstChar.equals("S")||firstChar.equals("R")){
            player.setScore(player.getScore()+20);
        }
        else{
            player.setScore(player.getScore()+Integer.valueOf(card.CardName().charAt(0)+""));
        }
        return true;
    }
}
