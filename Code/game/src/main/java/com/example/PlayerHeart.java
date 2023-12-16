package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Objects;


public class PlayerHeart extends Utilities{
    public GamePanel gp;
    public String name;
    public BufferedImage heart_blank,heart_half,heart_full;

    public PlayerHeart (GamePanel gp){
        this.gp = gp;
        name = "Heart";
        getPlayerImage();
    }
    public void getPlayerImage(){
            heart_blank = setupImage("/player/heart_blank.png");
            heart_half = setupImage("/player/heart_half.png");
            heart_full = setupImage("/player/heart_full.png");
    }
}