package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    Controller keyH;
    PlayerHeart heart ;//= new PlayerHeart(gp);
    int minX = 100; // Replace with your desired values
    int minY = 100;
    int maxX = 200;
    int maxY = 200;
    
    BufferedImage attack_right_1,attack_right_2,attack_right_3,attack_up_1,attack_up_2
                    ,attack_up_3,attack_down_1,attack_down_2,attack_down_3,attack_left_1,attack_left_2,attack_left_3; 


    int attack_counter = 0 ; 

    public Projectile projectile ;

    public Player(GamePanel gp ,Controller keyH){
        super(gp);
        this.keyH = keyH;
        
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultX = solidArea.y ;

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
        projectile = new Fireball(gp);

    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/right3.png"));
            
            attack_right_1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/attack1.png"));
            attack_right_2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/attack2.png"));
            attack_right_3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/right/attack3.png"));
            
            attack_up_1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/attack1.png"));
            attack_up_2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/attack2.png"));
            attack_up_3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/up/attack3.png"));
            
            attack_down_1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/attack1.png"));
            attack_down_2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/attack2.png"));
            attack_down_3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/down/attack3.png"));
            
            attack_left_1 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/attack1.png"));
            attack_left_2 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/attack2.png"));
            attack_left_3 = ImageIO.read(getClass().getResourceAsStream("/player/player3/left/attack3.png"));
            
            


        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void update(){
        gp.checker.checkMonstre(this, gp.monsterSpawner);
        for (int j=0 ; j<gp.projectileList.size();j++){
            gp.checker.checkProjectile(gp.projectileList.get(j),gp.monsterSpawner);
         }
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
            if (spriteCounter > 12){
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
        if (gp.control.shoot == true && projectile.alive == false ){
            projectile.set(x,y,direction,true,this);
            gp.projectileList.add(projectile);
        }
        if(life <= 0){
            gp.gameState = GameState.GAMEOVER;
        }

    }

    public void drawPlayer(Graphics2D g2,GamePanel gp){

        BufferedImage image = null;

        if (keyH.attaque) {
            attack_counter++;
            if (attack_counter > 10) {
                // Reset the counter only after a certain threshold
                attack_counter = 0;
            }
        
            // Determine the attack image based on direction and attack_counter
            switch (direction) {
                case "up":
                    if (attack_counter >= 1 && attack_counter <= 3) {
                        image = attack_up_1;
                    } else if (attack_counter >= 4 && attack_counter <= 6) {
                        image = attack_up_2;
                    } else if (attack_counter >= 7 && attack_counter <= 9) {
                        image = attack_up_3;
                    }
                    break;
                case "down":
                    if (attack_counter >= 1 && attack_counter <= 3) {
                        image = attack_down_1;
                    } else if (attack_counter >= 4 && attack_counter <= 6) {
                        image = attack_down_2;
                    } else if (attack_counter >= 7 && attack_counter <= 9) {
                        image = attack_down_3;
                    }
                    break;
                case "left":
                    if (attack_counter >= 1 && attack_counter <= 3) {
                        image = attack_left_1;
                    } else if (attack_counter >= 4 && attack_counter <= 6) {
                        image = attack_left_2;
                    } else if (attack_counter >= 7 && attack_counter <= 9) {
                        image = attack_left_3;
                    }
                    break;
                case "right":
                    if (attack_counter >= 1 && attack_counter <= 3) {
                        image = attack_right_1;
                    } else if (attack_counter >= 4 && attack_counter <= 6) {
                        image = attack_right_2;
                    } else if (attack_counter >= 7 && attack_counter <= 9) {
                        image = attack_right_3;
                    }
                    break;
            }
        } else {
            attack_counter = 0;
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    } else if (spriteNum == 3) {
                        image = up3;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    } else if (spriteNum == 3) {
                        image = down3;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    } else if (spriteNum == 3) {
                        image = left3;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    } else if (spriteNum == 3) {
                        image = right3;
                    }
                    break;
            }
        }
        
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
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

    public void replenishLife() {
        this.life = maxLife;
    }
}