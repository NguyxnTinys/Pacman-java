package main;
import java.awt.*;

public class Ghost extends GameObject {

    public Ghost(String imageName, int x, int y) {
        super(imageName, x, y, tileSize, tileSize); // Truyền đúng tham số cho GameObject
    }

    @Override
    public void update() {
        // AI movement logic (to be improved later)
    }

    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null); // Vẽ ảnh nếu có
        } else {
            g.setColor(Color.RED); // Mặc định màu nếu không có ảnh
            g.fillOval(x, y, width, height);
        }
    }
}