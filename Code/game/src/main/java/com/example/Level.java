package com.example;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level {
    private final GamePanel gp;
    private int levelNumber;
    private final int levelDifficultyStep = 1;
    private final int finalLevel = 4;
    private static final String fileSaveLocation = "save_files/save.txt";
    private boolean gameSaved = false;

    public Level(GamePanel gp) {
        this.gp = gp;
        gp.labyrinth = new Labyrinth(gp);
    }

    public void initializeGame(){
        gp.labyrinth.generateStartMenuMap();
    }

    public void startNewGame(){
        gp.labyrinth.generate(null);
        levelNumber = 1;
        gp.player.setPosition(gp.labyrinth.getSpawn().getPosition().getX() * gp.tileSize,
                              gp.labyrinth.getSpawn().getPosition().getY() * gp.tileSize);
    }

    public void continueGame() throws IOException {
        loadGame();
    }

    public void restart(){
        gp.monsterSpawner.clearMonsters();
        gp.player.replenishLife();
        gp.player.restoreMana();
        this.startNewGame();
    }

    public void saveGame() throws IOException {
        gp.labyrinth.saveToFile();
        FileWriter fileWriter = new FileWriter(fileSaveLocation);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(levelNumber);
        printWriter.print(',');
        printWriter.print(gp.labyrinth.getDifficulty());
        printWriter.print(',');
        printWriter.print(gp.player.x);
        printWriter.print(',');
        printWriter.print(gp.player.y);
        printWriter.print(',');
        printWriter.print(gp.player.life);
        printWriter.print(',');
        printWriter.print(gp.player.mana);
        printWriter.print(',');
        printWriter.close();
        setGameSaved(true);
    }

    public void loadGame() throws IOException {
        gp.labyrinth.loadFromFile("save_files/map.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileSaveLocation));

        String line = reader.readLine();
        if (line != null) {
            String[] values = line.split(",");
            if (values.length == 6) { // Assuming there are 5 values separated by commas
                levelNumber = Integer.parseInt(values[0]);
                gp.labyrinth.setDifficulty(Difficulty.valueOf(values[1]));
                int playerX = Integer.parseInt(values[2]);
                int playerY = Integer.parseInt(values[3]);
                int playerLife = Integer.parseInt(values[4]);
                int playerMana = Integer.parseInt(values[5]);

                gp.getPlayer().setPosition(playerX, playerY);
                gp.getPlayer().setLife(playerLife);
                gp.getPlayer().setMana(playerMana);

            } else {
                // Handle incorrect file format
                System.out.println("Error: Incorrect file format");
            }
        }

        reader.close();
    }

    public void update(){
        if (gp.checker.checkTreasure(gp.player,gp.labyrinth)){
            if(levelNumber == finalLevel){
                gp.setGameState(GameState.WIN);
            }
            else{
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

    public boolean isGameSaved() {
        return gameSaved;
    }

    public void setGameSaved(boolean gameSaved) {
        this.gameSaved = gameSaved;
    }
}

