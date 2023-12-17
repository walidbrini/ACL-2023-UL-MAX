package com.example;

public class Fireball extends Projectile {
    public GamePanel gp;
    public Fireball(GamePanel gp){
        super(gp);
        name = "Fireball";
        setSpeed(5);
        maxLife = 80;
        life = maxLife;
        attack = 2 ;
        useCost = 1;
        getImage();
    }
    public void getImage(){
        up1 = setupImage("/items/fireball/fireball_up_1.png");
        up2 = setupImage("/items/fireball/fireball_up_1.png");
        up3 = up2;
        down1 = setupImage("/items/fireball/fireball_down_1.png");
        down2 = setupImage("/items/fireball/fireball_down_1.png");
        down3 = down2;
        left1 = setupImage("/items/fireball/fireball_left_1.png");
        left2 = setupImage("/items/fireball/fireball_left_1.png");
        left3 = left2;
        right1 = setupImage("/items/fireball/fireball_right_1.png");
        right2 = setupImage("/items/fireball/fireball_right_1.png");
        right3 = right2;
    }
    public boolean checkMana(Entity user){
        boolean resource = false;
        if(user.mana >= useCost){
            resource = true;
        }
        return resource;
    }
    public void reduceMana(Entity user){
        user.mana -= useCost;
    }



}