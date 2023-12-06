import com.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    private Labyrinth labyrinth;

    @BeforeEach
    void setUp() {
        labyrinth = new Labyrinth(16, 16, new GamePanel());
        labyrinth.generate(null);
    }

    @Test
    void testRightResults() {
        assertNotNull(labyrinth.getTreasure());
        assertNotNull(labyrinth.getSpawn());
        assertNotNull(labyrinth.getGrid());
    }

    @Test
    void LoadingInvalidFileShouldReturnException(){
        Labyrinth loadedLabyrith = new Labyrinth(new GamePanel());
        assertThrows(FileNotFoundException.class, () -> {labyrinth.loadFromFile("invalid/path/to/file.txt");}, "Exception not raised");
    }

    @Test
    void loadedShouldBeEqualToSaved() throws IOException, FileNotFoundException {
        Labyrinth loadedLabyrinth = new Labyrinth(16, 16, new GamePanel());
        labyrinth.saveToFile();
        loadedLabyrinth.loadFromFile(labyrinth.getFileSaveLocation());

        for (int i = 0; i < labyrinth.getHeight(); i++) {
            for (int j = 0; j < labyrinth.getWidth(); j++) {
                assertEquals(loadedLabyrinth.getGrid()[j][i].getContent(), labyrinth.getGrid()[j][i].getContent());
            }
        }
    }

    /*
    @Test
    void testBoundaryConditions() {
        // Test with the smallest possible labyrinth
        Labyrinth smallLabyrinth = new Labyrinth(1, 1, new GamePanel());
        smallLabyrinth.generate(null);
        assertNotNull(smallLabyrinth.getTreasure());
        assertNotNull(smallLabyrinth.getSpawn());
        assertNotNull(labyrinth.getGrid());
    }
    */
}