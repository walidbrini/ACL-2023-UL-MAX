package com.example;


import java.awt.Graphics2D; 
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
 

public  class Personnage
{
	public int pointsVie;
	public int positionX;
	public int positionY;
	GamePanel gp;

	public Rectangle solidArea=new Rectangle(0,0,48,48);

	public int solidAreaDefaultX,solidAreaDefaultY;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,mons;
	public String direction="down";
	public int spriteCounter =0;
	public int spriteNum=1;
	public boolean collisionOn=false;


	public Personnage(GamePanel gp ,int pointsVie, int positionX,int positionY)
	{
		this.gp=gp;
		this.pointsVie = pointsVie;
		this.positionX = positionX;
		this.positionY = positionY;
	}



	public int getPointsVie()
	{
		return pointsVie;
	}

	public void setPointsVie(int pointsVie)
	{
		this.pointsVie = pointsVie;
	}

	public void deplacerHaut()
	{
		if (this.positionY > 0)
			this.positionY -= gp.tileSize; 
		
	}

	public void deplacerBas()
	{
		if (this.positionY < (gp.maxScreenCol-1)*gp.tileSize)
			this.positionY += gp.tileSize;
		
	}

	public void deplacerGauche()
	{
		if (this.positionX > 0)
			this.positionX -= gp.tileSize;
	}

	public void deplacerDroite()
	{
		if (this.positionX < (gp.maxScreenRow-1)*gp.tileSize)
			this.positionX += gp.tileSize;
	}
	//setter a ajouter dans la classe case


	 public void winGame() {
	        System.out.println("Slaaaay!!!");
	        
	    }

	    public void takeDamage(int damage) {
	        pointsVie -= damage;
	        if (pointsVie <= 0) {
	            System.out.println("Game Over! Your character has run out of health.");
	             
	        }
	    }

	    public void heal(int healing) {
	        pointsVie += healing;
	        System.out.println("You have been healed for " + healing + " points.");
	    }
	    
	    public void interactWithSquare(Square square) {
	        Object squareContent = square.getContent();
	        
	        if (squareContent == Object.FIRE) {
	            takeDamage(10);  
	        } else if (squareContent == Object.AID) {
	            heal(20);  
	        }
	    }
	    
	    public boolean areInSameSquare(Personnage otherPersonnage) {
	        return this.positionX == otherPersonnage.positionX && this.positionY == otherPersonnage.positionY;
	    }

	    
	    
	    
}