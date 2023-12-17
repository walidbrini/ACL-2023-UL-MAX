package com.example;

public class Axe extends Projectile{

    public GamePanel gp;
    public Axe(GamePanel gp){
        super(gp);
        name = "Axe";
        setSpeed(10);
        maxLife = 80;
        life = maxLife;
        attack = 2 ;
        useCost = 1;
        getImage();

    }
    public void getImage(){
        up1 = setupImage("/items/axe/three.png");
        up2 = setupImage("/items/axe/one.png");
        up3 = setupImage("/items/axe/two.png");

        down1 = setupImage("/items/axe/two.png");
        down2 = setupImage("/items/axe/three.png");
        down3 = setupImage("/items/axe/one.png");

        left1 = setupImage("/items/axe/two.png");
        left2 = setupImage("/items/axe/three.png");
        left3 = setupImage("/items/axe/one.png");

        right1 = setupImage("/items/axe/one.png");
        right2 = setupImage("/items/axe/two.png");
        right3 = setupImage("/items/axe/three.png");
    }

}
