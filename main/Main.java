package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String[] args) {
        // Creates the frame for the game
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0,0,0));
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        // Creates the chess board
        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);

    }
}