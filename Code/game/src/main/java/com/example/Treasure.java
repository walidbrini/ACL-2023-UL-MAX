package com.example;

public class Treasure extends Square {
    private Coordinates position;

    public Treasure() {
        super(ObjectType.TREASURE, "/map/props/chest.png");
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
        return "T";
    }
}

