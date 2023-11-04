package com.example;

public class Walkway extends Square{
    public Walkway() {
        super(ObjectType.WALKWAY, "/map/tiles/floor.png");
    }


    // FOR TESTING
    @Override
    public String toString() {
        return " ";
    }
}
