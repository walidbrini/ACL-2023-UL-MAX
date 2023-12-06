import com.example.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

class LabyrinthTest {
    private Labyrinth labyrinth;

    @BeforeEach
    void setUp() {
        labyrinth = new Labyrinth(16, 16, new GamePanel());
        labyrinth.generate(null);
    }

    @Test
    void gridShouldNotBeNull() {
        assertNotNull(labyrinth.getGrid());
    }

    @Test
    void loadingInvalidFileShouldRaiseException(){
        Labyrinth loadedLabyrith = new Labyrinth(new GamePanel());
        assertThrows(FileNotFoundException.class, () -> labyrinth.loadFromFile("invalid/path/to/file.txt"), "Exception not raised");
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
}