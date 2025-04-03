package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Pacman pacman;
    private HashSet<GameObject> ghosts;
    private HashSet<GameObject> walls;
    private HashSet<GameObject> pellets;

    public GamePanel() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // Khởi tạo đối tượng game
        pacman = new Pacman(50, 50);
        ghosts = new HashSet<>();
        walls = new HashSet<>();
        pellets = new HashSet<>();

        // Load bản đồ từ file (Lưu ý, bạn cần dùng phương thức đúng cho map)
        String[] tileMap = MapLoader.loadTileMap("src/res/maps/map1.txt");
        MapLoader.loadMap(tileMap, walls, pellets, ghosts, pacman);
        
        // Timer cập nhật game loop
        timer = new Timer(1000 / 60, this);
        addKeyListener(new KeyHandler(pacman));
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pacman.update();
        for (GameObject ghost : ghosts) ghost.update();
        repaint();
    }
}