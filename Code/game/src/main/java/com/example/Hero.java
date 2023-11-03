package com.example;
 

public class Hero extends Personnage {
	 
    public Hero(GamePanel gp, int pointsVie, int positionX, int positionY) {
        super(gp, pointsVie, positionX, positionY);
         
        
    }
        
        public void heroWon(Square square) {
            Object squareContent = square.getContent();
            if (squareContent == Object.TREASURE) {
            	 winGame();
         
       
            }
        }
        public int calculateDamageFromMonster() {
   	     
    	    int constantDamage = 10;  

    	    return constantDamage;
    	}
        
        public void checkEncounterWithMonster(Monstre monstre) {
            if (areInSameSquare(monstre)) {
                 
                int damageFromMonster = calculateDamageFromMonster();  
                takeDamage(damageFromMonster);
            }
        }
    

     
     
    }

    
     
    

   
        

    

