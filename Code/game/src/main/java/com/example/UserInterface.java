package com.example;

import java.awt.*;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80B;
    public String message ="";
    public boolean messageOn = false;
    int messageCounter = 0;

    public UserInterface(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gp.gameState == GameState.PLAYSTATE){

        }else if(gp.gameState == GameState.PAUSESTATE){
            drawPauseScreen();
        }else if (gp.gameState== GameState.GAMEOVER){
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text = "Game Over";
        // Shadow text
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.screenHeight/2;
        g2.drawString(text,x,y);
        // main text
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
