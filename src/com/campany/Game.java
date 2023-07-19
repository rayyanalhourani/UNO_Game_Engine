package com.campany;

import com.campany.cards.Card;
import com.campany.rules.DrawRule;
import com.campany.rules.PlayRule;
import com.campany.rules.Rule;
import com.campany.rules.ScoringRule;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {
    protected static int PlayerTurn=0;
    protected static CardsDeck cardsDeck = CardsDeck.getINSTANCE();
    protected static ArrayList<Player> Players = new ArrayList<>();
    protected static String GameColor;
    protected static boolean Reversed=false;
    private Rule PlayRule = new PlayRule();
    private Rule DrawRule = new DrawRule();
    private Rule ScoringRule = new ScoringRule();

    Scanner Charinput = new Scanner(System.in);
    Scanner Numbersinput = new Scanner(System.in);

    public abstract void play();

    public static void setCardsDeck(CardsDeck cardsDeck) {
        Game.cardsDeck = cardsDeck;
    }

    public static void setReversed(boolean reversed) {
        Reversed = reversed;
    }

    public int getPlayerTurn() {
        return PlayerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        PlayerTurn = playerTurn;
    }

    public String getGameColor() {
        return GameColor;
    }

    public void setGameColor(String gameColor) {
        GameColor = gameColor;
    }

    public int getNumberOfPlayers(){
        return Players.size()-1;
    }

    public  boolean isReversed() {
        return Reversed;
    }

    public void ReverseTurn(){
        Reversed=!Reversed;
    }

    public void CreatePlayers() {
        System.out.println("How many players you need?");
        while (true) {
            int numOfPlayers = Numbersinput.nextInt();
            if (numOfPlayers > 1 || numOfPlayers <= 10) {
                AddPlayers(numOfPlayers);
                break;
            } else {
                System.out.println("Enter valid number");
            }
        }
    }

    public void AddPlayers(int NumOfPlayers){
        //create the init thrower
        Players.add(new Player("init"));

        //other players
        for (int i=1;i<=NumOfPlayers;i++){
            System.out.println("Enter The name of Player "+i);
            Players.add(new Player(Charinput.nextLine()));
        }
    }



    public void DistributeCards(){
        for(int i=1;i<Players.size();i++){
            for(int NumOfCards=0;NumOfCards<7;NumOfCards++){
                Players.get(i).AddToPlayerCards(cardsDeck.DrawFromDeck());
            }
        }
        Players.get(0).AddToPlayerCards(cardsDeck.DrawFromDeck());
    }

    public int sizeofdeck(){
        return cardsDeck.GetNumberOfCardsInDeck();
    }

    public void playCard(int Playerid , Card card){
        Players.get(Playerid).RemoveFromPlayerCards(card);
        cardsDeck.AddtoDiscardPile(card);
        card.CardJob();
    }

    public CardsDeck getCardsDeck() {
        return cardsDeck;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void DrawCards(int numbersOfCards){
        //make the currunt player draw cards
        while(numbersOfCards>0 && cardsDeck.GetNumberOfCardsInDeck()>0){
            Players.get(getPlayerTurn()).AddToPlayerCards(cardsDeck.DrawFromDeck());
            numbersOfCards--;
        }

        if(cardsDeck.GetNumberOfCardsInDeck()==0){
            CardsDeck.getINSTANCE().ReFilldrawPile();
            DrawCards(numbersOfCards);
        }
    }

    public void nextTurn(){
        //move turn for next player
        if(!isReversed()){
            int turn=getPlayerTurn()+1;
            if(turn==getNumberOfPlayers()+1){
                setPlayerTurn(1);
            }
            else PlayerTurn=turn;
        }
        else{
            int turn =PlayerTurn-1;
            if(turn==0 || turn==-1){
                PlayerTurn = getNumberOfPlayers();
            }
            else PlayerTurn= turn;
        }
    }

    public void initGame(){
        //crate basic deck , Distribe cards and drop the start card
        CardsDeck.getINSTANCE().CreateBasicCards();
        DistributeCards();
        while ((cardsDeck.GetTopOfDeck().CardName().charAt(0)+"").equals("W")){
            cardsDeck.ReShuffle();
        }
        playCard(0, cardsDeck.GetTopOfDeck());
        GameColor=cardsDeck.GetTopOfDiscardPile().getColor();
    }

    public void LastCardPlayed(){
        System.out.println("---------------------------------------------");
        System.out.print("Last Card Played :");
        System.out.println(cardsDeck.GetTopOfDiscardPile().CardName());
    }

    public void ConfirmUser(){
        //to don't make other user see the player cards
        System.out.print("if you are "+Players.get(getPlayerTurn()).getName()+" press any thing to continue");
        Charinput.nextLine();
    }

    public void setPlayRule(Rule playRule) {
        PlayRule = playRule;
    }

    public void setDrawRule(Rule drawRule) {
        DrawRule = drawRule;
    }

    public void setScoringRule(Rule scoringRule) {
        ScoringRule = scoringRule;
    }

    public void DrawWhenNoCardsToPlay(){
        System.out.println("you don't have cards to play,you will draw a card");
        Card card = cardsDeck.DrawFromDeck();
        while (true) {
            if (PlayRule.Validate(card)) {
                System.out.println("the card played");
                getPlayers().get(PlayerTurn).RemoveFromPlayerCards(card);
                cardsDeck.AddtoDiscardPile(card);
                SayUNO();
                card.CardJob();
            } else {
                System.out.println("no cards to play");
                Players.get(PlayerTurn).AddToPlayerCards(card);
                nextTurn();
            }

            if(DrawRule.Validate(card)){
                break;
            }
        }
    }

    public void playCards(){

        //get all currunt player cards
        ArrayList<Card> playerCards =Players.get(PlayerTurn).getPlayerCards();
        ArrayList<Card> playableCards = new ArrayList<>();
        //but all playable cards in arrayList
        for (int i=0;i<playerCards.size();i++){
            if(PlayRule.Validate(playerCards.get(i))){
                playableCards.add(playerCards.get(i));
            }
        }

        //print the cards
        System.out.println("Unplayable cards");
        for (int i =0;i<playerCards.size();i++){
            if(!playableCards.contains(playerCards.get(i))){
                System.out.print(playerCards.get(i).CardName()+" ");
            }
        }
        System.out.println("");
        if(playableCards.size()!=0) {
            System.out.println("Playable Cards");
            for (int i = 0; i < playableCards.size(); i++) {
                System.out.print(playableCards.get(i).CardName() + " ");
            }
            System.out.println("");
            DropCard(playableCards);
        }
        else {
            //if user dont have cards to play
            DrawWhenNoCardsToPlay();
        }
    }

    public void DropCard(ArrayList<Card> playableCards) {
        //let player choose card to play
        System.out.println("Choose a card number between 1 and "+playableCards.size());
        while (true) {
            int choosenCard = Numbersinput.nextInt();
            if (choosenCard>0 && choosenCard<=playableCards.size()){
                Card card = playableCards.get(choosenCard-1);
                Players.get(PlayerTurn).RemoveFromPlayerCards(card);
                //check if player have 1 card or not to say UNO
                SayUNO();
                //check if player finish his all cards
                FinishRound();
                card.CardJob();
                cardsDeck.AddtoDiscardPile(card);
                break;
            }
            else {
                System.out.println("Wrong number");
            }
        }
    }
    public void SayUNO(){
        //user choose say uno or not , if no he draw a card
        if(Players.get(PlayerTurn).getPlayerCards().size()==1) {
            System.out.println("do you want sau UNO");
            System.out.println("press 1 for YES and 2 for NO");
            while (true) {
                int res = Numbersinput.nextInt();
                if (res == 1) {
                    break;
                } else if (res == 2) {
                    Players.get(PlayerTurn).AddToPlayerCards(cardsDeck.DrawFromDeck());
                    break;
                } else {
                    System.out.println("wrong number");
                }
            }
        }
    }

    public void FinishRound(){
        //if one of players drop all his cards
        if (Players.get(PlayerTurn).getPlayerCards().size()==0){
            System.out.println("Round ended");
            CountScore();
            PrintScore();
            NewRound();
        }
    }

    public void CountScore(){
        for (int i=1;i<=getNumberOfPlayers();i++){
            if (i!=PlayerTurn){
                ArrayList<Card> playerCards = Players.get(i).getPlayerCards();
                for (int j=0;j<playerCards.size();j++){
                    ScoringRule.Validate(playerCards.get(j));
                }
            }
        }
    }

    public void PrintScore(){
        for (int i=1;i<=getNumberOfPlayers();i++){
            System.out.print("player "+i + " score : ");
            System.out.println(Players.get(i).getScore());
        }
    }

    public void NewRound(){
        if(maxScore()<500){
            System.out.println("-----------------------------------------------");
            System.out.println("new round start");
            for (int i=0;i<Players.size();i++){
                Players.get(i).resetCards();
            }
            cardsDeck.resetDecks();
            initGame();
        }
        else{
            System.out.print("the winner is ");
            for (int i=1;i<Players.size();i++) {
                if (Players.get(i).getScore() >= 500) {
                    System.out.println(Players.get(i).getName());
                }
            }
            System.exit(0);
        }
    }

    public int maxScore(){
        int max=0;
        for (int i=0;i<Players.size();i++){
            if(Players.get(i).getScore()>max){
                max=Players.get(i).getScore();
            }
        }
        return max;
    }
}
