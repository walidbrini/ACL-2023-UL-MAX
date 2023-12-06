package com.example;

import java.awt.*;

public class Projectile extends Entity {
    public int useCost ; // the cost to use the projectile
    Entity user;
    public int maxMana ;
    public int mana = maxMana;
    public int attack;
    public String name ;
    public Projectile(GamePanel gp){
        super(gp);
    }

    public void set(int x , int y , String direction , boolean alive , Entity user){
        this.x=x;
        this.y=y;
        this.direction = direction;
        this.alive =alive;
        this.user = user;
        this.life=this.maxLife; // reset the life to the max value every time you shoot it
        solidArea = new Rectangle(0,0,48,48);
    }

    public void update(){
        switch (direction){
            case "up" : y -= speed ; break;
            case "down" : y += speed ; break;
            case "left" : x -= speed ; break;
            case "right" : x += speed ; break;
        }
        life--; // lose 1 each game frame or loop => the fireball disappears after 80 frames
        if(life <=0 ) {
            alive = false; // disappear
        }
        // once you shoot a projectile it gradually lose its life

        //CHECK PROJECTILE COLLISION
        //gp.gamePanel.checker.checkProjectile(gamePanel,this);

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

}