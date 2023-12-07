package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

    public void clearMonsters() {
        monsters.clear();
    }

    public void drawMonsters(Graphics2D g2) {
        if (monsters != null) {
            for (Monstre monster : monsters) {
                monster.draw(g2,gamePanel);
            }
        }
    }

    public void update(){
        if (monsters != null){
			for (Monstre monster : monsters) {
				monster.update();

                // CHECK projectile collision
			}
		}
    }
}
