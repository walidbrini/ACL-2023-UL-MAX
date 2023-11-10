package com.example;

public class Aid extends Square{
    private static final int healthPoints = 20;

    public Aid() {
        super(ObjectType.AID, healthPoints, "/map/props/potion.png");
        collision=false; // à modifier (ajouter une action )
    }


    // FOR TESTING
    @Override
    public String toString() {
        return "a";
    }
}
