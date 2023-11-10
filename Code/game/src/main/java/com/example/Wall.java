package com.example;

public class Wall extends Square{
    public Wall() {
        
        super(ObjectType.WALL, true, "/map/tiles/brickwall.png");
        collision=true;
    }

    // FOR TESTING
    @Override
    public String toString() {
        return "#";
    }
}
