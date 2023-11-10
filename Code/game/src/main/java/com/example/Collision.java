package com.example;

import com.example.Entity;
import com.example.Labyrinth;
import com.example.Square;

public class Collision{

    GamePanel gp;
    Labyrinth l;
    public Collision(GamePanel gp ,Labyrinth l){
        this.l=l;
        this.gp=gp;
    }
    public void checkSquare(Entity entity){

        int entityLeftx = entity.x+ entity.solidArea.x;
        int entityRightx = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopy = entity.y + entity.solidArea.y ;
        int entityBottomy = entity.y + entity.solidArea.y + entity.solidArea.height;

        //Conversion en coordonnées (row,col)
        int entityLeftCol = entityLeftx/ gp.tileSize;
        int entityRightCol = entityRightx/ gp.tileSize;
        int entityTopRow = entityTopy/ gp.tileSize;
        int entityBottomRow = entityBottomy/ gp.tileSize;

        Square corner1 = new Square();
        Square corner2 = new Square();
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopy-entity.speed)/gp.tileSize;
                corner1 = l.getGrid()[entityLeftCol][entityTopRow];
                corner2 = l.getGrid()[entityRightCol][entityTopRow];
                if(corner1.collision  || corner2.collision  ){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomy+entity.speed)/gp.tileSize;
                corner1 = l.getGrid()[entityLeftCol][entityBottomRow];
                corner2 = l.getGrid()[entityRightCol][entityBottomRow];
                if(corner1.collision  || corner2.collision  ){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftx-entity.speed)/gp.tileSize;
                corner1 = l.getGrid()[entityLeftCol][entityTopRow];
                corner2 = l.getGrid()[entityLeftCol][entityBottomRow];
                if(corner1.collision  || corner2.collision  ){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol = (entityRightx+entity.speed)/gp.tileSize;
                corner1 = l.getGrid()[entityRightCol][entityTopRow];
                corner2 = l.getGrid()[entityRightCol][entityBottomRow];
                if(corner1.collision  || corner2.collision  ){
                    entity.collisionOn=true;
                }
                break;

        }

    }
    public int checkObject(Entity entity , boolean player){

        int index = 999;
        switch(entity.direction){
            case "up":
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
        }
        return index ;

    }
}