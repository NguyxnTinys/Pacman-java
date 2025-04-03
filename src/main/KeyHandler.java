package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private Pacman pacman;

    public KeyHandler(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            pacman.move(0, -1);  // Di chuyển lên
            System.out.println("Moving Up");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            pacman.move(0, 1);   // Di chuyển xuống
            System.out.println("Moving Down");
        } else if (keyCode == KeyEvent.VK_LEFT) {
            pacman.move(-1, 0);  // Di chuyển sang trái
            System.out.println("Moving Left");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            pacman.move(1, 0);   // Di chuyển sang phải
            System.out.println("Moving Right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}