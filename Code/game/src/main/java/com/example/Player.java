package com.example;
import com.example.GamePanel ;
import com.example.Controller ;
import com.example.Entity ;
        

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    Controller keyH;

    public Player( GamePanel gp, Controller keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100 ;
        y = 100 ;
        speed = 4 ;
        direction = "down";
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
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if (keyH.up == true || keyH.down==true || keyH.left==true || keyH.right==true){
            if(keyH.up){
                direction = "up";
                y -= speed;
            }
            else if (keyH.down){
                direction = "down";
                y += speed;
            }
            else if (keyH.right){
                direction ="right";
                x += speed;
            }
            else if (keyH.left){
                direction = "left" ;
                x -= speed;
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

        // g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize,gp.tileSize);

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

    // to create sprites we can use paint photoshop gimp or PiSKEL (browse-base free software )
}