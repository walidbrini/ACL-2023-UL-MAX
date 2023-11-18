package com.example;

import java.util.concurrent.TimeUnit;

public class Level {
    GamePanel gp;
    int levelNumber;

    public Level(GamePanel gp) {
        this.gp = gp;
        gp.labyrinth = new Labyrinth(GamePanel.getMaxScreenCol(), GamePanel.getMaxScreenRow(),gp);
        gp.labyrinth.generate(null);
        levelNumber = 1;
    }

    public void update(){
        if (gp.checker.checkTreasure(gp.player,gp.labyrinth)){
            gp.labyrinth.levelTransition();
            gp.repaint();
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if ( levelNumber++ % 3 == 0 ){
                gp.labyrinth.setDifficulty(gp.labyrinth.getDifficulty().next());
            }
            gp.labyrinth.generate(gp.labyrinth.getTreasure().getPosition());

            // TODO
            // Monster spawning depeding on the difficulty
        }
    }
}

