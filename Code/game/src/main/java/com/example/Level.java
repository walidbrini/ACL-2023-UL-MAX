package com.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level {
    private final GamePanel gp;
    private int levelNumber;
    private final int levelDifficultyStep = 2;
    private final int finalLevel = 12;

    public Level(GamePanel gp) {
        this.gp = gp;
        gp.labyrinth = new Labyrinth(GamePanel.getMaxScreenCol(), GamePanel.getMaxScreenRow(),gp);
        gp.labyrinth.generate(null);
        levelNumber = 1;
    }

    public void update(){
        if (gp.checker.checkTreasure(gp.player,gp.labyrinth)){
            if(levelNumber == finalLevel){
                //TODO
                // win
            }
            gp.labyrinth.levelTransition();
            gp.repaint();
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if ( levelNumber++ % levelDifficultyStep == 0 ){
                gp.labyrinth.setDifficulty(gp.labyrinth.getDifficulty().next());
            }
            gp.labyrinth.generate(gp.labyrinth.getTreasure().getPosition());
            spawnNewMonsters();
        }
    }
    
    private void spawnNewMonsters(){
        int min = 0, max = 0;

        switch (gp.labyrinth.getDifficulty()) {
            case CHICKEN:
                min = 1;
                max = 2;
                break;
            case EASY:
                min = 3;
                max = 5;
                break;
            case MEDIUM:
                min = 5;
                max = 8;
                break;
            case HARD:
                min = 10;
                max = 12;
                break;
            case INSANE:
                min = 13;
                max = 16;
                break;
        }

        Random r = new Random();
        int numberOfMonsters = r.nextInt(max-min) + min;
        gp.monsterSpawner.clearMonsters();
        gp.monsterSpawner.spawnMonsters(numberOfMonsters);
    }
}

