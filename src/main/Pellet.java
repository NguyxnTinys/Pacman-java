package main;
import java.awt.*;

public class Pellet extends GameObject {
    private final int pelletSize = 5;

    public Pellet(int x, int y) {
        super("powerFood.png", x, y, tileSize, tileSize);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        // Vẽ Pellet ở vị trí chính giữa
        int centerX = x + (width - pelletSize) / 2;
        int centerY = y + (height - pelletSize) / 2;
        g.setColor(Color.WHITE);
        g.fillOval(centerX, centerY, pelletSize, pelletSize);
    }
}