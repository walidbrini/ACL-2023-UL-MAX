package com.example;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.util.concurrent.TimeUnit;


public class GamePanel extends JPanel implements Runnable{
	private static Monstre monstre;  
	public final static int originalTileSize = 16;
	public final int scale = 3;
	public final int tileSize = scale * originalTileSize ;
	public static int maxScreenCol = 16;
	public static int maxScreenRow = 16;
	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;

	Thread thread;
	Controller control= new Controller();

	Labyrinth labyrinth = new Labyrinth(maxScreenCol,maxScreenRow,Difficulty.CHICKEN, this);

	Player player = new Player(this,control); // oth
	public Collision checker = new Collision(this,labyrinth);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(control); // Wait for key input
		this.setFocusable(true);

		// Print player position
		System.out.println(player.x) ;
		System.out.println(player.y) ;

		player.setPosition(labyrinth.getSpawn().getPosition().getX() * this.tileSize,
				           labyrinth.getSpawn().getPosition().getY() * this.tileSize);
	}

	public void startThread()  {
		thread = new Thread(this);  
		thread.start(); // Automatically call run()
		labyrinth.afficheVersionTexte();
	}
	
	public void run() {
		long interval=1000/60; // FPS
		long passTime =0;
		long oldTime = System.currentTimeMillis();
		long currentTime;
		
		while (thread != null){	
			currentTime = System.currentTimeMillis();
			passTime = currentTime - oldTime;
			if (passTime > interval ) {  //><
			update();
			repaint();
			oldTime = System.currentTimeMillis();
			}
		}
	}

	public void update() {
		if (checker.checkTreasure(player)){
			labyrinth.levelTransition();
			repaint();
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //labyrinth.setDifficulty(Difficulty.CHICKEN);
			labyrinth.generateRandomly(labyrinth.getTreasure().getPosition());
		}

		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		labyrinth.draw(g2);
		player.draw(g2);

		g2.dispose();
	}

	public int getTileSize() {
		return tileSize;
	}

	public static int getMaxScreenCol() {
		return maxScreenCol;
	}

	public static int getMaxScreenRow() {
		return maxScreenRow;
	}

	public static void setMonstre(Monstre monstre) {
		GamePanel.monstre = monstre;
	}

}