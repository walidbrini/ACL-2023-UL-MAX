package com.example;

public class Mana extends Square{
    public Mana() {
        super(ObjectType.MANA, 0,"/map/props/mana.png");
        collision = false;
    }



    // FOR TESTING
    @Override
    public String toString() {
        return "m";
    }
}
