package com.example;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	GamePanel gp;
	public boolean up,left,down,right,attaque,shoot;
	public int weapon = 1;
	public int play = 1 ;

	public Controller(GamePanel gp){
		this.gp =gp;
	}
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int input = e.getKeyCode();

		// GAME STATE
		if (gp.gameState == GameState.PLAYSTATE) {
			if (input == KeyEvent.VK_UP) {
				up = true;
			}
			if (input == KeyEvent.VK_DOWN) {
				down = true;
			}
			if (input == KeyEvent.VK_LEFT) {
				left = true;
			}
			if (input == KeyEvent.VK_RIGHT) {
				right = true;
			}
			if (input == KeyEvent.VK_SPACE) {
				attaque = true;
			}
			if (input == KeyEvent.VK_F) {
				shoot = true;
			}
			if (input == KeyEvent.VK_P) {
				gp.gameState = GameState.PAUSESTATE;
			}
			if (input == KeyEvent.VK_C) {
				gp.gameState = GameState.CHARACTER_STATUS;
			}
		}
		// PAUSE STATE
		else if (gp.gameState == GameState.PAUSESTATE) {
			if (input == KeyEvent.VK_P) {
				gp.ui.removeSaveButton();
				gp.gameState = GameState.PLAYSTATE;
			}
		}
		// CHARACTER STATE
		else if (gp.gameState == GameState.CHARACTER_STATUS) {
			if (input == KeyEvent.VK_C) {
				gp.gameState = GameState.PLAYSTATE;
			}
		}
		else if (gp.gameState == GameState.START_MENU){

			if( input == KeyEvent.VK_UP){
				if (gp.ui.slotRow != 0){
					gp.ui.slotRow--;
				}
			}
			if( input == KeyEvent.VK_DOWN){
				if (gp.ui.slotRow != 3){
					gp.ui.slotRow++;
				}
			}
			if( input == KeyEvent.VK_LEFT){
				if (gp.ui.slotCol !=0){
					gp.ui.slotCol--;
				}
			}
			if( input == KeyEvent.VK_RIGHT){
				if (gp.ui.slotCol != 2){
					gp.ui.slotCol++;
				}
			}
			if(input == KeyEvent.VK_SPACE){
				if (gp.ui.slotCol == 0 && gp.ui.slotRow == 0){
					weapon = 1;
				}
				if (gp.ui.slotCol == 1 && gp.ui.slotRow == 0){
					weapon = 2;
				}
				if (gp.ui.slotCol == 0 && gp.ui.slotRow == 1){
					play = 1;
				}
				if (gp.ui.slotCol == 1 && gp.ui.slotRow == 1){
					play = 2;
				}
				if (gp.ui.slotCol == 2 && gp.ui.slotRow == 1){
					play = 3;
				}
				if (gp.ui.slotCol == 0 && gp.ui.slotRow == 2){
					play = 4;
				}

			}
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
		if (input == KeyEvent.VK_F) {
			shoot=false;
		}
	}

}