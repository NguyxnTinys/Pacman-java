package main;
import java.awt.*;

public class Pellet extends GameObject {
    public Pellet(int x, int y) {
        super("powerFood.png", x, y, 5, 5);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x + 7, y + 7, width, height);
    }
}