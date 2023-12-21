import com.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

class PlayerTest {

    private GamePanel gp;
    private Player player;
    private Labyrinth l;
    private Controller control ; 

    @BeforeEach
    void setup() throws IOException{
        // Initialize GamePanel, Player, and Labyrinth before each test
        gp = new GamePanel();
        player = gp.getPlayer();
        l = gp.getLabyrinth();
        gp.getController(); 

    }

    @Test
    void testPlayerSpawnNotOnWall() {
        Square[][] grid = l.getGrid(); 
        ObjectType squareContent = grid[l.getSpawn().getPosition().getX()][l.getSpawn().getPosition().getY()].getContent();
        assertNotEquals(ObjectType.WALL, squareContent);
    }

    @Test
    void testPlayerSpawnNotOnFire() {
        Square[][] grid = l.getGrid(); 
        ObjectType squareContent = grid[l.getSpawn().getPosition().getX()][l.getSpawn().getPosition().getY()].getContent();
        assertNotEquals(ObjectType.FIRE, squareContent);
    }   

    // Check player up movement
    void testPlayerMoveUp() {
        int oldY = player.getY();
        
        control.up =  true;
    
        player.update();
    
        control.up = false;
    
        if (!player.collisionOn) {
            assertEquals(oldY + 1, player.getY(), "Player should move up");
        } else {
            assertEquals(oldY, player.getY(), "Player should not move due to collision");
        }
    }
    // Check player Down movement
    @Test
    void testPlayerMoveDown() {
        int oldY = player.getY();
        
        // Simulate click of down arrow
        control.down =  true;
    
        player.update();
    
        control.down = false;
    
        if (!player.collisionOn) {
            // Check if the player's y position decreased by 1
            assertEquals(oldY - 1, player.getY(), "Player should move down");
        } else {
            // Assert that the position did not change due to collision
            assertEquals(oldY, player.getY(), "Player should not move due to collision");
        }
    }

    // Check pkayer right movement 
   @Test
    void testPlayerMoveRight() {
        int oldX = player.getX();
        
        // Simulate click of down arrow
        control.right =  true;
    
        player.update();
    
        control.right = false;
    
        if (!player.collisionOn) {
            assertEquals(oldX + 1 , player.getX(), "Player should move down");
        } else {
            assertEquals(oldX, player.getX(), "Player should not move due to collision");
        }
    }

    // Check player Left movement 
   @Test
    void testPlayerMoveLeft() {
        int oldX = player.getX();
        
        // Simulate click of right
        control.left =  true;
    
        player.update();
    
        control.left = false;
    
        if (!player.collisionOn) {
            assertEquals(oldX - 1 , player.getX(), "Player should move down");
        } else {
            assertEquals(oldX, player.getX(), "Player should not move due to collision");
        }
    }
    

}
