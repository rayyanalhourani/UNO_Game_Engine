package com.campany.rules;

import com.campany.MyGame;
import com.campany.cards.Card;

public interface Rule {
    MyGame MyGameINSTANCE = MyGame.getINSTANCE();

    public boolean Validate(Card card);

}
