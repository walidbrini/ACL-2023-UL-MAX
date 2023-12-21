package com.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80B;
    public String message ="";
    public boolean messageOn = false;
    int messageCounter = 0;
    boolean buttonAdded = false;
    Button saveButton = new Button("Save Game");

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

        }
        else if(gp.gameState == GameState.PAUSESTATE){
            drawPauseScreen();
        }
        else if (gp.gameState== GameState.GAMEOVER){
            drawGameOverScreen();
        }
        else if (gp.gameState == GameState.WIN){
            drawWinGameScreen();
        }
    }

    private void drawWinGameScreen() {
        // Draw a semi-transparent overlay
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Display "You Won!" message
        String winMessage = "You Won!";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        g2.setColor(Color.white);
        int x = getXforCenteredText(winMessage);
        int y = gp.screenHeight / 2 - 40;
        g2.drawString(winMessage, x, y);

        // Play Again button
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        playAgainButton.setBackground(Color.white);
        int buttonWidth = 200;
        int buttonHeight = 100;
        int buttonX = (gp.screenWidth - buttonWidth) / 2;
        int buttonY = y + 40;
        playAgainButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        // Add the button to the GamePanel
        if(!buttonAdded){
            gp.add(playAgainButton);
            buttonAdded = true;
        }

        // Add a MouseListener to the button for click events
        playAgainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gp.setGameState(GameState.RESTART);
                gp.remove(playAgainButton);
                buttonAdded = false;
            }
        });
    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        // Display "Game Over" message
        String gameOverMessage = "Game Over";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        g2.setColor(Color.white);
        int x = getXforCenteredText(gameOverMessage);
        int y = gp.screenHeight / 2 - 40;
        g2.drawString(gameOverMessage, x, y);

        // Retry button
        Button retryButton = new Button("Restart Game");
        retryButton.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        retryButton.setBackground(Color.white);
        int buttonWidth = 200;
        int buttonHeight = 100;
        int buttonX = (gp.screenWidth - buttonWidth) / 2;
        int buttonY = y + 40;
        retryButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        // Add the button to the GamePanel
        if(!buttonAdded){
            gp.add(retryButton);
            buttonAdded = true;
        }

        // Add a MouseListener to the button for click events
        retryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gp.setGameState(GameState.RESTART);
                gp.remove(retryButton);
                buttonAdded = false;
            }
        });

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);

        // Save button
        saveButton.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        saveButton.setBackground(Color.white);
        int buttonWidth = 200;
        int buttonHeight = 100;
        int buttonX = (gp.screenWidth - buttonWidth) / 2;
        int buttonY = y + 40;
        saveButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        // Add the button to the GamePanel
        if(!buttonAdded){
            gp.add(saveButton);
            buttonAdded = true;
        }

        if(gp.level.isGameSaved()){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
            text = "game saved successfully";
            x = getXforCenteredText(text);
            y = gp.screenHeight/2 + 90;
            g2.drawString(text,x,y);
        }

        // Add a MouseListener to the button for click events
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //gp.setGameState(GameState.PLAYSTATE);
                gp.remove(saveButton);
                try {
                    gp.level.save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void removeSaveButton(){
        gp.remove(saveButton);
        buttonAdded = false;
        gp.level.setGameSaved(false);
    }
}
