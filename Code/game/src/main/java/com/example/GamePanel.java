package com.example;

import java.awt.Color;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JLabel;
import javax.swing.JPanel;
import com.example.Monstre;
import com.example.Controller;
import com.example.Player;


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

	Labyrinth labyrinth = new Labyrinth(maxScreenCol,maxScreenRow,Difficulty.INSANE, this);

	Player player = new Player(this,control); // oth
	public Collision checker = new Collision(this,labyrinth);
	 
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(control); // Wait for key input
		this.setFocusable(true);
		System.out.println(labyrinth.getSpawn().getPosition().getX()) ;
		System.out.println(labyrinth.getSpawn().getPosition().getY()) ;
		System.out.println(player.x) ;
		System.out.println(player.y) ;

		player.x = labyrinth.getSpawn().getPosition().getX() * this.tileSize;
		player.y = labyrinth.getSpawn().getPosition().getY() * this.tileSize;
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
			//labyrinth.setDifficulty(Difficulty.CHICKEN);
			labyrinth.generateRandomly();
			player.setPosition(labyrinth.getSpawn().getPosition().getX() * this.tileSize, labyrinth.getSpawn().getPosition().getY() * this.tileSize);
		}

		player.update();
		// Commented it because it was causing a NullPointer Exception - Moemen
		// monstre.moveRandomly();
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