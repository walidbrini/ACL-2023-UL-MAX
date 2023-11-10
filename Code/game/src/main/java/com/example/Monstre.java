package com.example;


 

public class Monstre extends Personnage {
	private Hero hero;
	private Labyrinth labyrinth;
    
    
	public Monstre(GamePanel gp, int pointsVie, int positionX, int positionY,Hero hero,Labyrinth labyrinth) {
        super(gp, pointsVie, positionX, positionY);
        this.hero = hero;
        this.labyrinth = labyrinth;
}
	
	/*public void moveRandomly() {
        
        int currentX = positionX / gp.tileSize;
        int currentY = positionY / gp.tileSize;

         
        int heroX = hero.positionX / gp.tileSize;
        int heroY = hero.positionY / gp.tileSize;

         
        if (currentX < heroX) {
             
            deplacerDroite();
        } else if (currentX > heroX) {
             
            deplacerGauche();
        } else if (currentY < heroY) {
             
            deplacerBas();
        } else if (currentY > heroY) {
             
            deplacerHaut();
        }
    }
    */
	public void moveRandomly() {
	     
	    int currentX = positionX / gp.tileSize;
	    int currentY = positionY / gp.tileSize;

	     
	    int heroX = hero.positionX / gp.tileSize;
	    int heroY = hero.positionY / gp.tileSize;

	     
	    int diffX = heroX - currentX;
	    int diffY = heroY - currentY;

	    
	    if (Math.abs(diffX) > Math.abs(diffY)) {
	         
	        if (diffX > 0) {
	              
	            if (!isValidMove(currentX + 1, currentY)) {
	                deplacerDroite();
	            }
	        } else {   
	        	
	            if (!isValidMove(currentX - 1, currentY)) {
	                deplacerGauche();
	            }
	        }
	    } else {
	         
	        if (diffY > 0) {
	               
	            if (!isValidMove(currentX, currentY + 1)) {
	                deplacerBas();
	            }
	        } else {
	               
	            if (!isValidMove(currentX, currentY - 1)) {
	                deplacerHaut();
	            }
	        }
	    }
	}

	private boolean isValidMove(int x, int y) {
	       
	    if (x < 0 || x >= gp.maxScreenCol || y < 0 || y >= gp.maxScreenRow) {
	        return false;
	    }
         ObjectType content = gp.labyrinth.getGrid()[x][y].getContent();

	     return content != ObjectType.WALL &&  content!= ObjectType.FIRE;
	}


	
	
}

 

	


     