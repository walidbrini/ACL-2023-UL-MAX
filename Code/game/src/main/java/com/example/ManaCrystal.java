package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ManaCrystal extends Utilities{
    public GamePanel gp;
    public String name;
    public BufferedImage mana_full, mana_blank;

    public ManaCrystal(GamePanel gp) {
        this.gp = gp;
        name = "mana crystal";
        getCrystalImage();
    }
    public void getCrystalImage() {
            mana_full = setupImage("/items/mana/mana_full.png");
            mana_blank = setupImage("/items/mana/mana_blank.png");
    }

}
