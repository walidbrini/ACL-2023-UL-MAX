package com.example;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    public GamePanel gp;
    public int x,y;
    public int speed;
    public BufferedImage up1,up2,up3, down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public boolean collisionOn = false ;
    public int solidAreaDefaultX,solidAreaDefaultY;

    public int maxLife = 6;
    public int life = maxLife;
    public int maxMana ;
    public int mana = maxMana;
    public int attack;

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Entity(){}
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}