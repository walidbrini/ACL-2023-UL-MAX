package Code.game.src.main.java.com.example;

import java.awt.*;
import java.util.Random;

enum Level{
    CHICKEN,
    EASY,
    MEDIUM,
    HARD,
    INSANE
}

public class Labyrinth {
    private final int width;
    private final int height;
    private final Square[][] grid;
    private final Level level;
    private final Walkway walkway = new Walkway();
    private final Wall wall = new Wall();
    private final Fire fire = new Fire();
    private final Aid firstAid = new Aid();
    private final Random random = new Random();
    private final Spawn spawn = new Spawn();
    private final Treasure treasure = new Treasure();
    private GamePanel gamePanel;


    public Labyrinth(int width, int height, Level level, GamePanel gamePanel) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.grid = new Square[width][height];
        this.gamePanel = gamePanel;
        generateRandomly();
    }

    private void fillBordersWithWalls(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == (height - 1))
                    grid[i][j] = wall;
                else  grid[i][j] = walkway;
            }
        }
    }

    private void randomizeWalls(){
        double wallProbability = 0;

        switch (level) {
            case CHICKEN, EASY:
                wallProbability = 0.05; // 1% chance of a wall
                break;
            case MEDIUM:
                wallProbability = 0.07; // 6% chance of a wall
                break;
            case HARD:
                wallProbability = 0.09; // 8% chance of a wall
                break;
            case INSANE:
                wallProbability = 0.2; // 12% chance of a wall
                break;
        }

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if(grid[i][j].getContent() == null || grid[i][j].getContent() == Object.WALKWAY){
                    if (random.nextDouble() < wallProbability)
                        grid[i][j] = wall; // Place a wall based on the probability for the selected level
                }
            }
        }
    }

    private void randomizeFire() {
        double fireProbability = 0;

        switch (level) {
            case CHICKEN:
                fireProbability = 0.0;  // No fire for CHICKEN level
                break;
            case EASY:
                fireProbability = 0.03;  // 2% chance of fire
                break;
            case MEDIUM:
                fireProbability = 0.07;  // 4% chance of fire
                break;
            case HARD:
                fireProbability = 0.1;  // 6% chance of fire
                break;
            case INSANE:
                fireProbability = 0.12;  // 10% chance of fire
                break;
        }


        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if(grid[i][j].getContent() == null || grid[i][j].getContent() == Object.WALKWAY){
                    if (random.nextDouble() < fireProbability) {
                        grid[i][j] = fire; // Place fire based on the probability for the selected level
                    }
                }

            }
        }
    }

    private void randomizeAid() {
        double aidProbability = 0;
        switch (level) {
            case CHICKEN:
                aidProbability = 0.05;  // 10% chance of first aid
                break;
            case EASY, MEDIUM:
                aidProbability = 0.025; // 8% chance of first aid
                break;
            case HARD:
                aidProbability = 0.012; // 4% chance of first aid
                break;
            case INSANE:
                aidProbability = 0.005; // 2% chance of first aid
                break;
        }


        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if(grid[i][j].getContent() == null || grid[i][j].getContent() == Object.WALKWAY){
                    if (random.nextDouble() < aidProbability) {
                        grid[i][j] = firstAid; // Place first aid based on the probability for the selected level
                    }
                }

            }
        }
    }

    private void randomizeStartAndFinishPoints() {
        int minX = 1;
        int minY = 1;
        int maxX = width - 2; // Initialize maxX outside the switch statement
        int maxY = height - 2; // Initialize maxY outside the switch statement

        // Place the starting point randomly within the labyrinth
        Coordinates spawnPosition = getRandomCoordinates(minX, minY, maxX, maxY);

        // Adjust the range for placing the finish point based on the level
        int minDistanceToSpawn = 0;
        int maxDistanceToSpawn = 0;

        switch (level) {
            case CHICKEN:
                minDistanceToSpawn = width / 4;
                maxDistanceToSpawn = Math.min(width / 2, height / 2);
                break;
            case EASY:
            case MEDIUM:
                minDistanceToSpawn = width / 2;
                maxDistanceToSpawn = Math.min(3 * width / 4, 3 * height / 4);
                break;
            case HARD:
            case INSANE:
                minDistanceToSpawn = 50 * width / 100;
                maxDistanceToSpawn = Math.min(7 * width / 8, 7 * height / 8);
                break;
            default:
                // maxX and maxY are already initialized above
        }

        // Randomly place the finish point within the generated range
        Coordinates treasurePosition;
        do {
            treasurePosition = getRandomCoordinates(minX, minY, maxX, maxY);
        } while (isTooCloseToSpawn(spawnPosition, treasurePosition, minDistanceToSpawn, maxDistanceToSpawn));

        // Set the spawn and treasure points
        grid[spawnPosition.getX()][spawnPosition.getY()] = spawn;
        grid[treasurePosition.getX()][treasurePosition.getY()] = treasure;
        spawn.setPosition(spawnPosition);
        treasure.setPosition(treasurePosition);
    }


    private boolean isTooCloseToSpawn(Coordinates spawnPosition, Coordinates treasurePosition, int minDistance, int maxDistance) {
        int dx = spawnPosition.getX() - treasurePosition.getX();
        int dy = spawnPosition.getY() - treasurePosition.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < minDistance || distance > maxDistance;
    }

    private Coordinates getRandomCoordinates(int minX, int minY, int maxX, int maxY) {
        Random random = new Random();
        int randomX = random.nextInt(maxX - minX + 1) + minX;
        int randomY = random.nextInt(maxY - minY + 1) + minY;
        return new Coordinates(randomX, randomY);
    }

    private boolean isReachable(Coordinates pointA, Coordinates pointB) {
        // Create a 2D array to track visited squares
        boolean[][] visited = new boolean[width][height];

        int startX = pointA.getX();
        int startY = pointA.getY();
        int endX = pointB.getX();
        int endY = pointB.getY();

        // Call the recursive DFS method to check if point B is reachable from point A
        return isReachableDFS(startX, startY, endX, endY, visited);
    }

    private boolean isReachableDFS(int x, int y, int endX, int endY, boolean[][] visited) {
        // Base case: If the current cell is out of bounds or a wall, return false
        if (x < 0 || x >= width || y < 0 || y >= height || grid[x][y] == wall || grid[x][y] == fire || visited[x][y]) {
            return false;
        }

        // Base case: If we've reached the destination, return true
        if (x == endX && y == endY) {
            return true;
        }

        // Mark the current cell as visited
        visited[x][y] = true;

        // Recursively check in all four directions (up, down, left, right)
        return isReachableDFS(x - 1, y, endX, endY, visited) ||
                isReachableDFS(x + 1, y, endX, endY, visited) ||
                isReachableDFS(x, y - 1, endX, endY, visited) ||
                isReachableDFS(x, y + 1, endX, endY, visited);
    }

    private void generateRandomly(){
        boolean isReachable;

        do{
            this.fillBordersWithWalls(); // And fill the inside with walkways
            this.randomizeWalls();
            randomizeStartAndFinishPoints();
            this.randomizeFire();
            isReachable = isReachable(spawn.getPosition(),treasure.getPosition());
            System.out.println(isReachable);
        } while(!isReachable);
        randomizeAid();
    }

    /* TODO
    private void savetoFile(){

    }

    private void generateFromFile(){

    }
    */

    public Square[][] getGrid() {
        return grid;
    }
    
    public void draw(Graphics graphics){
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Square square = grid[x][y];
                int tileSize = gamePanel.getTileSize();
                int xPos = x * tileSize;
                int yPos = y * tileSize;

                // Check the square's content and draw it accordingly
                if (square.getContent() == Object.WALL) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                } else if (square.getContent() == Object.FIRE) {
                    graphics.setColor(Color.RED);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                } else if (square.getContent() == Object.AID) {
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                } else if (square.getContent() == Object.WALKWAY) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                } else if (square.getContent() == Object.SPAWN) {
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                } else if (square.getContent() == Object.TREASURE) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(xPos, yPos, tileSize, tileSize);
                }
            }
        }
    }

    // FOR TESTING
    public void afficheVersionTexte(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }


}