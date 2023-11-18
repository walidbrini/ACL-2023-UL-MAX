package com.example;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;




public class GamePanel extends JPanel implements Runnable{
	public final static int originalTileSize = 16;
	public final int scale = 3;
	public final int tileSize = scale * originalTileSize ;
	public static int maxScreenCol = 16;
	public static int maxScreenRow = 16;
	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;

	Thread thread;
	Controller control= new Controller();
	Level level = new Level(this, Difficulty.MEDIUM);
	Labyrinth labyrinth = new Labyrinth(maxScreenCol,maxScreenRow,Difficulty.MEDIUM,this);
	Sound sound = new Sound();
	Player player = new Player(this,control); // oth

	MonsterSpawner monsterSpawner;

	public Collision checker = new Collision(this);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(control); // Wait for key input
		this.setFocusable(true);

		setupGame(0.3f);

		// Print player position
		System.out.println(player.x) ;
		System.out.println(player.y) ;

		player.setPosition(labyrinth.getSpawn().getPosition().getX() * this.tileSize,
				           labyrinth.getSpawn().getPosition().getY() * this.tileSize);

		monsterSpawner = new MonsterSpawner(this);
        monsterSpawner.spawnMonsters(10);

	}
	public void setupGame(float volume){
		playMusic(3);
		sound.setVolume(volume);
	}
	public void startThread() throws IOException{
		thread = new Thread(this);
		thread.start(); // Automatically call run()
		labyrinth.afficheVersionTexte();

		labyrinth.saveToFile();
		//labyrinth.loadFromFile("res/map/map01.txt");
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
		level.update();
		player.update();
		
		for (Monstre monster : monsterSpawner.getMonsters()) {
            monster.update();
        }
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		labyrinth.draw(g2);
		player.draw(g2);
		for (Monstre monster : monsterSpawner.getMonsters()) {
            monster.draw(g2);
        }
		g2.dispose();
	}
	public void playMusic(int i){
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic(){
		sound.stop();
	}
	public void playSE(int i){
		sound.setFile(i);
		sound.play();
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

	
}