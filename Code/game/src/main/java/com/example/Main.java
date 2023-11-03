package com.example;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame; 



public class Main

{
	//static JButton jouer = new JButton("d�marrer la partie");
	static GamePanel game =new GamePanel();
	public static void main(String[] args) 
	{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("ACL_MAX");
		
		window.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;

		
		window.add(game);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		window.add(game,c);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		game.startThread();
		
		 
	    //Labyrinth labyrinth = new Labyrinth(10, 10, Level.EASY, game );
	  //  Hero hero = new Hero(game , 100, 2, 2);  
	  //  Monstre monstre = new Monstre(game , 50, 2, 2,hero,labyrinth);  

		 
	   // GamePanel.setMonstre(monstre); 
	    //hero.checkEncounterWithMonster(monstre);

	     
	   // System.out.println("Points de vie du héros : " + hero.getPointsVie());
	}
		
	}
	

