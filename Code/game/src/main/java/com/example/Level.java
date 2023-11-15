package com.example;

import java.util.concurrent.TimeUnit;

public class Level {
    GamePanel gp;
    Difficulty difficulty;

    public Level(GamePanel gp, Difficulty difficulty) {
        this.gp = gp;
        this.difficulty = difficulty;
    }

    //public void intializeLevel(difficulty, gp){
    //    labyrinth = new Labyrinth(gp.getMaxScreenCol(),gp.getMaxScreenRow(),difficulty, gp);
    //}

    public void update(){
        if (gp.checker.checkTreasure(gp.player,gp.labyrinth)){
            gp.labyrinth.levelTransition();
            gp.repaint();
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //labyrinth.setDifficulty(Difficulty.CHICKEN);
            gp.labyrinth.generateRandomly(gp.labyrinth.getTreasure().getPosition());
        }
    }
}

