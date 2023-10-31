package com.example;

public class Spawn extends Square {
    private Coordinates position;

    public Spawn() {
        super();
        this.setContent(Object.SPAWN);
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
        return "\uD83D\uDC23";
    }
}
