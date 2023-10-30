package com.example;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageLoader {

    private BufferedImage image;

    public ImageLoader(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's needs
        }
    }

    public BufferedImage loadImage(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's needs
        }
        return image;
    }

  public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        displayImage(img); // Display the grabbed image
        return img;
    }

    public void displayImage(BufferedImage img) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    /*public static void main(String[] args) {
        ImageLoader loader = new ImageLoader("D:\\2AISN\\ACL-2023-UL-MAX\\Code\\game\\res\\player\\M_07.png");
        BufferedImage image = loader.grabImage(1, 1, 16 , 16);

        if (image != null) {
            // Image grabbed successfully, you can now work with the 'image' object
        } else {
            // Image grabbing failed, handle the error
        }
    }*/
}
