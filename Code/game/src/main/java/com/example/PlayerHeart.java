package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Objects;


public class PlayerHeart {
    public GamePanel gp;
    public String name;
    public BufferedImage heart_blank,heart_half,heart_full;
    //UtilityTool uTool=new UtilityTool();
    public PlayerHeart(GamePanel gp){
        this.gp = gp;
        name = "Heart";
        getPlayerImage();
    }
    public void getPlayerImage(){
        try {
            heart_blank = ImageIO.read(getClass().getResourceAsStream("/player/heart_blank.png"));
            heart_half = ImageIO.read(getClass().getResourceAsStream("/player/heart_half.png"));
            heart_full = ImageIO.read(getClass().getResourceAsStream("/player/heart_full.png"));
            //uTool.scaleImage(heart_blank,gp.tileSize,gp.tileSize);
            //uTool.scaleImage(heart_half,gp.tileSize,gp.tileSize);
            //uTool.scaleImage(heart_full,gp.tileSize,gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}