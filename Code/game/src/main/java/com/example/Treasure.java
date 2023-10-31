package com.example;

public class Treasure extends Square {
    private Coordinates position;

    public Treasure() {
        super();
        this.setContent(Object.TREASURE);
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Coordinates getPosition() {
        return position;
    }

    // FOR TESTING
    @Override
    public String toString() {
        return "\uD83D\uDC8E";
    }
}

