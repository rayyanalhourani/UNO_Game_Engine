package com.campany.cards;

public abstract class Wild extends Card{


    Wild(String color) {
        super(color);
    }

    public static void ChooseAColor(){
        //choose color when play wild card
        System.out.println("Choose a color");
        System.out.println("1-Red 2-Green 3-Blue 4-Yellow");
        int ChoosenColor = input.nextInt();
        if(ChoosenColor==1){
            MyGameINSTANCE.setGameColor("Red");
        }
        else if (ChoosenColor==2){
            MyGameINSTANCE.setGameColor("Green");
        }
        else if (ChoosenColor==3){
            MyGameINSTANCE.setGameColor("Blue");
        }
        else if (ChoosenColor==4){
            MyGameINSTANCE.setGameColor("Yellow");
        }
    }
}
