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
            up1 = setupImage("/player/up1.png");
            up2 = setupImage("/player/up2.png");
            up3 = setupImage("/player/up3.png");
            down1 = setupImage("/player/down1.png");
            down2 = setupImage("/player/down2.png");
            down3 = setupImage("/player/down3.png");
            left1 = setupImage("/player/left1.png");
            left2 = setupImage("/player/left2.png");
            left3 = setupImage("/player/left3.png");
            right1 = setupImage("/player/right1.png");
            right2 = setupImage("/player/right2.png");
            right3 = setupImage("/player/right3.png");
    }
    public void update(){
        gp.checker.checkMonstre(this, gp.monsterSpawner);

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

        draw(g2,gp);
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