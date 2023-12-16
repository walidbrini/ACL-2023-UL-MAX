package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utilities {

    public BufferedImage setupImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
