package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;




public class GamePanel extends JPanel implements Runnable{
	public final static int originalTileSize = 16;
	public final int scale = 3;
	public final int tileSize = scale * originalTileSize ;
	public static int maxScreenCol = 16;
	public static int maxScreenRow = 16;
	final int screenWidth = tileSize * maxScreenCol ;
	final int screenHeight = tileSize * maxScreenRow ;
	private final static String fileSaveLocation = "save_files/map.txt";

	Thread thread;
	Controller control= new Controller(this);
	Labyrinth labyrinth;
	Level level = new Level(this);
	Sound sound = new Sound();
	UserInterface ui = new UserInterface(this);
	Player player = new Player(this,control); // oth
	Ghost fantome = new Ghost(this);

	ArrayList<Projectile> projectileList = new ArrayList<>();
	MonsterSpawner monsterSpawner;
	public long speedBoostStartTime ;
	public long currentTime ;

	public Collision checker = new Collision(this);

	// GAME STATE
	GameState gameState ;

	public GamePanel() throws IOException {

		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(control); // Wait for key input
		this.setFocusable(true);

		setupGame(1f);

		monsterSpawner = new MonsterSpawner(this);

	}
	public void setupGame(float volume) throws IOException {
		playMusic(3,volume);
		level.startNewGame();
		gameState = GameState.PLAYSTATE;

	}
	public void startThread() throws IOException{
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
		if (gameState == GameState.PLAYSTATE){
			level.update();
			player.update();
			fantome.update();
			currentTime = System.currentTimeMillis();
			checker.checkboostSpeedForDuration(player,5);
			for (Monstre monster : monsterSpawner.getMonsters()) {
				monster.update();
			}
		}
		else if (gameState == GameState.PAUSESTATE){
			// nothing
		}
		else if(gameState == GameState.RESTART){
			level.restart();
			player.restoreMana();
			gameState = GameState.PLAYSTATE;
		}
		for (int i =0;i < projectileList.size();i++){
			if(projectileList.get(i) != null){
				if (projectileList.get(i).alive == true){
					projectileList.get(i).update();
				}
				if(projectileList.get(i).alive == false){
					projectileList.remove(i);
				}
			}
		}

        /*
		for (int j =0;j < monsterSpawner.getMonsters().size();j++){
			if(monsterSpawner.getMonsters().get(j) != null){
				if (monsterSpawner.getMonsters().get(j).alive == true){
					monsterSpawner.getMonsters().get(j).update();
				}
				if(monsterSpawner.getMonsters().get(j).alive == false){
					monsterSpawner.getMonsters().remove(j);
				}
			}
		}

         */
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		labyrinth.draw(g2);
		player.drawPlayer(g2,this);
		fantome.appear(g2,this);
		for (Monstre monster : monsterSpawner.getMonsters()) {
			monster.draw(g2,this);
		}
		ui.draw(g2);

		for (int i = 0 ; i<projectileList.size() ; i++){
			if(projectileList.get(i) != null){
				projectileList.get(i).draw(g2,this);
			}
		}
		g2.dispose();
	}
	public void playMusic(int i,float volume){
		sound.setFile(i);
		sound.play();
		sound.loop();
		sound.setVolume(volume);
	}
	public void stopMusic(){
		sound.stop();
	}
	public void playSE(int i,float volume){
		sound.setFile(i);
		sound.play();
		sound.setVolume(volume);
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

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Labyrinth getLabyrinth (){ 
		return labyrinth;
	}
	public Player getPlayer(){
		return player ; 
	}
	public Controller getController(){
		return control; 
	}
}