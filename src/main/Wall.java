package main;
import java.awt.*;

public class Wall extends GameObject {
    public Wall(int x, int y) {
        super("wall.png",x, y, tileSize, tileSize);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}