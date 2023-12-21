import com.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

public class MonsterTest {

    private GamePanel gp;
    private Monstre monster;

    @BeforeEach
    void setup() throws IOException {
        // Initialize GamePanel and Monstre before each test
        gp = new GamePanel();
        monster = new Monstre(gp);
    }
    
    // Tester si la position de monstre change apres 100 iteration
    @Test
    void testMonsterDoesNotGetStuck() {
        int initialX = monster.getX();
        int initialY = monster.getY();

        int maxIterations = 100;

        boolean positionChanged = false;

        for (int i = 0; i < maxIterations; i++) {
            monster.update();

            if (monster.getX() != initialX && monster.getY() != initialY) {
                positionChanged = true;
                break;
            }
        }

        // Verify that the monster's position has changed from the initial position
        assertNotEquals(initialX, monster.getX(), "Monster should not get stuck (X position)");
        assertNotEquals(initialY, monster.getY(), "Monster should not get stuck (Y position)");
    }

}
