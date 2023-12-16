package com.example;

import java.util.Iterator;

public class Collision{
    GamePanel gp;

    private long lastCollisionTime;
    private long collisionCooldown = 500; // 500 milliseconds
    private int fireCooldown = 500 ; 
    private long lastFireDamageTime = 0;

    public Collision(GamePanel gp) {
        this.gp=gp;
    }

    public void checkMonstre(Entity entity, MonsterSpawner monsterSpawner) {
        long currentTime = System.currentTimeMillis();
    
        if (currentTime - lastCollisionTime >= collisionCooldown) {
            int collisionRange = gp.getTileSize();
    
            Iterator<Monstre> iterator = monsterSpawner.getMonsters().iterator();
    
            while (iterator.hasNext()) {
                Monstre monster = iterator.next();
    
                if (Math.abs(entity.x - monster.x) <= collisionRange &&
                    Math.abs(entity.y - monster.y) <= collisionRange) {
                    if (this.gp.player.getKeyH().attaque) {
                        // delete monster if hit
                        iterator.remove();
                    }
    
                    if (Math.abs(entity.x - monster.x) <= collisionRange / 2 &&
                            Math.abs(entity.y - monster.y) <= collisionRange / 2) {
                        if (entity.life > 0) {
                            entity.life--;
                        }
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

    public void  checkProjectile(Projectile projectile, MonsterSpawner m) {
        int collisionRange = gp.getTileSize();
    
        if (projectile.alive) {
            Iterator<Monstre> iterator = m.getMonsters().iterator();
    
            while (iterator.hasNext()) {
                Monstre monster = iterator.next();
    
                if (Math.abs(projectile.x - monster.x) <= collisionRange &&
                    Math.abs(projectile.y - monster.y) <= collisionRange) {
    
                    if (monster.life > 0) {
                        monster.life = 0;
                        monster.alive = false;
                        iterator.remove(); // Use Iterator's remove method
                        gp.player.addKills();
                    }
                }
            }
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
        if (entity.solidArea.intersects(obj.solidArea)) {
            if (obj.getContent() == ObjectType.FIRE) {
                long currentTime = System.currentTimeMillis();
                // Check if enough time has passed since the last fire damage
                if (currentTime - lastFireDamageTime >= fireCooldown) {
                    if (entity.life > 0) {
                        entity.life--;
                        gp.playSE(1, 1.0f);
                        // Update the last fire damage time
                        lastFireDamageTime = currentTime;
                    }
                }
            } else if (obj.getContent() == ObjectType.BOOTS) {
                l.setSquare(abs, ord, ObjectType.WALKWAY);
                entity.speedBoosted = true;
                entity.originalSpeed = entity.speed;
                entity.speed = entity.boostValue; // Set the speed to the boosted value
                gp.speedBoostStartTime = System.currentTimeMillis();
            } else if (obj.getContent() == ObjectType.AID) {
                if (entity.life < entity.maxLife) {
                    if (entity.life == entity.maxLife - 1) {
                        entity.life++;
                        gp.playSE(2, 1.0f);
                    } else {
                        entity.life += 2;
                        gp.playSE(2, 1.0f);
                    }
                    l.setSquare(abs, ord, ObjectType.WALKWAY);
                }
            }
            else if (obj.getContent() == ObjectType.MANA ) {
                if (entity.mana < entity.maxMana) {
                    entity.mana ++;
                    l.setSquare(abs, ord,ObjectType.WALKWAY);
                }

            }

        }
        
    }

    public boolean checkTreasure(Entity entity,Labyrinth labyrinth){
        // Check if the player has reached the treasure
        return ((entity.x + entity.solidArea.height)/gp.getTileSize() == labyrinth.getTreasure().getPosition().getX() && (entity.y+entity.solidArea.width)/gp.getTileSize() == labyrinth.getTreasure().getPosition().getY());
    }
    public void checkboostSpeedForDuration(Entity entity,long durationMillis){
        durationMillis = durationMillis * 1000;
        if (gp.currentTime - gp.speedBoostStartTime >= durationMillis){
            // Boost duration has elapsed, revert to original speed
            entity.speed = 4;
            entity.speedBoosted = false;// Reset speed boost flag
        }
    }

}