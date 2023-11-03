package com.example;

 

public class Monstre extends Personnage {
	private Hero hero;
	private Labyrinth labyrinth;
    
    
	public Monstre(GamePanel gp, int pointsVie, int positionX, int positionY,Hero hero,Labyrinth labyrinth) {
        super(gp, pointsVie, positionX, positionY);
        this.hero = hero;
        this.labyrinth = labyrinth;
}
	
	public void moveRandomly() {
        
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
	
	
}

 

	


     