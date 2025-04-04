package main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private Pacman pacman;
    private CollisionManager collisionManager;

    public KeyHandler(Pacman pacman, CollisionManager collisionManager) {
        this.pacman = pacman;
        this.collisionManager = collisionManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Kiểm tra hướng mới
        if (keyCode == KeyEvent.VK_UP) {
            // Nếu Pacman đang di chuyển ngang, kiểm tra xem có thể đi lên không
            if (pacman.getDx() != 0) {
                int nextX = pacman.getX() + pacman.getDx() * pacman.getSpeed();
                int nextY = pacman.getY() - pacman.getSpeed();
                if (collisionManager.canMove(new Rectangle(nextX, nextY, pacman.getWidth(), pacman.getHeight()))) {
                    pacman.moveUp(); // Nếu không có tường, chuyển hướng lên
                }
            } else {
                pacman.moveUp(); // Nếu đang di chuyển lên, không cần kiểm tra thêm
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            // Nếu Pacman đang di chuyển dọc, kiểm tra xem có thể đi phải không
            if (pacman.getDy() != 0) {
                int nextX = pacman.getX() + pacman.getSpeed();
                int nextY = pacman.getY() + pacman.getDy() * pacman.getSpeed();
                if (collisionManager.canMove(new Rectangle(nextX, nextY, pacman.getWidth(), pacman.getHeight()))) {
                    pacman.moveRight(); // Nếu không có tường, chuyển hướng phải
                }
            } else {
                pacman.moveRight(); // Nếu đang di chuyển phải, không cần kiểm tra thêm
            }
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            // Nếu Pacman đang di chuyển ngang, kiểm tra xem có thể đi xuống không
            if (pacman.getDx() != 0) {
                int nextX = pacman.getX() + pacman.getDx() * pacman.getSpeed();
                int nextY = pacman.getY() + pacman.getSpeed();
                if (collisionManager.canMove(new Rectangle(nextX, nextY, pacman.getWidth(), pacman.getHeight()))) {
                    pacman.moveDown(); // Nếu không có tường, chuyển hướng xuống
                }
            } else {
                pacman.moveDown(); // Nếu đang di chuyển xuống, không cần kiểm tra thêm
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            // Nếu Pacman đang di chuyển dọc, kiểm tra xem có thể đi trái không
            if (pacman.getDy() != 0) {
                int nextX = pacman.getX() - pacman.getSpeed();
                int nextY = pacman.getY() + pacman.getDy() * pacman.getSpeed();
                if (collisionManager.canMove(new Rectangle(nextX, nextY, pacman.getWidth(), pacman.getHeight()))) {
                    pacman.moveLeft(); // Nếu không có tường, chuyển hướng trái
                }
            } else {
                pacman.moveLeft(); // Nếu đang di chuyển trái, không cần kiểm tra thêm
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            pacman.stopMoving();
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            pacman.stopMoving();
        }
    }
}