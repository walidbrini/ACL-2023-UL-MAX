package com.example;

public class Hero extends Personnage {
	

    public Hero(GamePanel gp, int pointsVie, int positionX, int positionY) {
        super(gp, pointsVie, positionX, positionY);
         
       
            }
    public void takeDamage(int damage) {
        pointsVie -= damage;
        if (pointsVie < 0) {
            pointsVie = 0; 
        }
    }

    
    public void heal(int healing) {
        pointsVie += healing;
         
    }

     
    public void teleport(Coordinates destination) {
         
        this.positionX = destination.getX();
        this.positionY = destination.getY();
    }

     
    public void interactWithCurrentSquare(Square currentSquare) {
        switch (currentSquare.getContent()) {
            case TREASURE:
                 
                System.out.println("Slaaay!");
                break;
            case FIRE:
                 
                int damage = 10;  
                takeDamage(damage);
                 
                break;
            case AID:
                   
                int healing = 20;   
                heal(healing);
                 
                break;
             
        }
    }

   
        

    }

