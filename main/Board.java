package main;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    public int tileSize = 85;
    final int COLUMNS = 8;
    final int ROWS = 8;

    public Board() {
        this.setPreferredSize(new Dimension(COLUMNS*tileSize, ROWS*tileSize));
    }

    // Paints the board 
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0 ; c < COLUMNS; c++) {
                g2d.setColor((c+r) % 2 == 0 ? new Color(227,198,181): new Color(157,105,53));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
    }
}