package com.example;

public enum Difficulty {
    CHICKEN,
    EASY,
    MEDIUM,
    HARD,
    INSANE {
        @Override
        public Difficulty next() {
            return this;
        };
    };

    public Difficulty next() {
        return values()[ordinal() + 1];
    }
}
