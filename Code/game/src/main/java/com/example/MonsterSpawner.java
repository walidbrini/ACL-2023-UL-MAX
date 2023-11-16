package com.example;

import java.util.ArrayList;
import java.util.List;

public class MonsterSpawner {
    private GamePanel gamePanel;
    private List<Monstre> monsters;

    public MonsterSpawner(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.monsters = new ArrayList<>();
    }

    public void spawnMonsters(int numberOfMonsters) {
        for (int i = 0; i < numberOfMonsters; i++) {
            Monstre monster = new Monstre(gamePanel);
            monsters.add(monster);
        }
    }

    public List<Monstre> getMonsters() {
        return monsters;
    }
}
