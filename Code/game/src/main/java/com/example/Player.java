package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    Controller keyH;
    PlayerHeart heart ;//= new PlayerHeart(gp);

    public Player(GamePanel gp, Controller keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultX =solidArea.y ;

        heart = new PlayerHeart(gp);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        speed = 4;
        direction = "down";

        //Player Status
        maxLife = 6;
        life = maxLife ; //2 lives = 1 heart

    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if (keyH.up == true || keyH.down==true || keyH.left==true || keyH.right==true){
            if(keyH.up){
                direction = "up";
            }
            else if (keyH.down){
                direction = "down";
            }
            else if (keyH.right){
                direction ="right";
            }
            else if (keyH.left){
                direction = "left" ;
            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkSquare(this,gp.labyrinth);
            // CHECK Fire Collision
            gp.checker.checkObject(this ,gp.labyrinth ,true);
            

            //IF COLLISION IS FALSE , PLAYER CAN MOVE
            if(collisionOn == false){
                switch(direction){
                    case "up":  y -= speed; break;
                    case "down":  y += speed; break;
                    case "left":  x -= speed; break;
                    case "right":  x += speed; break;
                }
            }
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
        drawPlayerLife(g2);
        // draw an image on the screen
    }
    public void drawPlayerLife(Graphics2D g2){
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW EMPTY LIFE
        while (i < maxLife / 2){
            g2.drawImage(heart.heart_blank , x ,y ,gp.tileSize , gp.tileSize , null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        // DRAW CURRENT LIFE
        while(i < life){
            g2.drawImage(heart.heart_half, x ,y ,gp.tileSize , gp.tileSize , null);
            i++;
            if (i < life){
                g2.drawImage(heart.heart_full, x ,y ,gp.tileSize , gp.tileSize , null);
            }
            i++;
            x += gp.tileSize ;
        }
    }
    // to create sprites we can use paint photoshop gimp or PiSKEL (browse-base free software )
}