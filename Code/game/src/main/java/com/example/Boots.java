package com.example;

public class Boots extends Square{
    public Boots() {
        super(ObjectType.BOOTS,"/items/boots.png");
        collision = false;
    }



    // FOR TESTING
    @Override
    public String toString() {
        return "b";
    }
}
