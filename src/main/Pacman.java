package main;
import java.awt.*;

public class Pacman extends GameObject {
    private int dx, dy;

    public Pacman(int x, int y) {
        super("pacmanRight.png", x, y, 20, 20); // Mặc định ban đầu
    }

    public void move(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);  // Vẽ Pacman
    }
}