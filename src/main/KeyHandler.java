package main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private Pacman pacman;
    private CollisionManager collisionManager;

    private int desiredDx = 0;
    private int desiredDy = 0;

    public KeyHandler(Pacman pacman, CollisionManager collisionManager) {
        this.pacman = pacman;
        this.collisionManager = collisionManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            desiredDx = 0;
            desiredDy = -1;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            desiredDx = 0;
            desiredDy = 1;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            desiredDx = -1;
            desiredDy = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            desiredDx = 1;
            desiredDy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Không cần xử lý gì trong trường hợp này nếu không muốn dừng Pacman khi thả phím
    }

    public void update() {
        // Nếu người chơi chưa nhấn hướng nào thì không làm gì
        if (desiredDx == 0 && desiredDy == 0) return;

        int nextX = pacman.getX() + desiredDx * pacman.getSpeed();
        int nextY = pacman.getY() + desiredDy * pacman.getSpeed();

        Rectangle nextRect = new Rectangle(nextX, nextY, pacman.getWidth(), pacman.getHeight());

        if (collisionManager.canMove(nextRect)) {
            if (desiredDx == -1) pacman.moveLeft();
            else if (desiredDx == 1) pacman.moveRight();
            else if (desiredDy == -1) pacman.moveUp();
            else if (desiredDy == 1) pacman.moveDown();
        }
    }
}