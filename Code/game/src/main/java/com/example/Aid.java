package com.example;

public class Aid extends Square{
    private static final int healthPoints = 20;

    public Aid() {
        super();
        this.setContent(Object.AID);
        this.setEffectOnHealthPoints(healthPoints);
    }


    // FOR TESTING
    @Override
    public String toString() {
        return "a";
    }
}
