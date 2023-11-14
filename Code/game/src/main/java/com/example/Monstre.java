package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
 
// change to entity 
  public class Monstre extends Entity {
    private Labyrinth labyrinth;
    private GamePanel gp;


    public Monstre(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
        getMonstreImage();
    }

    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 20;
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
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

        private int movementDelay = 100;  // Adjust this value based on your preference
        private int movementCounter = 0;
    
        public void moveRandomly() {
            // Check if it's time to perform the next movement
            if (movementCounter >= movementDelay) {
                movementCounter = 0;
    
                int randomDirection = (int) (Math.random() * 4);
    
                switch (randomDirection) {
                    case 0:
                        // Move up
                        if (y > 0) {
                            direction = "up";
                            y -= speed;
                        }
                        break;
                    case 1:
                        // Move down
                        if (y < (gp.maxScreenCol - 1) * gp.getTileSize()) {
                            direction = "down";
                            y += speed;
                        }
                        break;
                    case 2:
                        // Move left
                        if (x > 0) {
                            direction = "left";
                            x -= speed;
                        }
                        break;
                    case 3:
                        // Move right
                        if (x < (gp.maxScreenRow - 1) * gp.getTileSize()) {
                            direction = "right";
                            x += speed;
                        }
                        break;
                }
            } else {
                // Increment the movement counter if it's not time for a movement
                movementCounter++;
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

        
    
    public void run() {
        while (true) {
            moveRandomly();
            try {
                Thread.sleep(500);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

	
	 

	
	


 

	


     