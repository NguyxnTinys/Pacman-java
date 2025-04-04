package main;
import java.awt.*;

public class Pacman extends GameObject {
    private int dx = 0, dy = 0;
    private int speed = 5;
    private CollisionManager collisionManager;

    public Pacman(int x, int y, CollisionManager collisionManager) {
        super("pacmanRight.png", x, y, tileSize, tileSize);
        this.collisionManager = collisionManager;
    }

    // Hàm getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    // Hàm di chuyển
    public void moveUp() {
        dy = -1; // Hướng lên
    }

    public void moveDown() {
        dy = 1;  // Hướng xuống
    }

    public void moveLeft() {
        dx = -1; // Hướng trái
    }

    public void moveRight() {
        dx = 1;  // Hướng phải
    }

    public void stopMoving() {
        dx = 0;
        dy = 0;
    }

    public void move(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void update() {
        int nextX = x + dx * speed;
        int nextY = y + dy * speed;

        Rectangle nextBounds = new Rectangle(nextX, nextY, width, height);

        if (collisionManager.canMove(nextBounds)) {
            x = nextX;
            y = nextY;
        }

        collisionManager.handlePelletCollision(this);
        collisionManager.handleGhostCollision(this);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);  // Vẽ Pacman

        // Ví dụ: Vẽ Pacman
        g.setColor(Color.YELLOW);
        g.fillRect(x * (tileSize + gap), y * (tileSize + gap), tileSize, tileSize);
    }
}