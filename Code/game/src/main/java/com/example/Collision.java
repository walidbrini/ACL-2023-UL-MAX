package com.example;

public class Collision{
    GamePanel gp;

    private long lastCollisionTime;
    private long collisionCooldown = 500; // 500 milliseconds 
    
    public Collision(GamePanel gp) {
        this.gp=gp;
    }

    public void checkMonstre(Entity entity, MonsterSpawner monsterSpawner) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastCollisionTime >= collisionCooldown) {
            int collisionRange = gp.getTileSize();

            for (Monstre monster : monsterSpawner.getMonsters()) {
                if (Math.abs(entity.x - monster.x) <= collisionRange &&
                    Math.abs(entity.y - monster.y) <= collisionRange) {
                    System.out.println("Player-Monster : DAMAGEEEE");
                    if (entity.life > 0) {
                        entity.life--;
                    }

                    // Update the last collision time
                    lastCollisionTime = currentTime;
                }
            }
        }
    }
    
    

    public void checkSquare(Entity entity,Labyrinth l){

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

    public void checkObject(Entity entity , Labyrinth l ,boolean player){
        int abs , ord;
        Square obj = new Square();
        abs = ((entity.x + entity.solidArea.x + entity.solidArea.width/2))/gp.tileSize ;
        ord = ((entity.y +entity.solidArea.y + entity.solidArea.height/2))/gp.tileSize ;
        switch(entity.direction){
            case "up":
                ord = (entity.y +entity.solidArea.y) / gp.tileSize ;
                obj = l.getGrid()[abs][ord];
                break;
            case "down":
                ord = (entity.y +entity.solidArea.y + entity.solidArea.height) / gp.tileSize ;
                obj = l.getGrid()[abs][ord];
                break;
            case "left":
                abs = (entity.x +entity.solidArea.x + entity.solidArea.width) / gp.tileSize ;
                obj = l.getGrid()[abs][ord];
                break;
            case "right":
                abs = (entity.x + entity.solidArea.x) / gp.tileSize ;
                obj = l.getGrid()[abs][ord];
                break;
        }
        if(entity.solidArea.intersects(obj.solidArea)){
            if (obj.getContent() == ObjectType.FIRE ){
                //System.out.println(entity.direction + " collision !");
                if (entity.life > 0)
                    entity.life--;
                    gp.playSE(1,1.0f);
                    System.out.println("Damage taken");
            }
            else if (obj.getContent() == ObjectType.AID ){
                if(entity.life < entity.maxLife){
                    if(entity.life == entity.maxLife - 1){
                        entity.life = entity.life + 1;
                        gp.playSE(2,1.0f);
                        System.out.println("potion taken");
                    }
                    else {
                        entity.life = entity.life + 2;
                        gp.playSE(2, 1.0f);
                        System.out.println("potion taken");
                    }
                    l.setSquare(abs, ord,ObjectType.WALKWAY);
                }
            }
        }
    }

    public boolean checkTreasure(Entity entity,Labyrinth labyrinth){
        // Check if the player has reached the treasure
        return ((entity.x + entity.solidArea.height)/gp.getTileSize() == labyrinth.getTreasure().getPosition().getX() && (entity.y+entity.solidArea.width)/gp.getTileSize() == labyrinth.getTreasure().getPosition().getY());
    }
}