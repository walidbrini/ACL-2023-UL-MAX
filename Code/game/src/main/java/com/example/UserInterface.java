package com.example;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class UserInterface extends Utilities{
    private GamePanel gp;
    private Graphics2D g2;
    private Font arial_40,arial_80B,arial_30;
    private String message ="";
    private boolean messageOn = false;
    private int messageCounter = 0;
    private boolean buttonAdded = false;
    private boolean button2Added = false;
    private Button saveButton = new Button("Save Game");
    private Button quitButton = new Button("Quit Game");
    private boolean startNewGameButtonClicked = false;

    // Cursor position in the window
    public int slotCol = 0;
    public int slotRow = 0;
    private Button startnewGameButton = new Button("Start New Game");
    private Button continueGameButton = new Button("Continue");

    public UserInterface(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_30 = new Font("Arial",Font.PLAIN,30);
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
        else if(gp.gameState == GameState.CHARACTER_STATUS){
            drawCharacterScreen();
            //drawInventory();
        }
        else if(gp.gameState == GameState.START_MENU){
            drawStartMenu();
            drawInventory();
        }
    }

    private void drawStartMenu() {
        if (!startNewGameButtonClicked) {
            buttonAdded = addButton(continueGameButton, buttonAdded, -1, gp.screenHeight/4);
            button2Added = addButton(startnewGameButton, button2Added, -1, gp.screenHeight / 10);
    
            // Add a MouseListener to the continueGameButton for click events
            continueGameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    gp.remove(continueGameButton);
                    gp.remove(startnewGameButton);
                    try {
                        gp.level.continueGame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    gp.setGameState(GameState.PAUSESTATE);
                    button2Added = false;
    
                    // Set focus explicitly to the GamePanel component
                    gp.requestFocus();
                }
            });
    
            // Add a MouseListener to the startnewGameButton for click events
            startnewGameButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!startNewGameButtonClicked) {
                        gp.remove(startnewGameButton);
                        gp.remove(continueGameButton);
                        gp.level.startNewGame();
                        gp.setGameState(GameState.PLAYSTATE);
                        button2Added = false;
                        buttonAdded = false;
                        startNewGameButtonClicked = true;
    
                        // Set focus explicitly to the GamePanel component
                        gp.requestFocus();
                    }
                }
            });
        }
    }
    public void drawCharacterScreen(){
        final int x = gp.tileSize * 2 ;
        final int y = gp.tileSize *3 ;
        final int w = gp.tileSize * 7 ;
        final int h = gp.tileSize * 10 ;
        drawSubWindow(x,y,w,h);

        // TEXT

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = x+ 20;
        int textY = y +gp.tileSize;
        final int lineHeight = 32;

        g2.drawString("Level",textX,textY);
        textY +=lineHeight;
        g2.drawString("Life",textX,textY);
        textY +=lineHeight;
        g2.drawString("Mana",textX,textY);
        textY +=lineHeight;
        g2.drawString("Speed",textX,textY);
        textY +=lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY +=lineHeight;
        g2.drawString("Kills",textX,textY);
        textY +=lineHeight + 20;
        g2.drawString("Weapon ",textX,textY);
        textY +=lineHeight + 15 ;
        g2.drawString("Shield",textX,textY);
        textY +=lineHeight + 15 ;

        //VALUES
        g2.setFont(arial_30);
        g2.setFont(g2.getFont().deriveFont(20F));
        int edge = (x+w) - 30;
        textY = y +gp.tileSize;
        String value;

        value = getLevel(gp.labyrinth.getDifficulty());
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.getSpeed());
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        value = getLevel(gp.labyrinth.getDifficulty().next());
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        value = String.valueOf(gp.player.getKills());
        textX = getXforAlignToRightText(value,edge);
        g2.drawString(value,textX,textY);
        textY +=lineHeight;

        BufferedImage weapon = setupImage("/items/axe.png");
        g2.drawImage(weapon,edge-gp.tileSize,textY-15,gp.getTileSize(), gp.getTileSize(),null);
        textY+= gp.tileSize;

        BufferedImage shield = setupImage("/items/shield_wood.png");
        g2.drawImage(shield,edge-gp.tileSize,textY-15,gp.getTileSize(), gp.getTileSize(), null);
        textY+= gp.tileSize;
    }
    public void drawInventory(){
        int x = gp.getTileSize()*6;
        int y = gp.getTileSize()*8;
        int width = gp.getTileSize()*4;
        int height = gp.getTileSize()*5;
        drawSubWindow(x,y,width,height);

        //SLOT
        final int initialSlotX = x + 20 ;
        final int initialSlotY = y + 20 ;
        int slotX = initialSlotX;
        int slotY = initialSlotY;

        // DRAW PLAYER ITEMS
        for (int i = 0 ; i<gp.player.inventory.size();i++){
            g2.drawImage(gp.player.inventory.get(i).left1 ,slotX,slotY,gp.getTileSize(),gp.getTileSize(),null);
            slotX += gp.getTileSize();

            if(i==2 || i==5 || i==8){
                slotX = initialSlotX ;
                slotY += gp.getTileSize();
            }
        }
        slotX = initialSlotX ;
        slotY += gp.getTileSize();
        BufferedImage img1;
        img1 =setupImage("/player/player1/down/down1.png");
        g2.drawImage(img1,slotX,slotY,gp.getTileSize(),gp.getTileSize(),null);
        slotX += gp.getTileSize();
        img1 = setupImage("/player/player2/down/down1.png");
        g2.drawImage(img1,slotX,slotY,gp.getTileSize(),gp.getTileSize(),null);
        slotX += gp.getTileSize();
        img1 = setupImage("/player/player3/down/down1.png");
        g2.drawImage(img1,slotX,slotY,gp.getTileSize(),gp.getTileSize(),null);
        slotX += gp.getTileSize();
        slotX = initialSlotX ;
        slotY += gp.getTileSize();
        img1 = setupImage("/player/player4/down/down1.png");
        g2.drawImage(img1,slotX,slotY,gp.getTileSize(),gp.getTileSize(),null);
        slotX += gp.getTileSize();

        //CURSOR
        int cursorX = initialSlotX + (gp.getTileSize() * slotCol);
        int cursorY = initialSlotY + (gp.getTileSize() * slotRow);
        int cursorW = gp.getTileSize();
        int cursorH = gp.getTileSize();
        // DRAW CURSOR
        g2.setColor (Color.white);
        g2.setStroke(new BasicStroke(3)); // thickness of the line
        g2.drawRoundRect(cursorX,cursorY,cursorW,cursorH,10,10);
    }

    public String getLevel(Difficulty d){
        String level = null;
        switch (d){
            case CHICKEN:
                level = "CHICKEN";
                break;
            case EASY:
                level = "EASY";
                break;
            case MEDIUM:
                level = "MEDIUM";
                break;
            case HARD:
                level = "HARD";
                break;
            case INSANE:
                level = "INSANE";
                break;
            default:
                break;
        }
        return level;
    }
    public void drawSubWindow(int x , int y , int width , int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
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

        buttonAdded = addButton(saveButton, buttonAdded, -1, y + 40);
        button2Added = addButton(quitButton, button2Added, -1, y + 170);

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
                gp.remove(saveButton);
                try {
                    gp.level.saveGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Add a MouseListener to the button for click events
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
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
        gp.remove(quitButton);
        buttonAdded = false;
        button2Added = false;
        gp.level.setGameSaved(false);
    }

    public int getXforAlignToRightText(String text,int edge){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = edge - length;
        return x;
    }

    private boolean addButton(Button button, Boolean buttonAdded, int x, int y){
        int width = 200;
        int height = 100;

        // Chose x = -1 to center the button
        if(x == -1)
            x = (gp.screenWidth - width) / 2;

        button.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        button.setBackground(Color.white);

        button.setBounds(x, y, width, height);

        // Add the button to the GamePanel
        if(!buttonAdded){
            gp.add(button);
            buttonAdded = true;
        }
        return buttonAdded;
    }

    // GETTERS and SETTERS
    public int getSlotCol() {
        return slotCol;
    }
    public void setSlotCol(int slotCol) {
        this.slotCol = slotCol;
    }
    public int getSlotRow() {
        return slotRow;
    }
    public void setSlotRow(int slotRow) {
        this.slotRow = slotRow;
    }
}