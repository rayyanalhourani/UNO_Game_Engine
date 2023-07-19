package com.campany;

import com.campany.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class CardsDeck {
    private static CardsDeck INSTANCE = new CardsDeck();
    private static Stack<Card> drawPile  = new Stack<>();
    private static Stack<Card> discardPile = new Stack<>();
    private static String colors[] = {"Red","Green","Blue","Yellow","Black"};

    private CardsDeck(){}

    public static CardsDeck getINSTANCE(){
        return INSTANCE;
    }

    public void CreateBasicCards(){
        ArrayList<Card> SortedDeck= new ArrayList<>();
        for(int i=0;i<100;i++){
            SortedDeck.add(new NumberedCard(colors[0],9));
        }


        //shuffle the deck
        Collections.shuffle(SortedDeck);
        this.drawPile.addAll(SortedDeck);
    }

    public Stack<Card> getCardsDeck() {
        return drawPile;
    }

    public void ReFilldrawPile(){
        ArrayList<Card> Deck= new ArrayList(discardPile);
        discardPile.clear();
        //shuffle the deck
        Collections.shuffle(Deck);
        this.drawPile.addAll(Deck);
    }

    public int GetNumberOfCardsInDeck(){
        return drawPile.size();
    }

    public Card GetTopOfDeck(){
        return drawPile.lastElement();
    }

    public Card GetTopOfDiscardPile(){
        return discardPile.lastElement();
    }

    public Card DrawFromDeck(){
        return drawPile.pop();
    }

    public void AddtoCardDeck(Card card){
        drawPile.add(card);
    }

    public void AddtoDiscardPile(Card card){
        discardPile.add(card);
    }

    public void ReShuffle(){
        ArrayList<Card> Pile = new ArrayList<>(drawPile);
        Collections.shuffle(Pile);
        this.drawPile.addAll(Pile);
    }

    public void resetDecks(){
        discardPile.clear();
        drawPile.clear();
    }
}
