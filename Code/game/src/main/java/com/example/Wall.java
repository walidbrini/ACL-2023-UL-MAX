package com.example;

public class Wall extends Square{
    public Wall() {
        super();
        this.setContent(Object.WALL);
        this.setBlocks(true);
    }

    // FOR TESTING
    @Override
    public String toString() {
        return "#";
    }
}
