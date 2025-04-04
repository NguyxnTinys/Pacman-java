package main;
import java.awt.*;
import javax.swing.ImageIcon;

public abstract class GameObject {
    public static int tileSize = 40;
    public static int gap = GamePanel.gap;
    protected int x, y, width, height;
    protected int startX, startY;
    protected Image image;

    public GameObject(String imageName, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.width = width;
        this.height = height;
        
        loadImage(imageName); // Gọi loadImage để tải ảnh
    }

    public void loadImage(String imageName) {
        String path = "/res/images/" + imageName;
        try {
            image = new ImageIcon(getClass().getResource(path)).getImage();
            if (image == null) {
                throw new Exception("Image not found: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image: " + path);
        }
    }

    public abstract void update();

    void reset() {
        this.x = this.startX;
        this.y = this.startY;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }
    // Phương thức getBounds để lấy bounding box của đối tượng
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}