import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class MazeMapGenerator {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    // Define tile types
    private static final int GRASS = 0; // Movable path
    private static final int WALL = 1;
    private static final int WATER = 2;
    private static final int TREE = 4; // Outline and obstacle

    // Player's starting position
    private static final int START_X = 23;
    private static final int START_Y = 21;

    public static void main(String[] args) {
        int[][] map = generateMazeMap(WIDTH, HEIGHT);
        saveMapToFile(map, "mazeMap.txt");
    }

    private static int[][] generateMazeMap(int width, int height) {
        int[][] map = new int[height][width];

        // Step 1: Fill the entire map with obstacles
        fillMapWithObstacles(map);

        // Step 2: Carve a narrow maze-like path
        carveMazePath(map, START_X, START_Y);

        return map;
    }

    private static void fillMapWithObstacles(int[][] map) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = TREE; // Fill with trees as obstacles
            }
        }
    }

    private static void carveMazePath(int[][] map, int startX, int startY) {
        Random random = new Random();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});
        map[startY][startX] = GRASS; // Start point as a movable tile

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            // Randomly shuffle directions to ensure varied path direction
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            shuffleArray(directions, random);

            for (int[] direction : directions) {
                int nx = x + direction[0] * 2;
                int ny = y + direction[1] * 2;
                int mx = x + direction[0];
                int my = y + direction[1];

                // Ensure the path is narrow and maze-like by carving single-tile paths
                if (isValid(nx, ny, map)) {
                    map[my][mx] = GRASS; // Path tile between cells
                    map[ny][nx] = GRASS; // Movable path tile
                    stack.push(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int[][] map) {
        // Check bounds and ensure the tile is an obstacle
        return x > 0 && y > 0 && x < WIDTH - 1 && y < HEIGHT - 1 && map[y][x] == TREE;
    }

    private static void shuffleArray(int[][] array, Random random) {
        for (int i = 0; i < array.length; i++) {
            int j = random.nextInt(array.length);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void saveMapToFile(int[][] map, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : map) {
                for (int tile : row) {
                    writer.write(tile + " ");
                }
                writer.newLine();
            }
            System.out.println("Maze-like map saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
