package com.example;

public class Aid extends Square{
    public static final int healthPoints = 4;

    public Aid() {
        super(ObjectType.AID, healthPoints, "/map/props/potion.png");
        collision = false; // à modifier (ajouter une action )
    }

    // FOR TESTING
    @Override
    public String toString() {
        return "a";
    }
}
