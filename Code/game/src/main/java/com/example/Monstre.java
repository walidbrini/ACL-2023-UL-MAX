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
    public Monstre(){}
    public void setDefaultValues(Labyrinth labyrinth) {
        // Spawn Coordinates
        int spawnCol;
        int spawnRow;
    
        do {
            spawnCol = (int) (Math.random() * (GamePanel.getMaxScreenCol() - 2)) + 1;
            spawnRow = (int) (Math.random() * (GamePanel.getMaxScreenRow() - 2)) + 1;
        } while (spawnCol <= 1 || spawnRow <= 1 || !isSpawnLocationValid(labyrinth, spawnCol, spawnRow));
    
        x = spawnCol * gp.getTileSize();
        y = spawnRow * gp.getTileSize();
        setSpeed(1);
        direction = "down";
        maxLife = 30;
        life = maxLife;
        alive = true;
    }
    
    private boolean isSpawnLocationValid(Labyrinth labyrinth, int col, int row) {
        // Check if the spawn location and its adjacent cells are free of walls
        for (int i = col - 1; i <= col + 1; i++) {
            for (int j = row - 1; j <= row + 1; j++) {
                if (!labyrinth.isFree(i, j)) {
                    return false;
                }
            }
        }
        return true;
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
        spriteNum = (spriteNum % 3) + 1;
    }

    private void handleDirectionStayCollision() {
        directionStayingCounter--;

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);

        if (!collisionOn) {
            moveInDirection();
        } else {
            // Try moving in a different direction before changing
            chooseNewDirection();
            if (!attemptMoveInDirection()) {
                changeDirection();
            }
        }
    }

    private boolean attemptMoveInDirection() {
        // Try moving in the current direction without changing it
        String originalDirection = direction;
        moveInDirection();

        // Check for collision after attempting to move
        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);

        if (collisionOn) {
            // If there's still a collision, revert to the original direction
            direction = originalDirection;
            return false;
        }

        return true;
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

        directionStayingCounter = (int) (Math.random() * maxDirectionStayCount) + 1;
    }

    private void handleDirectionChangeCollision() {
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.checker.checkSquare(this, gp.labyrinth);

        if (!collisionOn) {
            moveInDirection();
        } else {
            // Try moving in a different direction before changing
            chooseNewDirection();
            if (!attemptMoveInDirection()) {
                changeDirection();
            }
        }
    }

    private void changeDirection() {
        // Change direction to a random direction
        chooseNewDirection();

        // Reset the counter to allow staying in the new direction
        directionStayingCounter = (int) (Math.random() * maxDirectionStayCount) + 1;
    }

    public void moveInDirection() {
        switch (direction) {
            case "up":
                y -= 1; // Adjust the step size based on your needs
                break;
            case "down":
                y += 1;
                break;
            case "left":
                x -= 1;
                break;
            case "right":
                x += 1;
                break;
        }
    }
}

	
	 

	
	


 

	


     