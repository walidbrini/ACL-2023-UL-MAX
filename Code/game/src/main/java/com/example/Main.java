package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

import com.example.GamePanel ;

public class Main

{
	//static JButton jouer = new JButton("d�marrer la partie");
	static GamePanel game = new GamePanel();
	public static void main(String[] args) throws IOException {
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
 
	}
		
}
	

