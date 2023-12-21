import com.example.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class LabyrinthTest {

    private Labyrinth labyrinth;
    static String fileSaveLocation = "save_files/test_map.txt";

    @BeforeEach
    void setUp() throws IOException {
        labyrinth = new Labyrinth(new GamePanel());
        labyrinth.generate(null);
    }

    @Test
    void gridShouldNotBeNull() {
        assertNotNull(labyrinth.getGrid());
    }

    @Test
    void loadingInvalidFileShouldRaiseException() throws IOException {
        Labyrinth loadedLabyrith = new Labyrinth(new GamePanel());
        assertThrows(FileNotFoundException.class, () -> labyrinth.loadFromFile("invalid/path/to/file.txt"), "Exception not raised");
    }

    @Test
    void loadedShouldBeEqualToSaved() throws IOException, FileNotFoundException {
        Labyrinth loadedLabyrinth = new Labyrinth(new GamePanel());
        labyrinth.saveToFile(fileSaveLocation);
        loadedLabyrinth.loadFromFile(fileSaveLocation);

        for (int i = 0; i < labyrinth.getHeight(); i++) {
            for (int j = 0; j < labyrinth.getWidth(); j++) {
                assertEquals(loadedLabyrinth.getGrid()[j][i].getContent(), labyrinth.getGrid()[j][i].getContent());
            }
        }
    }

    @AfterAll
    static void clean() {
        File test_map = new File(fileSaveLocation);
        test_map.delete();
    }
    
}
