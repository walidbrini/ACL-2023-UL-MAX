package com.example;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Entity extends Utilities{
    GamePanel gp;
    public int x, y;
    private int speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int originalSpeed =speed;
    public boolean speedBoosted =false;
    public int boostValue = 10;

    public int maxLife = 6;
    public int life = maxLife;
    public boolean alive;
    public int maxMana;
    public int mana;


    public Entity(GamePanel gp, int x, int y, int speed) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.originalSpeed=speed;
    }

    public Entity() {
    }

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public void draw(Graphics2D g2, GamePanel gp) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;

        }
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

/*
    *************************************************
    *                                               *
    *                   Getters                     *
    *                                               *
    * ***********************************************
 */

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX(){ 
        return(x);
    } 

    public int getY(){ 
        return(y);
    } 
}