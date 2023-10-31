package com.example;

public class Fire extends Square{
    private static final int damage = 10;

    public Fire() {
        super();
        this.setContent(Object.FIRE);
        this.setEffectOnHealthPoints(-damage);
    }


    // FOR TESTING
    @Override
    public String toString() {
        return "x";
    }
}
