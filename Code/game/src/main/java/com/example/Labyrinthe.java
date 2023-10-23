package Code.game.src.main.java.com.example;
import java.util.Random;

public class Labyrinthe {
    private int width;
    private int height;
    char[][] grid;

    public Labyrinthe(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[width][height];
        initializeLabyrinth();
    }

    private void initializeLabyrinth() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == (height - 1))
                    grid[i][j] = 'X';
                else
                    grid[i][j] = ' ';
            }
        }
    }


}