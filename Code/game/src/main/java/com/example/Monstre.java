package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
 
// change to entity 
public class Monstre extends Entity  {
    GamePanel gp;
    private int minDirectionStayCount = 10;  
    private int maxRandomWalkSteps = 10;
    public Monstre(GamePanel gp) {
        this.gp = gp;
        setDefaultValues(gp.labyrinth);
        getMonstreImage();

        solidArea = new Rectangle(0,0,48,48);

    }

    public void setDefaultValues(Labyrinth labyrinth) {
        // Spawn Coordinates
        int spawnCol;
        int spawnRow;
    
        // Ensure the monster doesn't spawn on the borders or on a wall
        do {
            spawnCol = (int) (Math.random() * (GamePanel.getMaxScreenCol() - 2)) + 1;
            spawnRow = (int) (Math.random() * (GamePanel.getMaxScreenRow() - 2)) + 1;
        } while (spawnCol <= 1 || spawnRow <= 1 || !labyrinth.isFree(spawnCol, spawnRow));
    
        x = spawnCol * gp.getTileSize();
        y = spawnRow * gp.getTileSize();
        setSpeed(1);
        direction = "down";
        maxLife = 30;
        life = maxLife;
        alive = true;
    }
    


    public void getMonstreImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/monster/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/monster/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/monster/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/monster/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private int directionStayingCounter = 0;
    private int maxDirectionStayCount = 60;  

    public void update() {
        spriteCounter++;
        if (spriteCounter > 10) {
            updateSpriteNum();
            spriteCounter = 0;
        }
    
        if (directionStayingCounter > 0) {
            handleDirectionStayCollision();
        } else {
            chooseNewDirection();
            handleDirectionChangeCollision();
        }
    }
    
    private void updateSpriteNum() {
        switch (spriteNum) {
            case 1:
                spriteNum = 2;
                break;
            case 2:
                spriteNum = 3;
                break;
            case 3:
                spriteNum = 1;
                break;
        }
    }
    
    private void handleDirectionStayCollision() {
        directionStayingCounter--;
    
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);
    
        if (!collisionOn) {
            moveInDirection();
        } else {
            changeDirectionAndMove();
        }
    }
    
    private void chooseNewDirection() {
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
    
        directionStayingCounter = (int) (Math.random() * maxDirectionStayCount) + minDirectionStayCount;
    }
    
    private void handleDirectionChangeCollision() {
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);
    
        if (!collisionOn) {
            moveInDirection();
        } else {
            changeDirectionAndMove();
        }
    }
    
    private void changeDirectionAndMove() {
        // Change direction to a random direction
        chooseNewDirection();
    
        // Move a random number of steps in the new direction
        int randomWalkSteps = (int) (Math.random() * maxRandomWalkSteps) + 1;  // Adjust maxRandomWalkSteps based on your needs
    
        for (int i = 0; i < randomWalkSteps; i++) {
            moveInDirection();
        }
    }
    private void moveInDirection() {
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
}


        
    
 

}

	
	 

	
	


 

	


     