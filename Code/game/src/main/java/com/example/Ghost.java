package com.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost extends Monstre{
    private GamePanel gp;
    private boolean visibility = true;
    private Timer timer;
    private int spiralCounter = 0;
    private int spiralRadius = 1;
    private int spiralDirection = 1; // 1 for clockwise, -1 for counterclockwise
    public Ghost(GamePanel gp) {
        this.gp = gp;
        getGhostImage();
        setDefaultValues();
        solidArea = new Rectangle(12,12,12,12);
        setSpeed(2);
        timer = new Timer();
        startDisappearingCycle(5000);
    }

    public void setDefaultValues() {
        // Spawn Coordinates
        int spawnCol;
        int spawnRow;

        // Ensure the monster doesn't spawn on the borders or on a wall
        do {
            spawnCol = (int) (Math.random() * (GamePanel.getMaxScreenCol() - 2)) + 1;
            spawnRow = (int) (Math.random() * (GamePanel.getMaxScreenRow() - 2)) + 1;
        } while (spawnCol <= 1 || spawnRow <= 1 );

        x = spawnCol * gp.getTileSize();
        y = spawnRow * gp.getTileSize();
        setSpeed(1);
        direction = "down";
        maxLife = 30;
        life = maxLife;
        alive = true;
    }
    public void getGhostImage(){
        up1 = setupImage("/ghost/up1.png");
        up2 = setupImage("/ghost/up2.png");
        up3 = up2;
        down1 = setupImage("/ghost/down1.png");
        down2 = setupImage("/ghost/down2.png");
        down3 = down2;
        left1 = setupImage("/ghost/left1.png");
        left2 = setupImage("/ghost/left2.png");
        left3 = left2;
        right1 = setupImage("/ghost/right1.png");
        right2 = setupImage("/ghost/right2.png");
        right3 = right2;
    }
    public void update() {

        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);

        if (collisionOn ) {
            changeDirection();
            //randomDirection();
        } else {
            moveInDirection();

        }

    }

    public void changeDirection(){
        switch(direction){
            case "up":
                direction = "right";
                y+=getSpeed();
               ; break;
            case "down":
                direction = "left" ;
                y-=getSpeed();
                break;
            case "left":
                direction = "up" ;
                x+=getSpeed();
                break;
            case "right":
                direction = "down" ;
                x-=getSpeed();
                break;
        }
    }
    public void randomDirection(){
        int randomDirection = (int) (Math.random() * 4);
        switch (randomDirection) {
            case 0:
                direction = "up";
                break;
            case 1:
                direction = "down";
                break;
            case 2:
                direction = "left";
                break;
            case 3:
                direction = "right";
                break;
        }
    }
    public void move(){

    }
    public void appear(Graphics2D g2, GamePanel gp){
        if (visibility){
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
            g2.drawImage(image, x, y, gp.getTileSize()*2/3, gp.getTileSize()*2/3, null);
        }
    }
    /*
    public void disappear() {
        visibility = false;
        gp.ui.showMessage("Ghost Disappeared");
    }

     */

    public void disappear(int amount) {
        visibility = false; // Hide the ghost
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                visibility = true; // Show the ghost after 5 seconds
            }
        }, amount); // 5000 milliseconds = 5 seconds
    }

    // This method should be called to start the disappearing and reappearing cycle
    public void startDisappearingCycle(int amount) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                disappear(amount); // Schedule the ghost to disappear every 5 seconds
            }
        }, 0, 2*amount); // Disappear every 10 seconds (adjust as needed)
    }

    public void moveInSpiral() {
        // Implementing spiral movement logic
        spiralCounter++;
        if (spiralCounter > spiralRadius * 2) {
            spiralCounter = 1;
            spiralRadius++;
            spiralDirection *= -1; // Change direction after completing a circle
            changeDirection();
        }

        switch (direction) {
            case "up":
                y -= getSpeed();
                break;
            case "down":
                y += getSpeed();
                break;
            case "left":
                x -= getSpeed();
                break;
            case "right":
                x += getSpeed();
                break;
        }

        switch (spiralDirection) {
            case 1: // Clockwise
                switch (direction) {
                    case "up":
                        direction = "right";
                        break;
                    case "down":
                        direction = "left";
                        break;
                    case "left":
                        direction = "up";
                        break;
                    case "right":
                        direction = "down";
                        break;
                }
                break;
            case -1: // Counterclockwise
                switch (direction) {
                    case "up":
                        direction = "left";
                        break;
                    case "down":
                        direction = "right";
                        break;
                    case "left":
                        direction = "down";
                        break;
                    case "right":
                        direction = "up";
                        break;
                }
                break;
        }
    }
}