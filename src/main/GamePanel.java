// GamePanel.java
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class GamePanel extends JPanel implements ActionListener {
    public static int score = 0;
    public static int lives = 3;

    private int tileSize = GameObject.tileSize;
    public static int gap = 5;
    private int colCount = 21;
    private int rowCount = 19;
    private int boardWidth = colCount * (tileSize + gap); 
    private int boardHeight = rowCount * (tileSize + gap);
    private int mapIdx = 1;

    private Timer timer;
    private Pacman pacman;
    private HashSet<GameObject> ghosts;
    private HashSet<GameObject> walls;
    private HashSet<GameObject> pellets;

    private CollisionManager collisionManager;

    private KeyHandler keyHandler;

    public GamePanel() {
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // Tạo collision manager trước
        ghosts = new HashSet<>();
        walls = new HashSet<>();
        pellets = new HashSet<>();
        
        collisionManager = new CollisionManager(walls, pellets, ghosts);
        // Tạo Pacman với collision manager
        pacman = new Pacman(50, 50, collisionManager);

        // Load bản đồ từ file
        String[] tileMap = MapLoader.loadTileMap("src/res/maps/map" + mapIdx + ".txt");
        MapLoader.loadMap(tileMap, walls, pellets, ghosts, pacman);

        // Timer cập nhật game loop
        timer = new Timer(1000 / 60, this);
        // TRONG constructor GamePanel() → sửa phần addKeyListener:
        keyHandler = new KeyHandler(pacman, collisionManager);
        addKeyListener(keyHandler);
        requestFocusInWindow();  // Đảm bảo nhận sự kiện bàn phím
    }

    public void startGame() {
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pacman.draw(g); // Vẽ Pacman
        for (GameObject ghost : ghosts) ghost.draw(g); // Vẽ Ghosts
        for (GameObject wall : walls) wall.draw(g); // Vẽ Walls
        for (GameObject pellet : pellets) pellet.draw(g); // Vẽ Pellets

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + collisionManager.getScore(), 10, 20);
        g.drawString("Lives: " + collisionManager.getLives(), 10, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyHandler.update();
        pacman.update();
        for (GameObject ghost : ghosts) ghost.update();

        // Kiểm tra nếu ăn hết pellet thì win
        if (pellets.isEmpty()) {
            timer.stop(); // Dừng game loop tạm thời
            mapIdx++; // Chuyển sang map tiếp theo

            JOptionPane.showMessageDialog(this, "You Win! Loading next map...");
            
            // XÓA dữ liệu map cũ
            walls.clear();
            pellets.clear();
            ghosts.clear();

            String[] tileMap = MapLoader.loadTileMap("src/res/maps/map" + mapIdx + ".txt");
            MapLoader.loadMap(tileMap, walls, pellets, ghosts, pacman);
            startGame();
        }
        repaint();
    }
}