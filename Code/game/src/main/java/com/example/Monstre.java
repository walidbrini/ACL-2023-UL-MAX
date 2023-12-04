package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
 
// change to entity 
public class Monstre extends Entity  {
    GamePanel gp;

    public Monstre(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
        getMonstreImage();

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultX =solidArea.y ;

        
    }

    public void setDefaultValues() {
        // Spawn Coordinates
        int spawnCol;
        int spawnRow;
    
        // Ensure the monster doesn't spawn on the borders
        do {
            spawnCol = (int) (Math.random() * (GamePanel.getMaxScreenCol() - 2)) + 1;
            spawnRow = (int) (Math.random() * (GamePanel.getMaxScreenRow() - 2)) + 1;
        } while (spawnCol <= 1 || spawnRow <= 1);
    
        x = spawnCol * gp.getTileSize();
        y = spawnRow * gp.getTileSize();
        speed = 1;
        direction = "down";
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
        if (spriteCounter > 10){
            if (spriteNum==1){
                spriteNum=2;
            }
            else if (spriteNum==2){
                spriteNum = 3;
            }
            else if (spriteNum==3){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (directionStayingCounter > 0) {
            directionStayingCounter--;

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkSquare(this, gp.labyrinth);
            // CHECK Fire Collision
            //gp.checker.checkObject(this, gp.labyrinth, true);

            if (collisionOn == false) {
                moveInDirection();
            } else {
                directionStayingCounter = 0;
            }
        } else {
            
            // Choose a new random direction
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

            directionStayingCounter = (int) (Math.random() * maxDirectionStayCount);

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkSquare(this, gp.labyrinth);
            // CHECK Fire Collision
            //gp.checker.checkObject(this, gp.labyrinth, true);

            if (collisionOn == false) {
                moveInDirection();
            } else {
                directionStayingCounter = 0;
            }
        }
    }

    private void moveInDirection() {
        switch (direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
        }
}

        
    public void draw(Graphics2D g2){

        BufferedImage image = null ;

        switch (direction) {
            case "up":
                if (spriteNum==1){
                    image = up1;
                }
                if (spriteNum==2){
                    image = up2;
                }
                if (spriteNum==3) {
                    image = up3;
                }
                break;
            case "down" :
                if (spriteNum==1){
                    image = down1;
                }
                if (spriteNum==2){
                    image = down2;
                }
                if (spriteNum==3){
                    image = down3;
                }
                break;
            case "left" :
                if (spriteNum==1){
                    image = left1;
                }
                if (spriteNum==2){
                    image = left2;
                }
                if (spriteNum==3){
                    image = left3;
                }
                break;
            case "right" :
                if (spriteNum==1){
                    image = right1;
                }
                if (spriteNum==2){
                    image = right2;
                }
                if (spriteNum==3){
                    image = right3;
                }
                break;

        }
        g2.drawImage(image , x ,y , gp.getTileSize() , gp.getTileSize(),null);
        // draw an image on the screen
    }

        
    
 

}

	
	 

	
	


 

	


     