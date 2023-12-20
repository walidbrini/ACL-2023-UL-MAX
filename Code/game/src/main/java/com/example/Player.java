package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private Controller keyH;
    private PlayerHeart heart ;//= new PlayerHeart(gp);
    private ManaCrystal crystal ;

    public BufferedImage attack_right_1,attack_right_2,attack_right_3,attack_up_1,attack_up_2
                    ,attack_up_3,attack_down_1,attack_down_2,attack_down_3,attack_left_1,attack_left_2,attack_left_3; 

    private int attack_counter = 0 ;
    private int kills = 0;

    private Projectile projectile ;


    public Player(GamePanel gp ,Controller keyH){
        super(gp);
        this.keyH = keyH;
        
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        heart = new PlayerHeart(gp);
        crystal = new ManaCrystal(gp);

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        setSpeed(4);
        direction = "down";

        //Player Status
        maxLife = 6;
        life = maxLife ; //2 lives = 1 heart
        maxMana = 4 ;
        mana = maxMana;
        projectile = new Fireball(gp);

    }
    public void getPlayerImage(){
            up1 = setupImage("/player/player3/up/up1.png");
            up2 = setupImage("/player/player3/up/up2.png");
            up3 = setupImage("/player/player3/up/up3.png");
            down1 = setupImage("/player/player3/down/down1.png");
            down2 = setupImage("/player/player3/down/down2.png");
            down3 = setupImage("/player/player3/down/down3.png");
            left1 = setupImage("/player/player3/left/left1.png");
            left2 = setupImage("/player/player3/left/left2.png");
            left3 = setupImage("/player/player3/left/left3.png");
            right1 = setupImage("/player/player3/right/right1.png");
            right2 = setupImage("/player/player3/right/right2.png");
            right3 = setupImage("/player/player3/right/right3.png");
    }
    public void getPlayerAttackImage(){
        attack_right_1 = setupImage("/player/player3/right/attack1.png");
        attack_right_2 = setupImage("/player/player3/right/attack2.png");
        attack_right_3 = setupImage("/player/player3/right/attack3.png");

        attack_up_1 = setupImage("/player/player3/up/attack1.png");
        attack_up_2 = setupImage("/player/player3/up/attack2.png");
        attack_up_3 = setupImage("/player/player3/up/attack3.png");

        attack_down_1 = setupImage("/player/player3/down/attack1.png");
        attack_down_2 = setupImage("/player/player3/down/attack2.png");
        attack_down_3 = setupImage("/player/player3/down/attack3.png");

        attack_left_1 = setupImage("/player/player3/left/attack1.png");
        attack_left_2 = setupImage("/player/player3/left/attack2.png");
        attack_left_3 = setupImage("/player/player3/left/attack3.png");
    }
    public void update(){
        if (keyH.up == true || keyH.down==true || keyH.left==true || keyH.right==true) {
            setDirection();
            checkcollision();
            //IF COLLISION IS FALSE , PLAYER CAN MOVE
            movePlayer();
        }
        if (gp.control.shoot == true && projectile.alive == false && projectile.checkMana(this) == true){
            projectile.set(x,y,direction,true,this);
            projectile.reduceMana(this);
            gp.projectileList.add(projectile);
        }
        if(life <= 0){
            gp.gameState = GameState.GAMEOVER;
        }
    }
    public void movePlayer(){
        if(collisionOn == false){
            switch(direction){
                case "up":  y -= getSpeed(); break;
                case "down":  y += getSpeed(); break;
                case "left":  x -= getSpeed(); break;
                case "right":  x += getSpeed(); break;
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
    public void setDirection(){

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
    }
    public void checkcollision(){
        gp.checker.checkMonstre(this, gp.monsterSpawner);
        for (int j=0 ; j<gp.projectileList.size();j++){
            gp.checker.checkProjectile(gp.projectileList.get(j),gp.monsterSpawner);
        }
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.checker.checkSquare(this,gp.labyrinth);
        // CHECK FIRE COLLISION
        gp.checker.checkObject(this ,gp.labyrinth ,true);
    }
        
    public void restoreMana(){
        mana = maxMana;
    }
    public void addKills(){kills++;}
    public void drawPlayer(Graphics2D g2,GamePanel gp){

        BufferedImage image = null;
        draw(g2, gp);
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
            g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
        } else {
            attack_counter = 0;
        }
        drawPlayerLife(g2);
        drawPlayerMana(g2);
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
    public void drawPlayerMana(Graphics2D g2){
        // DRAW MAX MANA

        int x = gp.tileSize / 2;
        int y = gp.tileSize * 2;
        int i = 0;

        x = gp.screenWidth - gp.tileSize * 4;
        y = gp.tileSize /2;
        i = 0;
        while(i<gp.player.maxMana){
            g2.drawImage(crystal.mana_blank,x,y,gp.tileSize , gp.tileSize,null);
            i++;
            x+=35;
        }
        // DRAW MANA
        x = gp.screenWidth - gp.tileSize * 4;
        y = gp.tileSize /2;
        i = 0;
        while(i<gp.player.mana){
            g2.drawImage(crystal.mana_full,x,y,gp.tileSize , gp.tileSize,null);
            i++;
            x+=35;
        }
    }

    public void replenishLife() {this.life = maxLife;
    }
    public void replenishMana() {this.mana = maxMana;}

//******************************************************//
//                                                      //
//                 Getters and Setters                  //
//                                                      //
//******************************************************//

    public Controller getKeyH() {
        return keyH;
    }

    public PlayerHeart getHeart() {
        return heart;
    }

    public ManaCrystal getCrystal() {
        return crystal;
    }

    public int getAttack_counter() {
        return attack_counter;
    }

    public int getKills() {
        return kills;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}