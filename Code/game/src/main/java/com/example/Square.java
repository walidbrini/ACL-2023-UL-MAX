package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.Objects;

public  class Square {
    private ObjectType content;
    private boolean blocking;
    private int effectOnHealthPoints;
    private String imagePath;
    private final BufferedImage bufferedImage;
    public boolean collision ; // oth
    public Rectangle solidArea = new Rectangle(0,0,48,48); // oth
    public int solidAreaDefaultX =0;
    public int solidAreaDefaultY =0;

    Square(ObjectType content, boolean blocking, int effectOnHealthPoints, String imagePath) {
        this.content = content;
        this.blocking = blocking;
        this.effectOnHealthPoints = effectOnHealthPoints;
        this.collision = false;
        try {
            this.bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Square(ObjectType content, String imagePath) {
        this.content = content;
        this.blocking = false;
        this.effectOnHealthPoints = 0;
        this.collision = false;
        try {
            this.bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Square(ObjectType content, int effectOnHealthPoints, String imagePath) {
        this.content = content;
        this.blocking = false;
        this.effectOnHealthPoints = effectOnHealthPoints;
        this.collision = false;
        try {
            this.bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Square(ObjectType content, boolean blocking, String imagePath) {
        this.content = content;
        this.blocking = blocking;
        this.effectOnHealthPoints = 0;
        this.collision = false;
        try {
            this.bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Square() {
        this.content = ObjectType.NONE;
        this.blocking = false;
        this.effectOnHealthPoints = 0;
        this.collision = false;
        try {
            this.bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectType getContent() {
        return content;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public int getEffectOnHealthPoints() {
        return effectOnHealthPoints;
    }

    public void setContent(ObjectType content) {
        this.content = content;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public void setEffectOnHealthPoints(int effectOnHealthPoints) {
        this.effectOnHealthPoints = effectOnHealthPoints;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
