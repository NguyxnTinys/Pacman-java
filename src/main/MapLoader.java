package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class MapLoader {
    // Phương thức đọc file map và trả về mảng String[]
    public static String[] loadTileMap(String filePath) {
        StringBuilder map = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                map.append(line).append("\n"); // Thêm từng dòng vào StringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Chuyển StringBuilder thành mảng String[]
        return map.toString().split("\n");
    }

    public static void loadMap(String[] tileMap, HashSet<GameObject> walls, HashSet<GameObject> pellet, HashSet<GameObject> ghosts, GameObject pacman) {
        for (int row = 0; row < tileMap.length; row++) {
            String line = tileMap[row];
            for (int col = 0; col < line.length(); col++) {
                char tileMapChar = line.charAt(col);
                int x = col * GameObject.tileSize;
                int y = row * GameObject.tileSize;

                if (tileMapChar == 'X') { // Wall block
                    System.out.printf("Wall: x: %d, y: %d%n", x, y);
                    walls.add(new Wall(x, y));
                } else if (tileMapChar == '.') { // Wall block
                    System.out.printf("Pellet: x: %d, y: %d%n", x, y);
                    pellet.add(new Pellet(x, y));
                } else if (tileMapChar == 'b') { // Wall block
                    System.out.printf("Ghost1: x: %d, y: %d%n", x, y);
                    ghosts.add(new Ghost("blueGhost.png", x, y));
                } else if (tileMapChar == 'o') { // Wall block
                    System.out.printf("Ghost2: x: %d, y: %d%n", x, y);
                    ghosts.add(new Ghost("orangeGhost.png",x, y));
                } else if (tileMapChar == 'p') { // Wall block
                    System.out.printf("Ghost3: x: %d, y: %d%n", x, y);
                    ghosts.add(new Ghost("pinkGhost.png", x, y));
                } else if (tileMapChar == 'r') { // Wall block
                    System.out.printf("Ghost4: x: %d, y: %d%n", x, y);
                    ghosts.add(new Ghost("redGhost.png",x, y));
                } else if (tileMapChar == 'P') { // Wall block
                    System.out.printf("x: %d, y: %d%n", x, y);
                    pacman.setPosition(x, y);
                } 
            }
        }
        System.out.println("Load map success!!");
    }
}