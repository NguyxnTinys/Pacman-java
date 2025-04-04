package main;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;

public class CollisionManager {
    private HashSet<GameObject> walls;
    private HashSet<GameObject> pellets;
    private HashSet<GameObject> ghosts;

    private int score = GamePanel.score;
    private int lives = GamePanel.lives;

    public CollisionManager(HashSet<GameObject> walls, HashSet<GameObject> pellets, HashSet<GameObject> ghosts) {
        this.walls = walls;
        this.pellets = pellets;
        this.ghosts = ghosts;
    }

    public boolean canMove(Rectangle nextBounds) {
        for (GameObject wall : walls) {
            if (nextBounds.intersects(wall.getBounds())) {
                return false;
            }
        }
        return true;
    }

    public void handlePelletCollision(Pacman pacman) {
        Iterator<GameObject> it = pellets.iterator();
        while (it.hasNext()) {
            GameObject pellet = it.next();
            if (pacman.getBounds().intersects(pellet.getBounds())) {
                it.remove(); // Ăn viên pellet
                score++;
                System.out.println("Score: " + score);
            }
        }
    }

    public void handleGhostCollision(Pacman pacman) {
        for (GameObject ghost : ghosts) {
            if (pacman.getBounds().intersects(ghost.getBounds())) {
                lives--;
                System.out.println("Lives: " + lives);
                if (lives <= 0) {
                    System.out.println("Game Over!");
                    System.exit(0); // Kết thúc game (hoặc chuyển sang màn GameOver)
                }
                break;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}