package com.example;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JLabel;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
	public final static int originalTileSize = 16; 
	public final int scale = 3 ; 
	public final int tileSize = scale * originalTileSize ;  

	public static int maxScreenCol = 16;
	public static int maxScreenRow = 12; 

	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;
	
	

		
	Thread thread;
	Controller Control= new Controller();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(Control); // Wait for key input
		this.setFocusable(true);
	}
	
	public void startThread()  {     
		thread = new Thread(this);  
		thread.start(); // Automatically call run()
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
		
		
	}

	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
	}
	
	
	
}