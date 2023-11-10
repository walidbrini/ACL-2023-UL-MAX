package com.example;

public class Fire extends Square{
    private static final int damage = 10;

    public Fire() {
        
        super(ObjectType.FIRE, -damage, "/map/props/fire.png");
        collision=false; // à modifier (ajouter une action )
    }
    


    // FOR TESTING
    @Override
    public String toString() {
        return "x";
    }
}
