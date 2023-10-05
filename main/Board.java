package main;

import javax.swing.*;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    public int tileSize = 85;

    final int COLUMNS = 8;
    final int ROWS = 8;
    
    ArrayList<Piece> pieceList = new ArrayList<>();

    // Creates the board when called in Main
    public Board() {
        this.setPreferredSize(new Dimension(COLUMNS*tileSize, ROWS*tileSize));
        addBlackPieces();
        addWhitePieces();
    }

    // Adds the all  black pieces onto the board
    public void addBlackPieces() {
    	pieceList.add(new King(this, 4, 0, false));
    	pieceList.add(new Queen(this, 3, 0, false));
    	pieceList.add(new Bishop(this, 2, 0, false));
    	pieceList.add(new Bishop(this, 5, 0, false));
    	pieceList.add(new Knight(this, 6, 0, false));
    	pieceList.add(new Knight(this, 1, 0, false));
    	pieceList.add(new Rook(this, 0, 0, false));
    	pieceList.add(new Rook(this, 7, 0, false));
    	
    	pieceList.add(new Pawn(this, 0, 1, false));
    	pieceList.add(new Pawn(this, 1, 1, false));
    	pieceList.add(new Pawn(this, 2, 1, false));
    	pieceList.add(new Pawn(this, 3, 1, false));
    	pieceList.add(new Pawn(this, 4, 1, false));
    	pieceList.add(new Pawn(this, 5, 1, false));
    	pieceList.add(new Pawn(this, 6, 1, false));
    	pieceList.add(new Pawn(this, 7, 1, false));
    }
    
    // Add all white pieces onto the board
    public void addWhitePieces() {
    	pieceList.add(new King(this, 4, 7, true));
    	pieceList.add(new Queen(this, 3, 7, true));
    	pieceList.add(new Bishop(this, 2, 7, true));
    	pieceList.add(new Bishop(this, 5, 7, true));
    	pieceList.add(new Knight(this, 6, 7, true));
    	pieceList.add(new Knight(this, 1, 7, true));
    	pieceList.add(new Rook(this, 0, 7, true));
    	pieceList.add(new Rook(this, 7, 7, true));
    	
    	pieceList.add(new Pawn(this, 0, 6, true));
    	pieceList.add(new Pawn(this, 1, 6, true));
    	pieceList.add(new Pawn(this, 2, 6, true));
    	pieceList.add(new Pawn(this, 3, 6, true));
    	pieceList.add(new Pawn(this, 4, 6, true));
    	pieceList.add(new Pawn(this, 5, 6, true));
    	pieceList.add(new Pawn(this, 6, 6, true));
    	pieceList.add(new Pawn(this, 7, 6, true));
    }
    
    
    // Paints the board 
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Paints the 8x8 chess board
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0 ; c < COLUMNS; c++) {
                g2d.setColor((c+r) % 2 == 0 ? new Color(227,198,181): new Color(157,105,53));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        
        for (Piece piece: pieceList) {
        	piece.paint(g2d);
        }
    }
}