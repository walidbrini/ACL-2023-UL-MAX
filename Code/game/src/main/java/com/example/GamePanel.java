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
	public final int scale = 3 ; 
	public final int tileSize = scale * originalTileSize ;  

	public static int maxScreenCol = 16;
	public static int maxScreenRow = 16;

	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;

	Labyrinth labyrinth = new Labyrinth(maxScreenCol,maxScreenRow,Level.MEDIUM, this);
	Personnage p1 = new Personnage(this,100,labyrinth.spawn.getPosition().getX()*tileSize,labyrinth.spawn.getPosition().getY()*tileSize);


	Thread thread;
	Controller control= new Controller();
	Player player = new Player(this,control,labyrinth); // oth
	 
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(control); // Wait for key input
		this.setFocusable(true);
		System.out.println(labyrinth.spawn.getPosition().getX()) ;
		System.out.println(labyrinth.spawn.getPosition().getY()) ;
		//System.out.println(p1.positionX) ;
		//System.out.println(p1.positionY) ;
		System.out.println(player.x) ;
		System.out.println(player.y) ;
	}
	
	public static void setMonstre(Monstre monstre) {
        GamePanel.monstre = monstre;
    }


	public int getTileSize() {
		return tileSize;
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

		/*
		if (Control.up){
			p1.deplacerHaut();
			Control.up=false ; 
			System.out.println(p1.positionY);
		}
		if (Control.down){
			p1.deplacerBas();
			Control.down = false ; 
			System.out.println(p1.positionY);
		}
		if (Control.right){
			p1.deplacerDroite();
			Control.right=false ; 
			System.out.println(p1.positionX);
		}
		if (Control.left){
			p1.deplacerGauche();
			Control.left= false ; 
			System.out.println(p1.positionX);
		}
		*/
		player.update();
		// Commented it because it was causing a NullPointer Exception - Moemen
		// monstre.moveRandomly();
		 
		
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		labyrinth.draw(g2);

		//g2.setColor(Color.BLACK);
		//g2.fillRect(p1.positionX, p1.positionY, tileSize, tileSize);
		player.draw(g2);
		g2.dispose();


	}
	
	
	
}