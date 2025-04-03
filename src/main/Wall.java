package main;
import java.awt.*;

public class Wall extends GameObject {
    public Wall(int x, int y) {
        super("wall.png",x, y, 30, 30);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}