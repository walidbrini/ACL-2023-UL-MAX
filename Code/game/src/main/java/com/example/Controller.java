package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	public boolean up,left,down,right,attaque;

	public void keyTyped(KeyEvent e) {			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();
		
		if (input == KeyEvent.VK_UP) {
			up=true;
		}
		if (input == KeyEvent.VK_DOWN) {
			down=true;
		}
		if (input == KeyEvent.VK_LEFT) {
			left=true;
		}
		if (input == KeyEvent.VK_RIGHT) {
			right=true;
		}
		if (input == KeyEvent.VK_SPACE) {
			attaque=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
        int input = e.getKeyCode();
		
		if (input == KeyEvent.VK_UP) {
			up=false;
		}
		if (input == KeyEvent.VK_DOWN) {
			down=false;
		}
		if (input == KeyEvent.VK_LEFT) {
			left=false;
		}
		if (input == KeyEvent.VK_RIGHT) {
			right=false;
		}
		if (input == KeyEvent.VK_SPACE) {
			attaque=false;
		}
	}
		
	

}