package main;
import java.awt.*;

public class Pacman extends GameObject {
    private int dx = 0, dy = 0;
    private int speed = 5;
    private CollisionManager collisionManager;
    private int pendingDx = 0;
    private int pendingDy = 0;

    public void setPendingDirection(int dx, int dy) {
        pendingDx = dx;
        pendingDy = dy;
    }

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

    public void moveUp() {
        dx = 0;
        dy = -1;
    }
    public void moveDown() {
        dx = 0;
        dy = 1;
    }
    public void moveLeft() {
        dx = -1;
        dy = 0;
    }
    public void moveRight() {
        dx = 1;
        dy = 0;
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
        // Kiểm tra hướng pending trước
        int nextX = x + pendingDx * speed;
        int nextY = y + pendingDy * speed;
        Rectangle nextBounds = new Rectangle(nextX, nextY, width, height);

        if (dx > 0) {
            loadImage("pacmanRight.png");
        } else if (dx < 0) {
            loadImage("pacmanLeft.png");
        } else if (dy > 0) {
            loadImage("pacmanDown.png"); 
        } else if (dy < 0) {
            loadImage("pacmanUp.png");
        }

        if (pendingDx != 0 || pendingDy != 0) {
            if (collisionManager.canMove(nextBounds)) {
                dx = pendingDx;
                dy = pendingDy;
                pendingDx = 0;
                pendingDy = 0;
            }
        }

        // Tiếp tục di chuyển theo hướng hiện tại
        nextX = x + dx * speed;
        nextY = y + dy * speed;

        nextBounds = new Rectangle(nextX, nextY, width, height);
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