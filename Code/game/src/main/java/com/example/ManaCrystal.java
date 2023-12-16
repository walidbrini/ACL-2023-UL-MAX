package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ManaCrystal {
    public GamePanel gp;
    public String name;
    public BufferedImage mana_full, mana_blank;

    public ManaCrystal(GamePanel gp) {
        this.gp = gp;
        name = "mana crystal";
        getCrystalImage();
    }

    public void getCrystalImage() {
        try {
            mana_full = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/mana/mana_full.png")));
            mana_blank = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/mana/mana_blank.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
